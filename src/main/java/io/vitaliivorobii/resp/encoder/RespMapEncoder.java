package io.vitaliivorobii.resp.encoder;

import io.vitaliivorobii.resp.types.RespDataType;
import io.vitaliivorobii.resp.types.RespDataTypeCodes;
import io.vitaliivorobii.resp.types.RespMap;

public class RespMapEncoder extends RespKeyValueObjectEncoder<RespMap> {

    public RespMapEncoder(RespEncoder<RespDataType> encoder) {
        super(RespDataTypeCodes.MAP, encoder);
    }
}
