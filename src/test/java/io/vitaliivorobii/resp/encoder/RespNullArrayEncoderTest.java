package io.vitaliivorobii.resp.encoder;

import io.vitaliivorobii.resp.TestUtils;
import io.vitaliivorobii.resp.types.RespNullArray;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class RespNullArrayEncoderTest {

    RespNullArrayEncoder encoder = new RespNullArrayEncoder();

    @Test
    void encode() {
        assertThat(TestUtils.encode(encoder, new RespNullArray()))
                .isEqualTo("*-1\r\n");
    }
}