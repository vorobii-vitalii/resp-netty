package io.vitaliivorobii.resp.decoder;

import io.vitaliivorobii.resp.types.RespDataTypeCodes;

public class DefaultRespDecoder extends DelegatingRespDecoder {

    public DefaultRespDecoder() {
        this.addDecoder((byte) RespDataTypeCodes.SIMPLE_STRING, new RespSimpleStringDecoder());
        this.addDecoder((byte) RespDataTypeCodes.SIMPLE_ERROR, new RespSimpleErrorDecoder());
        this.addDecoder((byte) RespDataTypeCodes.INTEGER, new RespIntegerDecoder());
        this.addDecoder((byte) RespDataTypeCodes.BULK_STRING, new RespRespBulkStringDecoder());
        this.addDecoder((byte) RespDataTypeCodes.ARRAY, new RespArrayDecoder(this));
        this.addDecoder((byte) RespDataTypeCodes.NULL, new RespNullDecoder());
        this.addDecoder((byte) RespDataTypeCodes.BOOLEAN, new RespBooleanDecoder());
        this.addDecoder((byte) RespDataTypeCodes.DOUBLE, new RespDoubleDecoder());
        this.addDecoder((byte) RespDataTypeCodes.BIG_NUMBER, new RespBigNumberDecoder());
        this.addDecoder((byte) RespDataTypeCodes.BULK_ERROR, new RespRespBulkErrorDecoder());
        this.addDecoder((byte) RespDataTypeCodes.VERBATIM_STRING, new RespVerbatimStringDecoder());
        this.addDecoder((byte) RespDataTypeCodes.MAP, new RespMapDecoder(this));
        this.addDecoder((byte) RespDataTypeCodes.ATTRIBUTES, new RespAttributesDecoder(this));
        this.addDecoder((byte) RespDataTypeCodes.SET, new RespSetDecoder(this));
        this.addDecoder((byte) RespDataTypeCodes.PUSHES, new RespPushDecoder(this));
    }

}
