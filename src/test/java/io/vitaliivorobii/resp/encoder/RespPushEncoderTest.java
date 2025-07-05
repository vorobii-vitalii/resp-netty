package io.vitaliivorobii.resp.encoder;

import io.vitaliivorobii.resp.TestUtils;
import io.vitaliivorobii.resp.types.RespDataType;
import io.vitaliivorobii.resp.types.RespPush;
import io.vitaliivorobii.resp.types.RespSimpleString;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RespPushEncoderTest {

    RespPushEncoder pushEncoder = new RespPushEncoder(new DefaultRespEncoder());

    @Test
    void encode() {
        List<RespDataType> elements = new ArrayList<>();
        elements.add(new RespSimpleString("hello"));
        elements.add(new RespSimpleString("world"));
        RespPush respPush = new RespPush(elements);
        assertThat(TestUtils.encode(pushEncoder, respPush))
                .isEqualTo(">2\r\n+hello\r\n+world\r\n");
    }
}