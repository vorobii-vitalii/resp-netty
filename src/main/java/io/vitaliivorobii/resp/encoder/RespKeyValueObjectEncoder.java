package io.vitaliivorobii.resp.encoder;

import io.netty.buffer.ByteBuf;
import io.vitaliivorobii.resp.types.RespDataType;
import io.vitaliivorobii.resp.types.RespKeyValueDataType;
import io.vitaliivorobii.resp.types.RespMap;
import io.vitaliivorobii.resp.utils.Constants;

import java.nio.charset.StandardCharsets;
import java.util.Map;

public class RespKeyValueObjectEncoder<M extends RespKeyValueDataType> implements RespEncoder<M> {
    private final RespEncoder<RespDataType> encoder;
    private final char firstByte;

    public RespKeyValueObjectEncoder(char firstByte, RespEncoder<RespDataType> encoder) {
        this.encoder = encoder;
        this.firstByte = firstByte;
    }

    @Override
    public void encode(M obj, ByteBuf out) {
        out.writeByte(firstByte);
        var map = obj.getAsMap();
        out.writeBytes(Integer.toString(map.size()).getBytes(StandardCharsets.UTF_8));
        out.writeBytes(Constants.CRLF);
        for (Map.Entry<RespDataType, RespDataType> entry : map.entrySet()) {
            encoder.encode(entry.getKey(), out);
            encoder.encode(entry.getValue(), out);
        }
    }

}
