package io.vitaliivorobii.resp.decoder;

import io.vitaliivorobii.resp.types.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static io.vitaliivorobii.resp.TestUtils.createByteBufFromString;
import static org.assertj.core.api.Assertions.assertThat;

class RespMapDecoderTest {

    RespMapDecoder respMapDecoder = new RespMapDecoder(new DefaultRespDecoder());

    private static Stream<Arguments> decodeGivenEnoughBytesToDecodeTestParams() {
        return Stream.of(
                Arguments.of(
                        "%2\r\n+first\r\n:1\r\n+second\r\n:2\r\n",
                        29,
                        new RespMap(Map.of(
                                new RespSimpleString("first"), new RespInteger(1),
                                new RespSimpleString("second"), new RespInteger(2)
                        ))
                )
        );
    }

    private static Stream<Arguments> decodeGivenNotEnoughBytesToDecodeTestParams() {
        return Stream.of(
                Arguments.of("%2\r\n+first\r\n:1\r\n+second\r"),
                Arguments.of("\"%2\\r\\n+first\\r\\n:"),
                Arguments.of("%2\r\n+first\r\n:1\r\n+second\r\n")
        );
    }

    @ParameterizedTest
    @MethodSource("decodeGivenEnoughBytesToDecodeTestParams")
    void decodeGivenEnoughBytesToDecode(String bufferStr, int expectedProcessedBytes, RespMap expectedMap) {
        Optional<RespDecodeResult<? extends RespDataType>> actual =
                respMapDecoder.decode(createByteBufFromString(bufferStr));
        assertThat(actual).contains(
                new RespDecodeResult<>(expectedMap, expectedProcessedBytes)
        );
    }

    @ParameterizedTest
    @MethodSource("decodeGivenNotEnoughBytesToDecodeTestParams")
    void decodeGivenNotEnoughBytesToDecode(String bufferStr) {
        Optional<RespDecodeResult<? extends RespDataType>> actual =
                respMapDecoder.decode(createByteBufFromString(bufferStr));
        assertThat(actual).isEmpty();
    }
}
