package io.vitaliivorobii.resp.encoder;

import io.vitaliivorobii.resp.types.RespDataType;
import io.vitaliivorobii.resp.types.RespDataTypeCodes;
import io.vitaliivorobii.resp.types.RespPush;

public class RespPushEncoder extends RespAbstractSequenceEncoder<RespPush> {

    public RespPushEncoder(RespEncoder<RespDataType> elementEncoder) {
        super(RespDataTypeCodes.PUSHES, elementEncoder);
    }
}
