package io.vitaliivorobii.resp.types;

import java.util.List;

public record RespArray(List<RespDataType> elements) implements RespDataType {
}
