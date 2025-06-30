package io.vitaliivorobii.resp.decoder;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufProcessor;
import io.vitaliivorobii.resp.types.RespArray;
import io.vitaliivorobii.resp.types.RespDataType;
import io.vitaliivorobii.resp.types.RespDecodeResult;
import io.vitaliivorobii.resp.types.RespNullArray;
import io.vitaliivorobii.resp.utils.Constants;
import io.vitaliivorobii.resp.utils.IntegerParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RespArrayDecoder implements RespDecoder {
    public static final int NUM_ELEMENTS_START = 1;
    private static final Logger log = LoggerFactory.getLogger(RespArrayDecoder.class);

    private final RespDecoder elementDecoder;

    public RespArrayDecoder(RespDecoder elementDecoder) {
        this.elementDecoder = elementDecoder;
    }

    @Override
    public Optional<RespDecodeResult<? extends RespDataType>> decode(ByteBuf buffer) {
        int crIndex = buffer.forEachByte(ByteBufProcessor.FIND_CR);
        int readableBytes = buffer.readableBytes();
        if (crIndex == Constants.NOT_FOUND || crIndex + 1 == readableBytes) {
            return Optional.empty();
        }
        int numElements = IntegerParser.parseIntInBase10(buffer, NUM_ELEMENTS_START, crIndex);
        log.info(buffer.toString(StandardCharsets.UTF_8) + " -> " + numElements + " " + crIndex + " " + buffer.toString(1, crIndex - 1, StandardCharsets.UTF_8));
        if (numElements == -1) {
            return Optional.of(new RespDecodeResult<>(
                    new RespNullArray(),
                    crIndex + Constants.CRLF_LENGTH
            ));
        }
        List<RespDataType> elements = new ArrayList<>(numElements);
        for (int i = 0; i < numElements; i++) {
            elements.add(null);
        }
        int scannedBytes = crIndex + Constants.CRLF_LENGTH;
        int elemStart = scannedBytes;
        int availableBytes = buffer.readableBytes() - scannedBytes;
        for (int i = 0; i < numElements; i++) {
            Optional<RespDecodeResult<? extends RespDataType>> decodeResult =
                    elementDecoder.decode(buffer.slice(elemStart, availableBytes));
            if (decodeResult.isEmpty()) {
                return Optional.empty();
            }
            RespDecodeResult<? extends RespDataType> decoded = decodeResult.get();
            elements.set(i, decoded.parsedObject());
            elemStart += decoded.scanedBytes();
            availableBytes -= decoded.scanedBytes();
            scannedBytes += decoded.scanedBytes();
        }
        return Optional.of(new RespDecodeResult<>(
                new RespArray(elements),
                scannedBytes
        ));
    }

}
