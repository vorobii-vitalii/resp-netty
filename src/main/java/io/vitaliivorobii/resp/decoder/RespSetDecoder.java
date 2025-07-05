package io.vitaliivorobii.resp.decoder;

import io.vitaliivorobii.resp.types.RespSet;

import java.util.HashSet;

public class RespSetDecoder extends RespGenericSequenceDecoder<RespSet> {

    public RespSetDecoder(RespDecoder elementDecoder) {
        super(
                elementDecoder,
                size -> new RespSet(new HashSet<>(size)),
                (element, arr) -> arr.elements().add(element)
        );

    }
}
