package io.vitaliivorobii.resp.types;

public class RespDataTypeCodes {

    private RespDataTypeCodes() {
        // Utility classes should not be instantiated
    }

    // RESP2 Simple Types
    /** Represents a Simple String, prefixed by '+'. */
    public static final char SIMPLE_STRING = '+';
    /** Represents a Simple Error, prefixed by '-'. */
    public static final char SIMPLE_ERROR = '-';
    /** Represents an Integer, prefixed by ':'. */
    public static final char INTEGER = ':';

    // RESP2 Aggregate Type
    /** Represents a Bulk String, prefixed by '$'. */
    public static final char BULK_STRING = '$';
    /** Represents an Array, prefixed by '*'. */
    public static final char ARRAY = '*';

    // RESP3 Simple Types
    /** Represents a Null, prefixed by '_'. */
    public static final char NULL = '_';
    /** Represents a Boolean, prefixed by '#'. */
    public static final char BOOLEAN = '#';
    /** Represents a Double, prefixed by ','. */
    public static final char DOUBLE = ',';
    /** Represents a Big Number, prefixed by '('. */
    public static final char BIG_NUMBER = '(';

    // RESP3 Aggregate Types
    /** Represents a Bulk Error, prefixed by '!'. */
    public static final char BULK_ERROR = '!';
    /** Represents a Verbatim String, prefixed by '='. */
    public static final char VERBATIM_STRING = '=';
    /** Represents a Map, prefixed by '%'. */
    public static final char MAP = '%';
    /** Represents Attributes, prefixed by '|'. */
    public static final char ATTRIBUTES = '|';
    /** Represents a Set, prefixed by '~'. */
    public static final char SET = '~';
    /** Represents Pushes, prefixed by '>'. */
    public static final char PUSHES = '>';

}

