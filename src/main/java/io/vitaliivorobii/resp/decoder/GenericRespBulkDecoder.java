package io.vitaliivorobii.resp.decoder;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufProcessor;
import io.vitaliivorobii.resp.types.RespDataType;
import io.vitaliivorobii.resp.types.RespDecodeResult;
import io.vitaliivorobii.resp.types.RespNullBulkString;
import io.vitaliivorobii.resp.utils.Constants;
import io.vitaliivorobii.resp.utils.IntegerParser;

import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.function.Function;

public abstract class GenericRespBulkDecoder implements RespDecoder {
    public static final int LENGTH_START = 1;

    private final Function<String, RespDataType> dataWrapper;

    public GenericRespBulkDecoder(Function<String, RespDataType> dataWrapper) {
        this.dataWrapper = dataWrapper;
    }

    @Override
    public Optional<RespDecodeResult<? extends RespDataType>> decode(ByteBuf buffer) {
        int crIndex = buffer.forEachByte(ByteBufProcessor.FIND_CR);
        if (crIndex == Constants.NOT_FOUND) {
            return Optional.empty();
        }
        int dataLength = IntegerParser.parseIntInBase10(buffer, LENGTH_START, crIndex);
        if (dataLength == Constants.NOT_FOUND) {
            // $-1\r\n
            return Optional.of(new RespDecodeResult<>(
                    new RespNullBulkString(),
                    crIndex + Constants.CRLF_LENGTH
            ));
        }
        int neededBytes = crIndex + Constants.CRLF_LENGTH + Constants.CRLF_LENGTH + dataLength;
        int bytesAvailable = buffer.readableBytes();
        if (bytesAvailable < neededBytes) {
            return Optional.empty();
        }
        String dataString = buffer.toString(crIndex + Constants.CRLF_LENGTH, dataLength, StandardCharsets.UTF_8);
        return Optional.of(new RespDecodeResult<>(
                dataWrapper.apply(dataString),
                neededBytes
        ));
    }
}
