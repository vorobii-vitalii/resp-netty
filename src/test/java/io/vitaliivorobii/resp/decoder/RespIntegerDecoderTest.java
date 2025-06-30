package io.vitaliivorobii.resp.decoder;

import io.vitaliivorobii.resp.types.RespDataType;
import io.vitaliivorobii.resp.types.RespDecodeResult;
import io.vitaliivorobii.resp.types.RespInteger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Optional;
import java.util.stream.Stream;

import static io.vitaliivorobii.resp.TestUtils.createByteBufFromString;
import static org.assertj.core.api.Assertions.assertThat;

class RespIntegerDecoderTest {

    RespIntegerDecoder respIntegerDecoder = new RespIntegerDecoder();

    private static Stream<Arguments> decodeGivenEnoughBytesToDecodeTestParams() {
        return Stream.of(
                Arguments.of(
                        ":123\r\n",
                        6,
                        123
                ),
                Arguments.of(
                        ":-123\r\n",
                        7,
                        -123
                )
        );
    }

    @ParameterizedTest
    @MethodSource("decodeGivenEnoughBytesToDecodeTestParams")
    void decodeGivenEnoughBytesToDecode(String bufferStr, int processedBytes, int decodedValue) {
        Optional<RespDecodeResult<? extends RespDataType>> actual =
                respIntegerDecoder.decode(createByteBufFromString(bufferStr));
        assertThat(actual).contains(
                new RespDecodeResult<>(new RespInteger(decodedValue), processedBytes)
        );
    }

    @Test
    void decodeGivenNotEnoughBytesToDecode() {
        Optional<RespDecodeResult<? extends RespDataType>> actual =
                respIntegerDecoder.decode(createByteBufFromString(":-123\r"));
        assertThat(actual).isEmpty();
    }

}