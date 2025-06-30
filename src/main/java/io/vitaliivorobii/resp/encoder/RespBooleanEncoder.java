package io.vitaliivorobii.resp.encoder;

import io.netty.buffer.ByteBuf;
import io.vitaliivorobii.resp.types.RespBoolean;
import io.vitaliivorobii.resp.utils.Constants;

public class RespBooleanEncoder implements RespEncoder<RespBoolean> {
    @Override
    public void encode(RespBoolean obj, ByteBuf out) {
        out.writeByte('#');
        out.writeByte(obj.value() ? 't' : 'f');
        out.writeBytes(Constants.CRLF);
    }
}
