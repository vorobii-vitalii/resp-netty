package io.vitaliivorobii.resp.types;

import java.util.Map;

public record RespMap(Map<RespDataType, RespDataType> map) implements RespDataType {
}
