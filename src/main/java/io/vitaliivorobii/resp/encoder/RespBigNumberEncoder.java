package io.vitaliivorobii.resp.encoder;

import io.netty.buffer.ByteBuf;
import io.vitaliivorobii.resp.types.RespBigNumber;
import io.vitaliivorobii.resp.types.RespDataTypeCodes;
import io.vitaliivorobii.resp.utils.Constants;

import java.nio.charset.StandardCharsets;

public class RespBigNumberEncoder implements RespEncoder<RespBigNumber> {
    @Override
    public void encode(RespBigNumber obj, ByteBuf out) {
        out.writeByte(RespDataTypeCodes.BIG_NUMBER);
        out.writeBytes(obj.value().toString().getBytes(StandardCharsets.UTF_8));
        out.writeBytes(Constants.CRLF);
    }
}
