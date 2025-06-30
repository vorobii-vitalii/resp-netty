package io.vitaliivorobii.resp.encoder;

import io.vitaliivorobii.resp.TestUtils;
import io.vitaliivorobii.resp.types.RespArray;
import io.vitaliivorobii.resp.types.RespSimpleString;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RespArrayEncoderTest {

    RespArrayEncoder arrayEncoder = new RespArrayEncoder(new DefaultRespEncoder());

    @Test
    void encode() {
        RespArray array = new RespArray(List.of(
                new RespSimpleString("hello"),
                new RespSimpleString("world")
        ));
        assertThat(TestUtils.encode(arrayEncoder, array))
                .isEqualTo("*2\r\n+hello\r\n+world\r\n");
    }
}