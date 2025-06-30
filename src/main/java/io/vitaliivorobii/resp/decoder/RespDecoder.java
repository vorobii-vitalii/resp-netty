package io.vitaliivorobii.resp.decoder;

import io.netty.buffer.ByteBuf;
import io.vitaliivorobii.resp.types.RespDataType;
import io.vitaliivorobii.resp.types.RespDecodeResult;

import java.util.Optional;

public interface RespDecoder {

    Optional<RespDecodeResult<? extends RespDataType>> decode(ByteBuf buffer);

}
