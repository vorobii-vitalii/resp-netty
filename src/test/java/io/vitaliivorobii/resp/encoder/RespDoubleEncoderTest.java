package io.vitaliivorobii.resp.encoder;

import io.vitaliivorobii.resp.TestUtils;
import io.vitaliivorobii.resp.types.RespDouble;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class RespDoubleEncoderTest {

    RespDoubleEncoder encoder = new RespDoubleEncoder();

    @Test
    void encode() {
        RespDouble respDouble = new RespDouble(24.5);
        assertThat(TestUtils.encode(encoder, respDouble))
                .isEqualTo(",24.5\r\n");
    }
}