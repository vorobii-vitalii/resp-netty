package io.vitaliivorobii.resp.decoder;

import io.vitaliivorobii.resp.types.RespBulkError;

public class RespRespBulkErrorDecoder extends GenericRespBulkDecoder {
    public RespRespBulkErrorDecoder() {
        super(RespBulkError::new);
    }
}
