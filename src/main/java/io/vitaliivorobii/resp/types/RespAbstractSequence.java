package io.vitaliivorobii.resp.types;

import java.util.Collection;

public interface RespAbstractSequence extends RespDataType {
    Collection<RespDataType> getElements();
}
