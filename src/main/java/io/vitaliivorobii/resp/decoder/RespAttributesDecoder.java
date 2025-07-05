package io.vitaliivorobii.resp.decoder;

import io.vitaliivorobii.resp.types.RespAttributes;

public class RespAttributesDecoder extends RespKeyValueDataTypeDecoder<RespAttributes> {

    public RespAttributesDecoder(RespDecoder elementDecoder) {
        super(elementDecoder, RespAttributes::new);
    }

}
