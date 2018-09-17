package com.sofort.lib.core.internal.net;

/**
 * Connection Exception.
 */
public class ConnectionException extends RuntimeException {

    private static final long serialVersionUID = -487912512040109424L;


    /**
     * @param message exception message
     */
    public ConnectionException(String message) {
        super(message);
    }


    /**
     * @param cause exception cause
     */
    public ConnectionException(Throwable cause) {
        super(cause);
    }

}
