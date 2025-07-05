package io.vitaliivorobii.resp.encoder;

import io.vitaliivorobii.resp.types.RespAttributes;
import io.vitaliivorobii.resp.types.RespDataType;

public class RespAttributesEncoder extends RespKeyValueObjectEncoder<RespAttributes> {

    public RespAttributesEncoder(RespEncoder<RespDataType> encoder) {
        super('%', encoder);
    }
}
