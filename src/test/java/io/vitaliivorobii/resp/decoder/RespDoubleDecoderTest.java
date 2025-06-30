package io.vitaliivorobii.resp.decoder;

import io.vitaliivorobii.resp.types.RespDataType;
import io.vitaliivorobii.resp.types.RespDecodeResult;
import io.vitaliivorobii.resp.types.RespDouble;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Optional;
import java.util.stream.Stream;

import static io.vitaliivorobii.resp.TestUtils.createByteBufFromString;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class RespDoubleDecoderTest {

    RespDoubleDecoder respDoubleDecoder = new RespDoubleDecoder();

    private static Stream<Arguments> decodeGivenEnoughBytesToDecodeTestParams() {
        return Stream.of(
                Arguments.of(
                        ",1.23\r\n",
                        7,
                        1.23
                ),
                Arguments.of(
                        ",1.23e2\r\n",
                        9,
                        123
                ),
                Arguments.of(
                        ",123e2\r\n",
                        8,
                        12300
                )
        );
    }

    @ParameterizedTest
    @MethodSource("decodeGivenEnoughBytesToDecodeTestParams")
    void decodeGivenEnoughBytesToDecode(String bufferStr, int expectedProcessedBytes, double expectedValue) {
        Optional<RespDecodeResult<? extends RespDataType>> decodeRes =
                respDoubleDecoder.decode(createByteBufFromString(bufferStr));
        assertThat(decodeRes).isNotEmpty();
        RespDecodeResult<? extends RespDataType> decodeResult = decodeRes.get();
        assertThat(decodeResult.scanedBytes()).isEqualTo(expectedProcessedBytes);
        assertThat(((RespDouble) decodeResult.parsedObject()).value())
                .isEqualTo(expectedValue, withPrecision(0.1));
    }

    @Test
    void decodeGivenNotEnoughBytesToDecode() {
        Optional<RespDecodeResult<? extends RespDataType>> decodeRes =
                respDoubleDecoder.decode(createByteBufFromString(",1.23\r"));
        assertThat(decodeRes).isEmpty();
    }
}
