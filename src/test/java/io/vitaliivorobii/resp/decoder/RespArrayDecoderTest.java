package io.vitaliivorobii.resp.decoder;

import io.vitaliivorobii.resp.types.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static io.vitaliivorobii.resp.TestUtils.createByteBufFromString;
import static org.assertj.core.api.Assertions.assertThat;

class RespArrayDecoderTest {

    RespArrayDecoder decoder = new RespArrayDecoder(new DefaultRespDecoder());

    private static Stream<Arguments> decodeGivenEnoughBytesToDecodeTestParams() {
        return Stream.of(
                Arguments.of(
                        "*2\r\n$5\r\nhello\r\n$5\r\nworld\r\n",
                        26,
                        new RespArray(List.of(
                                new RespBulkString("hello"),
                                new RespBulkString("world")
                        ))
                ),
                Arguments.of(
                        "*0\r\n",
                        4,
                        new RespArray(List.of())
                ),
                Arguments.of(
                        "*3\r\n$5\r\nhello\r\n$5\r\nworld\r\n:123\r\n",
                        32,
                        new RespArray(List.of(
                                new RespBulkString("hello"),
                                new RespBulkString("world"),
                                new RespInteger(123)
                        ))
                )
        );
    }

    private static Stream<Arguments> decodeGivenNotEnoughBytesToDecodeTestParams() {
        return Stream.of(
                Arguments.of("*2\r\n$5\r\nhello\r\n$5\r\nworld"),
                Arguments.of("*0\r")
        );
    }

    @ParameterizedTest
    @MethodSource("decodeGivenEnoughBytesToDecodeTestParams")
    void decodeGivenEnoughBytesToDecode(String bufferStr, int expectedProcessedBytes, RespArray expectedArray) {
        Optional<RespDecodeResult<? extends RespDataType>> actual = decoder.decode(createByteBufFromString(bufferStr));
        assertThat(actual).contains(
                new RespDecodeResult<>(expectedArray, expectedProcessedBytes)
        );
    }

    @ParameterizedTest
    @MethodSource("decodeGivenNotEnoughBytesToDecodeTestParams")
    void decodeGivenNotEnoughBytesToDecode(String bufferStr) {
        Optional<RespDecodeResult<? extends RespDataType>> actual = decoder.decode(createByteBufFromString(bufferStr));
        assertThat(actual).isEmpty();
    }
}
