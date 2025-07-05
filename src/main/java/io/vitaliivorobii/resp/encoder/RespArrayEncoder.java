package io.vitaliivorobii.resp.encoder;

import io.vitaliivorobii.resp.types.RespArray;
import io.vitaliivorobii.resp.types.RespDataType;

public class RespArrayEncoder extends RespAbstractSequenceEncoder<RespArray> {

    public RespArrayEncoder(RespEncoder<RespDataType> elementEncoder) {
        super('*', elementEncoder);
    }
}
