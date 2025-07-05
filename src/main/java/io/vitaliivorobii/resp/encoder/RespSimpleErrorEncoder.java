package io.vitaliivorobii.resp.encoder;

import io.netty.buffer.ByteBuf;
import io.vitaliivorobii.resp.types.RespDataTypeCodes;
import io.vitaliivorobii.resp.types.RespSimpleError;
import io.vitaliivorobii.resp.utils.Constants;

import java.nio.charset.StandardCharsets;

public class RespSimpleErrorEncoder implements RespEncoder<RespSimpleError> {

    @Override
    public void encode(RespSimpleError obj, ByteBuf out) {
        out.writeByte(RespDataTypeCodes.SIMPLE_ERROR);
        out.writeBytes(obj.errorMessage().getBytes(StandardCharsets.UTF_8));
        out.writeBytes(Constants.CRLF);
    }

}
