package io.vitaliivorobii.resp.decoder;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufProcessor;
import io.vitaliivorobii.resp.types.RespBigNumber;
import io.vitaliivorobii.resp.types.RespDataType;
import io.vitaliivorobii.resp.types.RespDecodeResult;
import io.vitaliivorobii.resp.utils.BigIntegerParser;
import io.vitaliivorobii.resp.utils.Constants;

import java.util.Optional;

public class RespBigNumberDecoder implements RespDecoder {

    @Override
    public Optional<RespDecodeResult<? extends RespDataType>> decode(ByteBuf buffer) {
        int lfIndex = buffer.forEachByte(ByteBufProcessor.FIND_LF);
        return lfIndex == Constants.NOT_FOUND ? Optional.empty() : Optional.of(new RespDecodeResult<>(
                new RespBigNumber(BigIntegerParser.parseBigNumberInBase10(buffer, 1, lfIndex - 1)),
                lfIndex + 1
        ));
    }

}
