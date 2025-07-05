package io.vitaliivorobii.resp.encoder;

import io.netty.buffer.ByteBuf;
import io.vitaliivorobii.resp.types.RespDataTypeCodes;
import io.vitaliivorobii.resp.types.RespSimpleString;
import io.vitaliivorobii.resp.utils.Constants;

import java.nio.charset.StandardCharsets;

public class RespSimpleStringEncoder implements RespEncoder<RespSimpleString> {

    @Override
    public void encode(RespSimpleString obj, ByteBuf out) {
        out.writeByte(RespDataTypeCodes.SIMPLE_STRING);
        out.writeBytes(obj.data().getBytes(StandardCharsets.UTF_8));
        out.writeBytes(Constants.CRLF);
    }

}
