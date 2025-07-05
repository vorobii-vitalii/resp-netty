package io.vitaliivorobii.resp.encoder;

import io.netty.buffer.ByteBuf;
import io.vitaliivorobii.resp.types.RespDataTypeCodes;
import io.vitaliivorobii.resp.types.RespDouble;
import io.vitaliivorobii.resp.utils.Constants;

import java.nio.charset.StandardCharsets;

public class RespDoubleEncoder implements RespEncoder<RespDouble> {

    @Override
    public void encode(RespDouble obj, ByteBuf out) {
        out.writeByte(RespDataTypeCodes.DOUBLE);
        out.writeBytes(String.valueOf(obj.value()).getBytes(StandardCharsets.UTF_8));
        out.writeBytes(Constants.CRLF);
    }

}
