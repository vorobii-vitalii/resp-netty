package io.vitaliivorobii.resp.encoder;

import io.vitaliivorobii.resp.TestUtils;
import io.vitaliivorobii.resp.types.RespSimpleError;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RespSimpleErrorEncoderTest {

    RespSimpleErrorEncoder encoder = new RespSimpleErrorEncoder();

    @Test
    void encode() {
        RespSimpleError simpleError = new RespSimpleError("error");
        assertThat(TestUtils.encode(encoder, simpleError))
                .isEqualTo("-error\r\n");
    }
}