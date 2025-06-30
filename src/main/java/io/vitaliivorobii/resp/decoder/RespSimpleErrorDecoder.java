package io.vitaliivorobii.resp.decoder;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufProcessor;
import io.vitaliivorobii.resp.types.RespDataType;
import io.vitaliivorobii.resp.types.RespDecodeResult;
import io.vitaliivorobii.resp.types.RespSimpleError;
import io.vitaliivorobii.resp.utils.Constants;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

public class RespSimpleErrorDecoder implements RespDecoder {

    // TODO: create generic class
    @Override
    public Optional<RespDecodeResult<? extends RespDataType>> decode(ByteBuf buffer) {
        int lfIndex = buffer.forEachByte(ByteBufProcessor.FIND_LF);
        if (lfIndex == Constants.NOT_FOUND) {
            return Optional.empty();
        }
        String data = buffer.toString(1, lfIndex - 2, StandardCharsets.UTF_8);
        return Optional.of(new RespDecodeResult<>(
                new RespSimpleError(data),
                lfIndex + 1
        ));
    }
}
