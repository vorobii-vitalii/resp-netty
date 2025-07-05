package io.vitaliivorobii.resp.decoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.vitaliivorobii.resp.types.RespDataType;
import io.vitaliivorobii.resp.types.RespDecodeResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

public class ByteToRespMessageDecoder extends ByteToMessageDecoder {
    private static final Logger log = LoggerFactory.getLogger(ByteToRespMessageDecoder.class);

    private final RespDecoder respDecoder;

    public ByteToRespMessageDecoder(RespDecoder respDecoder) {
        this.respDecoder = respDecoder;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        while (true) {
            Optional<RespDecodeResult<? extends RespDataType>> decodeResult =
                    respDecoder.decode(in.slice(in.readerIndex(), in.readableBytes()));
            if (decodeResult.isPresent()) {
                var v = decodeResult.get();
                RespDataType object = v.parsedObject();
                log.debug("Parsed {} of size {}", object, v.scanedBytes());
                out.add(object);
                in.skipBytes(v.scanedBytes());
            } else {
                break;
            }
        }
    }

}
