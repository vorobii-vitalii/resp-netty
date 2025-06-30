package io.vitaliivorobii.resp.decoder;

import io.vitaliivorobii.resp.types.RespBoolean;
import io.vitaliivorobii.resp.types.RespDataType;
import io.vitaliivorobii.resp.types.RespDecodeResult;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Optional;
import java.util.stream.Stream;

import static io.vitaliivorobii.resp.TestUtils.createByteBufFromString;
import static org.assertj.core.api.Assertions.assertThat;

class RespBooleanDecoderTest {

    RespBooleanDecoder respBooleanDecoder = new RespBooleanDecoder();

    private static Stream<Arguments> decodeTestParams() {
        return Stream.of(
                Arguments.of(
                        "#t\r\n#",
                        new RespBoolean(true)
                ),
                Arguments.of(
                        "#f\r\n#",
                        new RespBoolean(false)
                )
        );
    }

    @ParameterizedTest
    @MethodSource("decodeTestParams")
    void decodeGivenBooleanCanBeDecodedFully(String str, RespBoolean expected) {
        Optional<RespDecodeResult<? extends RespDataType>> actual = respBooleanDecoder.decode(createByteBufFromString(str));
        assertThat(actual).contains(
                new RespDecodeResult<>(expected, 4)
        );
    }

    @Test
    void decodeGivenBooleanCannotBeDecodedFully() {
        Optional<RespDecodeResult<? extends RespDataType>> actual =
                respBooleanDecoder.decode(createByteBufFromString("#f\r"));
        assertThat(actual).isEmpty();
    }
}
