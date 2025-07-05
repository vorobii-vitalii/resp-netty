package io.vitaliivorobii.resp.encoder;

import io.netty.buffer.ByteBuf;
import io.vitaliivorobii.resp.types.RespBulkError;
import io.vitaliivorobii.resp.types.RespDataTypeCodes;
import io.vitaliivorobii.resp.utils.Constants;

import java.nio.charset.StandardCharsets;

public class RespBulkErrorEncoder implements RespEncoder<RespBulkError> {

    @Override
    public void encode(RespBulkError obj, ByteBuf out) {
        out.writeByte(RespDataTypeCodes.BULK_ERROR);
        out.writeBytes(Integer.toString(obj.error().length()).getBytes(StandardCharsets.UTF_8));
        out.writeBytes(Constants.CRLF);
        out.writeBytes(obj.error().getBytes(StandardCharsets.UTF_8));
        out.writeBytes(Constants.CRLF);
    }

}
