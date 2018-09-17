package com.sofort.lib.paycode.products.common;

/**
 * The API paycode transaction status enum.
 */
public enum PaycodeTransactionStatus {

    LOSS,
    PENDING,
    RECEIVED,
    REFUNDED,
    UNTRACEABLE;

    /**
     * Gets the paycode transaction status for given name.
     *
     * @param name the name
     * @return the payment status
     */
    public static PaycodeTransactionStatus get(String name) {

        if (name == null) {
            return null;
        }

        for (PaycodeTransactionStatus status : values()) {
            if (status.name().equalsIgnoreCase(name)) {
                return status;
            }
        }

        throw new IllegalArgumentException("Unknown paycode transaction status: " + name);
    }
}
