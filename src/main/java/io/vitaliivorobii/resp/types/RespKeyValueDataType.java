package io.vitaliivorobii.resp.types;

import java.util.Map;

public interface RespKeyValueDataType extends RespDataType {
    Map<RespDataType, RespDataType> getAsMap();
}
