package io.vitaliivorobii.resp.types;

import java.math.BigInteger;

public record RespBigNumber(BigInteger value) implements RespDataType {
}
