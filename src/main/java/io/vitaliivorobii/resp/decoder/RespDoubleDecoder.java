package io.vitaliivorobii.resp.decoder;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufProcessor;
import io.vitaliivorobii.resp.processors.ByteProcessors;
import io.vitaliivorobii.resp.types.RespDataType;
import io.vitaliivorobii.resp.types.RespDecodeResult;
import io.vitaliivorobii.resp.types.RespDouble;
import io.vitaliivorobii.resp.utils.Constants;
import io.vitaliivorobii.resp.utils.DoubleParser;
import io.vitaliivorobii.resp.utils.IntegerParser;

import java.util.Optional;

public class RespDoubleDecoder implements RespDecoder {

    @Override
    public Optional<RespDecodeResult<? extends RespDataType>> decode(ByteBuf buffer) {
        int crIndex = buffer.forEachByte(ByteBufProcessor.FIND_CR);
        if (crIndex == Constants.NOT_FOUND || buffer.readableBytes() == crIndex + 1) {
            return Optional.empty();
        }
        int exponentIndex = buffer.forEachByte(ByteProcessors.FIND_EXPONENT);
        int exponentValue = exponentIndex == Constants.NOT_FOUND
                ? 0
                : IntegerParser.parseIntInBase10(buffer, exponentIndex + 1, crIndex);
        int exp = (int) Math.pow(10, exponentValue);
        int doubleEnd = exponentIndex == Constants.NOT_FOUND ? crIndex : exponentIndex;
        return Optional.of(new RespDecodeResult<>(
                new RespDouble(DoubleParser.parseDoubleInBase10(buffer, 1, doubleEnd) * exp),
                crIndex + Constants.CRLF_LENGTH
        ));
    }

}
