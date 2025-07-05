package io.vitaliivorobii.resp.decoder;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufProcessor;
import io.vitaliivorobii.resp.types.RespDataType;
import io.vitaliivorobii.resp.types.RespDecodeResult;
import io.vitaliivorobii.resp.types.RespNullArray;
import io.vitaliivorobii.resp.utils.Constants;
import io.vitaliivorobii.resp.utils.IntegerParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class RespGenericSequenceDecoder<C extends RespDataType> implements RespDecoder {
    public static final int NUM_ELEMENTS_START = 1;
    private static final Logger log = LoggerFactory.getLogger(RespGenericSequenceDecoder.class);

    private final RespDecoder elementDecoder;
    private final Function<Integer, C> sequenceCreator;
    private final BiConsumer<RespDataType, C> elementAppender;

    public RespGenericSequenceDecoder(
            RespDecoder elementDecoder,
            Function<Integer, C> sequenceCreator,
            BiConsumer<RespDataType, C> elementAppender
    ) {
        this.elementDecoder = elementDecoder;
        this.sequenceCreator = sequenceCreator;
        this.elementAppender = elementAppender;
    }

    @Override
    public Optional<RespDecodeResult<? extends RespDataType>> decode(ByteBuf buffer) {
        int crIndex = buffer.forEachByte(ByteBufProcessor.FIND_CR);
        int readableBytes = buffer.readableBytes();
        if (crIndex == Constants.NOT_FOUND || crIndex + 1 == readableBytes) {
            return Optional.empty();
        }
        int numElements = IntegerParser.parseIntInBase10(buffer, NUM_ELEMENTS_START, crIndex);
        if (numElements == -1) {
            return Optional.of(new RespDecodeResult<>(
                    new RespNullArray(),
                    crIndex + Constants.CRLF_LENGTH
            ));
        }
        C container = sequenceCreator.apply(numElements);
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
            elementAppender.accept(decoded.parsedObject(), container);
            elemStart += decoded.scanedBytes();
            availableBytes -= decoded.scanedBytes();
            scannedBytes += decoded.scanedBytes();
        }
        return Optional.of(new RespDecodeResult<>(container, scannedBytes));
    }

}
