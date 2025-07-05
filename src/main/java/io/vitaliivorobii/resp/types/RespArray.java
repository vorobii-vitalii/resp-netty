package io.vitaliivorobii.resp.types;

import java.util.Collection;
import java.util.List;

public record RespArray(List<RespDataType> elements) implements RespAbstractSequence {
    @Override
    public Collection<RespDataType> getElements() {
        return elements;
    }
}
