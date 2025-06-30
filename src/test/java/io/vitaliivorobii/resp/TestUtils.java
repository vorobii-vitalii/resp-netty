package io.vitaliivorobii.resp;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.charset.StandardCharsets;

public class TestUtils {

    public static ByteBuf createByteBufFromString(String str) {
        return Unpooled.copiedBuffer(str.getBytes(StandardCharsets.UTF_8));
    }

}
