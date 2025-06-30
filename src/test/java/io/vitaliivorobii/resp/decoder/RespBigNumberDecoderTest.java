package io.vitaliivorobii.resp.decoder;

import io.vitaliivorobii.resp.types.RespBigNumber;
import io.vitaliivorobii.resp.types.RespDataType;
import io.vitaliivorobii.resp.types.RespDecodeResult;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.Optional;

import static io.vitaliivorobii.resp.TestUtils.createByteBufFromString;
import static org.assertj.core.api.Assertions.assertThat;

class RespBigNumberDecoderTest {

    RespBigNumberDecoder respBigNumberDecoder = new RespBigNumberDecoder();

    @Test
    void decodeGivenBigNumberCanBeParsedFully() {
        Optional<RespDecodeResult<? extends RespDataType>> parsed =
                respBigNumberDecoder.decode(createByteBufFromString("(3492890328409238509324850943850943825024385\r\n("));
        assertThat(parsed).contains(
                new RespDecodeResult<>(
                        new RespBigNumber(new BigInteger("3492890328409238509324850943850943825024385")),
                        46
                ));
    }

    @Test
    void decodeGivenBigNumberCannotBeParsedFully() {
        Optional<RespDecodeResult<? extends RespDataType>> parsed =
                respBigNumberDecoder.decode(createByteBufFromString("(3492890328409238509324850943850943825024385\r"));
        assertThat(parsed).isEmpty();
    }

}