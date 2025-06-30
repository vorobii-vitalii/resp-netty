package io.vitaliivorobii.resp.decoder;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufProcessor;
import io.vitaliivorobii.resp.types.RespBoolean;
import io.vitaliivorobii.resp.types.RespDataType;
import io.vitaliivorobii.resp.types.RespDecodeResult;
import io.vitaliivorobii.resp.utils.Constants;

import java.util.Optional;

public class RespBooleanDecoder implements RespDecoder {
    protected static final char TRUE_VALUE = 't';

    @Override
    public Optional<RespDecodeResult<? extends RespDataType>> decode(ByteBuf buffer) {
        int lfIndex = buffer.forEachByte(ByteBufProcessor.FIND_LF);
        return lfIndex == Constants.NOT_FOUND ? Optional.empty() : Optional.of(new RespDecodeResult<>(
                new RespBoolean(buffer.getByte(1) == TRUE_VALUE),
                lfIndex + 1
        ));
    }
}
