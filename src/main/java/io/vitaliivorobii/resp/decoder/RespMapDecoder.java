package io.vitaliivorobii.resp.decoder;

import io.vitaliivorobii.resp.types.RespMap;

public class RespMapDecoder extends RespKeyValueDataTypeDecoder<RespMap> {

    public RespMapDecoder(RespDecoder elementDecoder) {
        super(elementDecoder, RespMap::new);
    }

}
