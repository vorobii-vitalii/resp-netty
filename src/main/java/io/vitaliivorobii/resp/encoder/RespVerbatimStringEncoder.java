package io.vitaliivorobii.resp.encoder;

import io.netty.buffer.ByteBuf;
import io.vitaliivorobii.resp.types.RespDataTypeCodes;
import io.vitaliivorobii.resp.types.RespVerbatimString;
import io.vitaliivorobii.resp.utils.Constants;

import java.nio.charset.StandardCharsets;

public class RespVerbatimStringEncoder implements RespEncoder<RespVerbatimString> {

    @Override
    public void encode(RespVerbatimString obj, ByteBuf out) {
        out.writeByte(RespDataTypeCodes.VERBATIM_STRING);
        out.writeBytes(Integer.toString(obj.data().length).getBytes(StandardCharsets.UTF_8));
        out.writeBytes(Constants.CRLF);
        out.writeBytes(obj.encoding().getBytes(StandardCharsets.UTF_8));
        out.writeByte(':');
        out.writeBytes(obj.data());
        out.writeBytes(Constants.CRLF);
    }

}
