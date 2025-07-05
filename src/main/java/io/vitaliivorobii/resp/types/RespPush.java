package io.vitaliivorobii.resp.types;

import java.util.List;

public record RespPush(List<RespDataType> elements) implements RespDataType {
}
