package io.vitaliivorobii.resp.encoder;

import io.netty.buffer.ByteBuf;
import io.vitaliivorobii.resp.types.RespNullArray;

import java.nio.charset.StandardCharsets;

public class RespNullArrayEncoder implements RespEncoder<RespNullArray> {
    private static final byte[] NULL_ARRAY_BYTES = "*-1\r\n".getBytes(StandardCharsets.UTF_8);

    @Override
    public void encode(RespNullArray obj, ByteBuf out) {
        out.writeBytes(NULL_ARRAY_BYTES);
    }
}
