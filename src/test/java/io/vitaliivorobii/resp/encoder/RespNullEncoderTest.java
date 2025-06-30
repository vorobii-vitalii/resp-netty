package io.vitaliivorobii.resp.encoder;

import io.vitaliivorobii.resp.TestUtils;
import io.vitaliivorobii.resp.types.RespNull;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class RespNullEncoderTest {

    RespNullEncoder encoder = new RespNullEncoder();

    @Test
    void encode() {
        assertThat(TestUtils.encode(encoder, new RespNull()))
                .isEqualTo("_\r\n");
    }
}