package io.vitaliivorobii.resp.decoder;

import io.vitaliivorobii.resp.types.RespArray;

import java.util.ArrayList;

public class RespArrayDecoder extends RespGenericSequenceDecoder<RespArray> {

    public RespArrayDecoder(RespDecoder elementDecoder) {
        super(
                elementDecoder,
                size -> new RespArray(new ArrayList<>(size)),
                (element, arr) -> arr.elements().add(element)
        );
    }

}
