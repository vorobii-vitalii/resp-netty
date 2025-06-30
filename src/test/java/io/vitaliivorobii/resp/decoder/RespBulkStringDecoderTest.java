package io.vitaliivorobii.resp.decoder;

import io.vitaliivorobii.resp.types.RespBulkString;
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

class RespBulkStringDecoderTest {

    RespRespBulkStringDecoder decoder = new RespRespBulkStringDecoder();

    private static Stream<Arguments> decodeGivenWholeStringCanBeParsedTestParams() {
        return Stream.of(
                Arguments.of(
                        "$5\r\nhello\r\n",
                        11,
                        "hello"
                )
        );
    }

    @ParameterizedTest
    @MethodSource("decodeGivenWholeStringCanBeParsedTestParams")
    void decodeGivenWholeStringCanBeParsed(String bufferStr, int expectedProcessedBytes, String expectedParsed) {
        Optional<RespDecodeResult<? extends RespDataType>> decodeRes =
                decoder.decode(createByteBufFromString(bufferStr));
        assertThat(decodeRes).contains(
                new RespDecodeResult<>(new RespBulkString(expectedParsed), expectedProcessedBytes)
        );
    }

    @Test
    void decodeGivenWholeStringCannotBeParsed() {
        Optional<RespDecodeResult<? extends RespDataType>> decodeRes =
                decoder.decode(createByteBufFromString("$5\r\nhello\r"));
        assertThat(decodeRes).isEmpty();
    }


}