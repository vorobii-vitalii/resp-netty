package io.vitaliivorobii.resp.types;

public record RespDecodeResult<T>(T parsedObject, int scanedBytes) {
}
