package io.vitaliivorobii.resp.decoder;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufProcessor;
import io.vitaliivorobii.resp.types.RespDataType;
import io.vitaliivorobii.resp.types.RespDecodeResult;
import io.vitaliivorobii.resp.types.RespInteger;
import io.vitaliivorobii.resp.utils.Constants;
import io.vitaliivorobii.resp.utils.IntegerParser;

import java.util.Optional;

public class RespIntegerDecoder implements RespDecoder {

    @Override
    public Optional<RespDecodeResult<? extends RespDataType>> decode(ByteBuf buffer) {
        int lfIndex = buffer.forEachByte(ByteBufProcessor.FIND_LF);
        if (lfIndex == Constants.NOT_FOUND) {
            return Optional.empty();
        }
        return Optional.of(new RespDecodeResult<>(
                new RespInteger(IntegerParser.parseIntInBase10(buffer, 1, lfIndex - 1)),
                lfIndex + 1
        ));
    }

}
