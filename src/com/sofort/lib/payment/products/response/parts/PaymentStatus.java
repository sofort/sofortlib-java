package com.sofort.lib.payment.products.response.parts;

/**
 * The API payment status enum.
 */
public enum PaymentStatus {

	LOSS,
	PENDING,
	RECEIVED,
	REFUNDED,
	UNTRACEABLE;

	/**
	 * Gets the payment status for given name.
	 * 
	 * @param name
	 *            the name
	 * @return the payment status
	 */
	public static PaymentStatus get(String name) {

		if (name == null) {
			return null;
		}

		for (PaymentStatus status : values()) {
			if (status.name().equalsIgnoreCase(name)) {
				return status;
			}
		}

		throw new IllegalArgumentException("Unknown status: " + name);
	}
}
