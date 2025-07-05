package io.vitaliivorobii.resp.encoder;

import io.vitaliivorobii.resp.types.RespAttributes;
import io.vitaliivorobii.resp.types.RespDataType;
import io.vitaliivorobii.resp.types.RespDataTypeCodes;

public class RespAttributesEncoder extends RespKeyValueObjectEncoder<RespAttributes> {

    public RespAttributesEncoder(RespEncoder<RespDataType> encoder) {
        super(RespDataTypeCodes.ATTRIBUTES, encoder);
    }
}
