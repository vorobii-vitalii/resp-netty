package io.vitaliivorobii.resp.decoder;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufProcessor;
import io.vitaliivorobii.resp.types.RespDataType;
import io.vitaliivorobii.resp.types.RespDecodeResult;
import io.vitaliivorobii.resp.types.RespVerbatimString;
import io.vitaliivorobii.resp.utils.Constants;
import io.vitaliivorobii.resp.utils.IntegerParser;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

public class RespVerbatimStringDecoder implements RespDecoder {
    public static final int LENGTH_START = 1;
    public static final int ENCODING_LENGTH = 3;

    // =<length>\r\n<encoding>:<data>\r\n

    @Override
    public Optional<RespDecodeResult<? extends RespDataType>> decode(ByteBuf buffer) {
        int crIndex = buffer.forEachByte(ByteBufProcessor.FIND_CR);
        if (crIndex == Constants.NOT_FOUND) {
            return Optional.empty();
        }
        int dataLength = IntegerParser.parseIntInBase10(buffer, LENGTH_START, crIndex);
        int neededBytes = crIndex + Constants.CRLF_LENGTH + ENCODING_LENGTH + 1 + dataLength + Constants.CRLF_LENGTH;
        int bytesAvailable = buffer.readableBytes();
        if (bytesAvailable < neededBytes) {
            return Optional.empty();
        }
        String encoding = buffer.toString(crIndex + Constants.CRLF_LENGTH, 3, StandardCharsets.UTF_8);
        byte[] data = new byte[dataLength];
        buffer.getBytes(
                crIndex + Constants.CRLF_LENGTH + ENCODING_LENGTH,
                data,
                0,
                dataLength
        );
        return Optional.of(
                new RespDecodeResult<>(
                        new RespVerbatimString(encoding, data),
                        neededBytes
                ));
    }
}
