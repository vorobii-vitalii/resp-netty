package io.vitaliivorobii.resp.utils;

import io.netty.buffer.ByteBuf;
import io.vitaliivorobii.resp.processors.ByteProcessors;

public class DoubleParser {

    public static double parseDoubleInBase10(ByteBuf byteBuf, int start, int end) {
        int dotIndex = byteBuf.forEachByte(ByteProcessors.FIND_DOT);
        if (dotIndex == Constants.NOT_FOUND) {
            return IntegerParser.parseIntInBase10(byteBuf, start, end);
        }
        double value = IntegerParser.parseIntInBase10(byteBuf, start, dotIndex);
        int dir = value < 0 ? -1 : 1;
        value += dir * parseFloatPartInBase10(byteBuf, dotIndex + 1, end);
        return value;
    }

    private static double parseFloatPartInBase10(ByteBuf byteBuf, int start, int end) {
        double x = 0.1;
        double res = 0;
        for (int i = start; i < end; i++) {
            byte b = byteBuf.getByte(i);
            res += (b - '0') * x;
            x /= 10;
        }
        return res;
    }

}
