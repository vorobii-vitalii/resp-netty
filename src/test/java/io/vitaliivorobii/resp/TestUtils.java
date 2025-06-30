package io.vitaliivorobii.resp;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.vitaliivorobii.resp.encoder.RespEncoder;
import io.vitaliivorobii.resp.types.RespDataType;

import java.nio.charset.StandardCharsets;

public class TestUtils {

    public static ByteBuf createByteBufFromString(String str) {
        return Unpooled.copiedBuffer(str.getBytes(StandardCharsets.UTF_8));
    }

    public static <T extends RespDataType, R extends T> String encode(RespEncoder<T> encoder, R resp) {
        ByteBuf buffer = Unpooled.buffer();
        encoder.encode(resp, buffer);
        return buffer.toString(StandardCharsets.UTF_8);
    }

}
