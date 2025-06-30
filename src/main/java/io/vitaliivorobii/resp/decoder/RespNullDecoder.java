package io.vitaliivorobii.resp.decoder;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufProcessor;
import io.vitaliivorobii.resp.types.RespDataType;
import io.vitaliivorobii.resp.types.RespDecodeResult;
import io.vitaliivorobii.resp.types.RespNull;

import java.util.Optional;

public class RespNullDecoder implements RespDecoder {
    // _\r\n

    @Override
    public Optional<RespDecodeResult<? extends RespDataType>> decode(ByteBuf buffer) {
        int lfIndex = buffer.forEachByte(ByteBufProcessor.FIND_LF);
        return lfIndex == -1 ? Optional.empty() : Optional.of(new RespDecodeResult<>(
                new RespNull(),
                lfIndex + 1
        ));
    }
}
