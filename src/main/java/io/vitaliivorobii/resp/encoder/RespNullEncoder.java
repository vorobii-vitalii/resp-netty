package io.vitaliivorobii.resp.encoder;

import io.netty.buffer.ByteBuf;
import io.vitaliivorobii.resp.types.RespNull;

import java.nio.charset.StandardCharsets;

public class RespNullEncoder implements RespEncoder<RespNull> {
    private static final byte[] NULL_BYTES = "_\r\n".getBytes(StandardCharsets.UTF_8);

    @Override
    public void encode(RespNull obj, ByteBuf out) {
        out.writeBytes(NULL_BYTES);
    }
}
