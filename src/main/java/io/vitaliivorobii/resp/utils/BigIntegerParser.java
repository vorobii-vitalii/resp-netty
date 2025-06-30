package io.vitaliivorobii.resp.utils;

import io.netty.buffer.ByteBuf;

import java.math.BigInteger;

public class BigIntegerParser {

    public static BigInteger parseBigNumberInBase10(ByteBuf byteBuf, int start, int end) {
        byte firstByte = byteBuf.getByte(start);
        int mult = 1;
        if (firstByte == '-') {
            mult = -1;
            start++;
        } else if (firstByte == '+') {
            start++;
        }
        BigInteger res = BigInteger.ZERO;
        for (int i = start; i < end; i++) {
            byte b = byteBuf.getByte(i);
            res = res.multiply(BigInteger.TEN);
            res = res.add(BigInteger.valueOf(b - '0'));
        }
        return res.multiply(BigInteger.valueOf(mult));
    }
}
