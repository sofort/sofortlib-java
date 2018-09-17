package com.sofort.lib.billcode.products.common;

/**
 * The API payment status reason enum.
 */
public enum BillcodeTransactionStatusReason {

    NOT_CREDITED,
    NOT_CREDITED_YET,
    CREDITED,
    PARTIALLY_CREDITED,
    OVERPAYMENT,
    COMPENSATION,
    REFUNDED,
    SOFORT_BANK_ACCOUNT_NEEDED;

    /**
     * Gets the billcode transaction status reason for given name.
     *
     * @param name the name
     * @return the payment status reason
     */
    public static BillcodeTransactionStatusReason get(String name) {

        if (name == null) {
            return null;
        }

        for (BillcodeTransactionStatusReason statusReason : values()) {
            if (statusReason.name().equalsIgnoreCase(name)) {
                return statusReason;
            }
        }

        throw new IllegalArgumentException("Unknown billcode transaction status reason: " + name);
    }
}
