package io.vitaliivorobii.resp.encoder;

import io.netty.buffer.ByteBuf;
import io.vitaliivorobii.resp.types.RespDataType;
import io.vitaliivorobii.resp.types.RespMap;
import io.vitaliivorobii.resp.utils.Constants;

import java.nio.charset.StandardCharsets;
import java.util.Map;

public class RespMapEncoder implements RespEncoder<RespMap> {
    private final RespEncoder<RespDataType> encoder;

    public RespMapEncoder(RespEncoder<RespDataType> encoder) {
        this.encoder = encoder;
    }

    @Override
    public void encode(RespMap obj, ByteBuf out) {
        out.writeByte('%');
        out.writeBytes(Integer.toString(obj.map().size()).getBytes(StandardCharsets.UTF_8));
        out.writeBytes(Constants.CRLF);
        for (Map.Entry<RespDataType, RespDataType> entry : obj.map().entrySet()) {
            encoder.encode(entry.getKey(), out);
            encoder.encode(entry.getValue(), out);
        }
    }

}
