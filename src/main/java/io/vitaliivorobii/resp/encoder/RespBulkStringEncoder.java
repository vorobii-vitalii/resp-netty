package io.vitaliivorobii.resp.encoder;

import io.netty.buffer.ByteBuf;
import io.vitaliivorobii.resp.types.RespBulkString;
import io.vitaliivorobii.resp.utils.Constants;

import java.nio.charset.StandardCharsets;

public class RespBulkStringEncoder implements RespEncoder<RespBulkString> {

    @Override
    public void encode(RespBulkString obj, ByteBuf out) {
        out.writeByte('$');
        String strData = obj.data();
        out.writeBytes(String.valueOf(strData.length()).getBytes(StandardCharsets.UTF_8));
        out.writeBytes(Constants.CRLF);
        out.writeBytes(strData.getBytes(StandardCharsets.UTF_8));
        out.writeBytes(Constants.CRLF);
    }

}
