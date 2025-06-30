package io.vitaliivorobii.resp.encoder;

import io.vitaliivorobii.resp.TestUtils;
import io.vitaliivorobii.resp.types.RespBulkString;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RespBulkStringEncoderTest {

    RespBulkStringEncoder encoder = new RespBulkStringEncoder();

    @Test
    void encode() {
        RespBulkString respBulkError = new RespBulkString("message");
        assertThat(TestUtils.encode(encoder, respBulkError))
                .isEqualTo("$7\r\nmessage\r\n");
    }

}
