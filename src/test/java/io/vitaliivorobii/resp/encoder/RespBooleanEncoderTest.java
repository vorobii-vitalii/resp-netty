package io.vitaliivorobii.resp.encoder;

import io.vitaliivorobii.resp.TestUtils;
import io.vitaliivorobii.resp.types.RespBoolean;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class RespBooleanEncoderTest {

    RespBooleanEncoder encoder = new RespBooleanEncoder();

    private static Stream<Arguments> encodeTestParameters() {
        return Stream.of(
                Arguments.of(true, "#t\r\n"),
                Arguments.of(false, "#f\r\n")
        );
    }

    @ParameterizedTest
    @MethodSource("encodeTestParameters")
    void encode(boolean value, String expected) {
        RespBoolean bigNumber = new RespBoolean(value);
        assertThat(TestUtils.encode(encoder, bigNumber))
                .isEqualTo(expected);
    }
}