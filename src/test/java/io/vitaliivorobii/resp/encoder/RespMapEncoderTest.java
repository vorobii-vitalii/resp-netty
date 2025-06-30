package io.vitaliivorobii.resp.encoder;

import io.vitaliivorobii.resp.TestUtils;
import io.vitaliivorobii.resp.types.RespDataType;
import io.vitaliivorobii.resp.types.RespInteger;
import io.vitaliivorobii.resp.types.RespMap;
import io.vitaliivorobii.resp.types.RespSimpleString;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class RespMapEncoderTest {

    RespMapEncoder encoder = new RespMapEncoder(new DefaultRespEncoder());

    @Test
    void encode() {
        LinkedHashMap<RespDataType, RespDataType> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put(new RespSimpleString("first"), new RespInteger(1));
        linkedHashMap.put(new RespSimpleString("second"), new RespInteger(2));
        RespMap map = new RespMap(linkedHashMap);
        assertThat(TestUtils.encode(encoder, map))
                .isEqualTo("%2\r\n+first\r\n:1\r\n+second\r\n:2\r\n");
    }
}