package io.vitaliivorobii.resp.encoder;

import io.netty.buffer.ByteBuf;
import io.vitaliivorobii.resp.types.RespAbstractSequence;
import io.vitaliivorobii.resp.types.RespDataType;
import io.vitaliivorobii.resp.utils.Constants;

import java.nio.charset.StandardCharsets;
import java.util.Collection;

public class RespAbstractSequenceEncoder<M extends RespAbstractSequence> implements RespEncoder<M> {
    private final RespEncoder<RespDataType> elementEncoder;
    private final char firstByte;

    public RespAbstractSequenceEncoder(char firstByte, RespEncoder<RespDataType> elementEncoder) {
        this.elementEncoder = elementEncoder;
        this.firstByte = firstByte;
    }

    @Override
    public void encode(M obj, ByteBuf out) {
        out.writeByte(firstByte);
        Collection<RespDataType> elements = obj.getElements();
        out.writeBytes(String.valueOf(elements.size()).getBytes(StandardCharsets.UTF_8));
        out.writeBytes(Constants.CRLF);
        for (RespDataType element : elements) {
            elementEncoder.encode(element, out);
        }
    }
}
