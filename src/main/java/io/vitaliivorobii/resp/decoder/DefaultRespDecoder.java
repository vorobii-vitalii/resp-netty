package io.vitaliivorobii.resp.decoder;

public class DefaultRespDecoder extends DelegatingRespDecoder {

    public DefaultRespDecoder() {
        this.addDecoder((byte) '+', new RespSimpleStringDecoder());
        this.addDecoder((byte) '-', new RespSimpleErrorDecoder());
        this.addDecoder((byte) ':', new RespIntegerDecoder());
        this.addDecoder((byte) '$', new RespRespBulkStringDecoder());
        this.addDecoder((byte) '*', new RespArrayDecoder(this));
        this.addDecoder((byte) '_', new RespNullDecoder());
        this.addDecoder((byte) '#', new RespBooleanDecoder());
        this.addDecoder((byte) ',', new RespDoubleDecoder());
        this.addDecoder((byte) '(', new RespBigNumberDecoder());
        this.addDecoder((byte) '!', new RespRespBulkErrorDecoder());
        this.addDecoder((byte) '=', new RespVerbatimStringDecoder());
        this.addDecoder((byte) '%', new RespMapDecoder(this));
        // TODO:
//        Attributes 	RESP3 	Aggregate 	`
//        Sets 	RESP3 	Aggregate 	~
//        Pushes 	RESP3 	Aggregate 	>
    }

}
