package io.vitaliivorobii.resp.encoder;

import io.vitaliivorobii.resp.TestUtils;
import io.vitaliivorobii.resp.types.RespSimpleString;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RespSimpleStringEncoderTest {

    RespSimpleStringEncoder encoder = new RespSimpleStringEncoder();

    @Test
    void encode() {
        RespSimpleString simpleString = new RespSimpleString("message");
        assertThat(TestUtils.encode(encoder, simpleString))
                .isEqualTo("+message\r\n");
    }
}