package io.vitaliivorobii.resp.types;

import java.util.Set;

public record RespSet(Set<RespDataType> elements) implements RespDataType {
}
