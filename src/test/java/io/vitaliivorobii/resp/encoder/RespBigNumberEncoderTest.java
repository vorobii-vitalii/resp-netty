package io.vitaliivorobii.resp.encoder;

import io.vitaliivorobii.resp.TestUtils;
import io.vitaliivorobii.resp.types.RespBigNumber;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.assertj.core.api.Assertions.assertThat;

class RespBigNumberEncoderTest {

    RespBigNumberEncoder encoder = new RespBigNumberEncoder();

    @Test
    void encode() {
        RespBigNumber bigNumber = new RespBigNumber(BigInteger.valueOf(99999929));
        assertThat(TestUtils.encode(encoder, bigNumber))
                .isEqualTo("(99999929\r\n");
    }
}