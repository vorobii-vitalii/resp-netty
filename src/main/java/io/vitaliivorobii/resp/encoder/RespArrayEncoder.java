package io.vitaliivorobii.resp.encoder;

import io.vitaliivorobii.resp.types.RespArray;
import io.vitaliivorobii.resp.types.RespDataType;
import io.vitaliivorobii.resp.types.RespDataTypeCodes;

public class RespArrayEncoder extends RespAbstractSequenceEncoder<RespArray> {

    public RespArrayEncoder(RespEncoder<RespDataType> elementEncoder) {
        super(RespDataTypeCodes.ARRAY, elementEncoder);
    }
}
