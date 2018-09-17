package com.sofort.lib.paycode.products.common;

/**
 * The API payment status reason enum.
 */
public enum PaycodeTransactionStatusReason {

    NOT_CREDITED,
    NOT_CREDITED_YET,
    CREDITED,
    PARTIALLY_CREDITED,
    REFUNDED,
    SOFORT_BANK_ACCOUNT_NEEDED,
    OVERPAYMENT,
    COMPENSATION;

    /**
     * Gets the paycode transaction status reason for given name.
     *
     * @param name the name
     * @return the payment status reason
     */
    public static PaycodeTransactionStatusReason get(String name) {

        if (name == null) {
            return null;
        }

        for (PaycodeTransactionStatusReason statusReason : values()) {
            if (statusReason.name().equalsIgnoreCase(name)) {
                return statusReason;
            }
        }

        throw new IllegalArgumentException("Unknown paycode transaction status reason: " + name);
    }
}
