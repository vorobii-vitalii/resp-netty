package io.vitaliivorobii.resp.encoder;

import io.netty.buffer.ByteBuf;
import io.vitaliivorobii.resp.types.RespDataType;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("raw")
public class DelegatingRespEncoder implements RespEncoder<RespDataType> {
    private final Map<Class<? extends RespDataType>, RespEncoder> encoders = new HashMap<>();

    public <R extends RespDataType, T extends RespEncoder<R>>
    void addEncoder(Class<R> type, T encoder) {
        if (type == null) {
            throw new IllegalArgumentException("type must not be null");
        }
        if (encoder == null) {
            throw new IllegalArgumentException("encoder must not be null");
        }
        encoders.put(type, encoder);
    }

    @Override
    public void encode(RespDataType obj, ByteBuf out) {
        var encoder = encoders.get(obj.getClass());
        if (encoder == null) {
            throw new IllegalStateException("No encoder registered for " + obj.getClass());
        }
        encoder.encode(obj, out);
    }
}
