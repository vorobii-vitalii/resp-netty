package io.vitaliivorobii.resp.decoder;

import io.netty.buffer.ByteBuf;
import io.vitaliivorobii.resp.types.RespDataType;
import io.vitaliivorobii.resp.types.RespDecodeResult;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class DelegatingRespDecoder implements RespDecoder {
    private static final int FIRST = 0;

    private final Map<Byte, RespDecoder> decoderByFirstByte = new HashMap<>();

    public void addDecoder(byte c, RespDecoder decoder) {
        if (decoder == null) {
            throw new IllegalArgumentException("decoder must not be null");
        }
        decoderByFirstByte.put(c, decoder);
    }

    @Override
    public Optional<RespDecodeResult<? extends RespDataType>> decode(ByteBuf buffer) {
        if (!buffer.isReadable()) {
            return Optional.empty();
        }
        byte b = buffer.getByte(FIRST);
        RespDecoder decoder = decoderByFirstByte.get(b);
        if (decoder == null) {
            throw new IllegalArgumentException("decoder not defined for " + (char) (b));
        }
        return decoder.decode(buffer);
    }
}
