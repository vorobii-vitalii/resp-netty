package io.vitaliivorobii.resp.utils;

import io.netty.buffer.ByteBuf;

public class IntegerParser {

    public static int parseIntInBase10(ByteBuf byteBuf, int start, int end) {
        int res = 0;
        byte firstByte = byteBuf.getByte(start);
        int mult = 1;
        if (firstByte == '-') {
            mult = -1;
            start++;
        } else if (firstByte == '+') {
            start++;
        }
        for (int i = start; i < end; i++) {
            byte b = byteBuf.getByte(i);
            res = res * 10 + (b - '0');
        }
        return mult * res;
    }

}
