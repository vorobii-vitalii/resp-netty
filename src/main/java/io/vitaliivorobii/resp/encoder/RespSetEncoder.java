package io.vitaliivorobii.resp.encoder;

import io.vitaliivorobii.resp.types.RespDataType;
import io.vitaliivorobii.resp.types.RespDataTypeCodes;
import io.vitaliivorobii.resp.types.RespSet;

public class RespSetEncoder extends RespAbstractSequenceEncoder<RespSet> {

    public RespSetEncoder(RespEncoder<RespDataType> elementEncoder) {
        super(RespDataTypeCodes.SET, elementEncoder);
    }
}
