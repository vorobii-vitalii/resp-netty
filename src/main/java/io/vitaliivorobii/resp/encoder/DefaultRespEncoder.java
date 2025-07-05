package io.vitaliivorobii.resp.encoder;

import io.vitaliivorobii.resp.types.*;

public class DefaultRespEncoder extends DelegatingRespEncoder {

    public DefaultRespEncoder() {
        addEncoder(RespSimpleString.class, new RespSimpleStringEncoder());
        addEncoder(RespSimpleError.class, new RespSimpleErrorEncoder());
        addEncoder(RespInteger.class, new RespIntegerEncoder());
        addEncoder(RespBulkString.class, new RespBulkStringEncoder());
        addEncoder(RespArray.class, new RespArrayEncoder(this));
        addEncoder(RespNullArray.class, new RespNullArrayEncoder());
        addEncoder(RespNull.class, new RespNullEncoder());
        addEncoder(RespBoolean.class, new RespBooleanEncoder());
        addEncoder(RespDouble.class, new RespDoubleEncoder());
        addEncoder(RespBigNumber.class, new RespBigNumberEncoder());
        addEncoder(RespBulkError.class, new RespBulkErrorEncoder());
        addEncoder(RespVerbatimString.class, new RespVerbatimStringEncoder());
        addEncoder(RespMap.class, new RespMapEncoder(this));
        addEncoder(RespAttributes.class, new RespAttributesEncoder(this));
        addEncoder(RespSet.class, new RespSetEncoder(this));
        addEncoder(RespPush.class, new RespPushEncoder(this));
    }


}
