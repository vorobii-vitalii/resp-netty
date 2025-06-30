package io.vitaliivorobii.resp.encoder;

import io.netty.buffer.ByteBuf;
import io.vitaliivorobii.resp.types.RespDataType;

public interface RespEncoder<T extends RespDataType> {

    void encode(T obj, ByteBuf out);

}
