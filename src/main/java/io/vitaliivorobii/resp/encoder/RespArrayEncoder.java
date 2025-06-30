package io.vitaliivorobii.resp.encoder;

import io.netty.buffer.ByteBuf;
import io.vitaliivorobii.resp.types.RespArray;
import io.vitaliivorobii.resp.types.RespDataType;
import io.vitaliivorobii.resp.utils.Constants;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class RespArrayEncoder implements RespEncoder<RespArray> {
    private final RespEncoder<RespDataType> elementEncoder;

    public RespArrayEncoder(RespEncoder<RespDataType> elementEncoder) {
        this.elementEncoder = elementEncoder;
    }

    @Override
    public void encode(RespArray obj, ByteBuf out) {
        out.writeByte('*');
        List<RespDataType> elements = obj.elements();
        out.writeBytes(String.valueOf(elements.size()).getBytes(StandardCharsets.UTF_8));
        out.writeBytes(Constants.CRLF);
        for (RespDataType element : elements) {
            elementEncoder.encode(element, out);
        }
    }
}
