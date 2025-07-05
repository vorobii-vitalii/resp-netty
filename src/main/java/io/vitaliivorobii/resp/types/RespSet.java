package io.vitaliivorobii.resp.types;

import java.util.Collection;
import java.util.Set;

public record RespSet(Set<RespDataType> elements) implements RespAbstractSequence {
    @Override
    public Collection<RespDataType> getElements() {
        return elements;
    }
}
