package io.vitaliivorobii.resp.types;

import java.util.Map;

public record RespAttributes(Map<RespDataType, RespDataType> map) implements RespKeyValueDataType {
    @Override
    public Map<RespDataType, RespDataType> getAsMap() {
        return map;
    }
}
