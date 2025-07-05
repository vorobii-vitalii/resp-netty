package io.vitaliivorobii.resp.encoder;

import io.vitaliivorobii.resp.TestUtils;
import io.vitaliivorobii.resp.types.RespArray;
import io.vitaliivorobii.resp.types.RespDataType;
import io.vitaliivorobii.resp.types.RespSet;
import io.vitaliivorobii.resp.types.RespSimpleString;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class RespSetEncoderTest {

    RespSetEncoder setEncoder = new RespSetEncoder(new DefaultRespEncoder());

    @Test
    void encode() {
        LinkedHashSet<RespDataType> elements = new LinkedHashSet<>();
        elements.add(new RespSimpleString("hello"));
        elements.add(new RespSimpleString("world"));
        RespSet set = new RespSet(elements);
        assertThat(TestUtils.encode(setEncoder, set))
                .isEqualTo("~2\r\n+hello\r\n+world\r\n");
    }
}