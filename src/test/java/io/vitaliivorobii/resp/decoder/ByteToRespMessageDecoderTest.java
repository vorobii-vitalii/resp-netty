package io.vitaliivorobii.resp.decoder;

import io.netty.channel.embedded.EmbeddedChannel;
import io.vitaliivorobii.resp.TestUtils;
import io.vitaliivorobii.resp.types.RespArray;
import io.vitaliivorobii.resp.types.RespDataType;
import io.vitaliivorobii.resp.types.RespSimpleString;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ByteToRespMessageDecoderTest {

    @Test
    void decode() {
        EmbeddedChannel embeddedChannel = new EmbeddedChannel(new ByteToRespMessageDecoder(new DefaultRespDecoder()));
        embeddedChannel.writeInbound(TestUtils.createByteBufFromString("*2\r\n+hello\r\n+world\r\n"));
        RespDataType actualRespMessage = embeddedChannel.readInbound();
        assertThat(actualRespMessage).isEqualTo(new RespArray(List.of(
                new RespSimpleString("hello"),
                new RespSimpleString("world")
        )));
    }
}
