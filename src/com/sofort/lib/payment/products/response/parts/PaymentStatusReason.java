package com.sofort.lib.payment.products.response.parts;

/**
 * The API payment status reason enum.
 */
public enum PaymentStatusReason {

	NOT_CREDITED,
	NOT_CREDITED_YET,
	CREDITED,
	PARTIALLY_CREDITED,
	OVERPAYMENT,
	COMPENSATION,
	REFUNDED,
	SOFORT_BANK_ACCOUNT_NEEDED;

	/**
	 * Gets the payment status reason for given name.
	 * 
	 * @param name
	 *            the name
	 * @return the payment status reason
	 */
	public static PaymentStatusReason get(String name) {

		if (name == null) {
			return null;
		}

		for (PaymentStatusReason statusReason : values()) {
			if (statusReason.name().equalsIgnoreCase(name)) {
				return statusReason;
			}
		}

		throw new IllegalArgumentException("Unknown status reason: " + name);
	}
}
