package io.vitaliivorobii.resp.encoder;

import io.netty.buffer.ByteBuf;
import io.vitaliivorobii.resp.types.RespInteger;
import io.vitaliivorobii.resp.utils.Constants;

import java.nio.charset.StandardCharsets;

public class RespIntegerEncoder implements RespEncoder<RespInteger> {

    @Override
    public void encode(RespInteger obj, ByteBuf out) {
        out.writeByte(':');
        out.writeBytes(String.valueOf(obj.value()).getBytes(StandardCharsets.UTF_8));
        out.writeBytes(Constants.CRLF);
    }

}
