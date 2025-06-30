package io.vitaliivorobii.resp.decoder;

import io.vitaliivorobii.resp.types.RespBulkString;

// $<length>\r\n<data>\r\n
public class RespRespBulkStringDecoder extends GenericRespBulkDecoder {
    public RespRespBulkStringDecoder() {
        super(RespBulkString::new);
    }
}
