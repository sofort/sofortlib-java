package com.sofort.lib.core.products.response.parts;

/**
 * The failure message container.
 */
public class FailureMessage {

    /**
     * The code.
     */
    private final String code;

    /**
     * The message.
     */
    private final String message;

    /**
     * The field.
     */
    private final String field;


    /**
     * Defines a {@link FailureMessage} object with a code, message and field.
     *
     * @param code    the failure code
     * @param message the failure message
     * @param field   the field name
     */
    public FailureMessage(String code, String message, String field) {
        this.code = code;
        this.message = message;
        this.field = field;
    }


    /**
     * Returns the failure code.
     *
     * @return the failure code
     */
    public String getCode() {
        return code;
    }


    /**
     * Returns the failure message.
     *
     * @return the failure message
     */
    public String getMessage() {
        return message;
    }


    /**
     * Return the field name failure happened in.
     *
     * @return the field name failure happened in
     */
    public String getField() {
        return field;
    }


    @Override
    public String toString() {
        return "code:" + code + " / message:" + message + " / field: " + field;
    }

}
