package io.vitaliivorobii.resp.encoder;

import io.vitaliivorobii.resp.TestUtils;
import io.vitaliivorobii.resp.types.RespBulkError;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class RespBulkErrorEncoderTest {

    RespBulkErrorEncoder encoder = new RespBulkErrorEncoder();

    @Test
    void encode() {
        RespBulkError respBulkError = new RespBulkError("error message");
        assertThat(TestUtils.encode(encoder, respBulkError))
                .isEqualTo("!13\r\nerror message\r\n");
    }
}
