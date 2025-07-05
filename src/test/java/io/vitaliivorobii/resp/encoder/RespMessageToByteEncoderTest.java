package io.vitaliivorobii.resp.encoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.embedded.EmbeddedChannel;
import io.vitaliivorobii.resp.types.RespArray;
import io.vitaliivorobii.resp.types.RespSimpleString;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RespMessageToByteEncoderTest {

    @Test
    void encode() {
        RespMessageToByteEncoder encoder = new RespMessageToByteEncoder(new DefaultRespEncoder());
        EmbeddedChannel embeddedChannel = new EmbeddedChannel(encoder);
        embeddedChannel.writeOutbound(new RespArray(List.of(
                new RespSimpleString("hello"),
                new RespSimpleString("world")
        )));
        ByteBuf byteMessage = embeddedChannel.readOutbound();
        assertThat(byteMessage.toString(StandardCharsets.UTF_8))
                .isEqualTo("*2\r\n+hello\r\n+world\r\n");
    }
}