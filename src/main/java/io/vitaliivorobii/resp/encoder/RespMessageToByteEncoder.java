package io.vitaliivorobii.resp.encoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.vitaliivorobii.resp.types.RespDataType;

public class RespMessageToByteEncoder extends MessageToByteEncoder<RespDataType> {
    private final RespEncoder<RespDataType> respEncoder;

    public RespMessageToByteEncoder(RespEncoder<RespDataType> respEncoder) {
        this.respEncoder = respEncoder;
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, RespDataType msg, ByteBuf out) throws Exception {
        respEncoder.encode(msg, out);
    }

}
