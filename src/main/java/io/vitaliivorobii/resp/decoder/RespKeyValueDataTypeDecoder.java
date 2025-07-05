package io.vitaliivorobii.resp.decoder;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufProcessor;
import io.vitaliivorobii.resp.types.RespDataType;
import io.vitaliivorobii.resp.types.RespDecodeResult;
import io.vitaliivorobii.resp.types.RespKeyValueDataType;
import io.vitaliivorobii.resp.types.RespMap;
import io.vitaliivorobii.resp.utils.Constants;
import io.vitaliivorobii.resp.utils.IntegerParser;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public class RespKeyValueDataTypeDecoder<M extends RespKeyValueDataType> implements RespDecoder {
    public static final int NUM_ENTRIES_START = 1;

    private final RespDecoder elementDecoder;
    private final Function<Map<RespDataType, RespDataType>, M> respFactory;

    public RespKeyValueDataTypeDecoder(
            RespDecoder elementDecoder,
            Function<Map<RespDataType, RespDataType>, M> respFactory
    ) {
        this.elementDecoder = elementDecoder;
        this.respFactory = respFactory;
    }

    @Override
    public Optional<RespDecodeResult<? extends RespDataType>> decode(ByteBuf buffer) {
        int crIndex = buffer.forEachByte(ByteBufProcessor.FIND_CR);
        int readableBytes = buffer.readableBytes();
        if (crIndex == Constants.NOT_FOUND || crIndex + 1 == readableBytes) {
            return Optional.empty();
        }
        int numEntries = IntegerParser.parseIntInBase10(buffer, NUM_ENTRIES_START, crIndex);
        int scannedBytes = crIndex + Constants.CRLF_LENGTH;
        int elemStart = scannedBytes;
        int availableBytes = buffer.readableBytes() - scannedBytes;
        Map<RespDataType, RespDataType> map = new HashMap<>();
        for (int i = 0; i < numEntries; i++) {
            Optional<RespDecodeResult<? extends RespDataType>> keyDecodeRes =
                    elementDecoder.decode(buffer.slice(elemStart, availableBytes));
            if (keyDecodeRes.isEmpty()) {
                return Optional.empty();
            }
            RespDecodeResult<? extends RespDataType> decodedKey = keyDecodeRes.get();
            elemStart += decodedKey.scanedBytes();
            availableBytes -= decodedKey.scanedBytes();
            scannedBytes += decodedKey.scanedBytes();

            Optional<RespDecodeResult<? extends RespDataType>> valueDecodeRes =
                    elementDecoder.decode(buffer.slice(elemStart, availableBytes));
            if (valueDecodeRes.isEmpty()) {
                return Optional.empty();
            }
            RespDecodeResult<? extends RespDataType> decodedValue = valueDecodeRes.get();
            elemStart += decodedValue.scanedBytes();
            availableBytes -= decodedValue.scanedBytes();
            scannedBytes += decodedValue.scanedBytes();

            map.put(decodedKey.parsedObject(), decodedValue.parsedObject());
        }
        return Optional.of(
                new RespDecodeResult<>(
                        respFactory.apply(map),
                        scannedBytes
                )
        );
    }
}
