package io.vitaliivorobii.resp.encoder;

import io.vitaliivorobii.resp.TestUtils;
import io.vitaliivorobii.resp.types.RespInteger;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class RespIntegerEncoderTest {

    RespIntegerEncoder encoder = new RespIntegerEncoder();

    private static Stream<Arguments> encodeTestParameters() {
        return Stream.of(
                Arguments.of(123, ":123\r\n"),
                Arguments.of(-123, ":-123\r\n")
        );
    }

    @ParameterizedTest
    @MethodSource("encodeTestParameters")
    void encode(int value, String expected) {
        RespInteger respInteger = new RespInteger(value);
        assertThat(TestUtils.encode(encoder, respInteger))
                .isEqualTo(expected);
    }
}