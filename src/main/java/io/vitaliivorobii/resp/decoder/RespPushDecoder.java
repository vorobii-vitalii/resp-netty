package io.vitaliivorobii.resp.decoder;

import io.vitaliivorobii.resp.types.RespPush;

import java.util.ArrayList;

public class RespPushDecoder extends RespGenericSequenceDecoder<RespPush> {

    public RespPushDecoder(RespDecoder elementDecoder) {
        super(
                elementDecoder,
                size -> new RespPush(new ArrayList<>(size)),
                (element, arr) -> arr.elements().add(element)
        );
    }

}
