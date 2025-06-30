package io.vitaliivorobii.resp.types;

public record RespVerbatimString(String encoding, byte[] data) implements RespDataType {
}
