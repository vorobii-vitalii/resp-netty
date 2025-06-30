package io.vitaliivorobii.resp.processors;

import io.netty.buffer.ByteBufProcessor;

public final class ByteProcessors {

    public static final ByteBufProcessor FIND_EXPONENT = value -> value != 'e' && value != 'E';

    public static final ByteBufProcessor FIND_DOT = value -> value != '.';

    private ByteProcessors() {
    }

}
