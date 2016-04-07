package com.sofort.lib.billcode.products.common;

/**
 * The API billcode transaction status enum.
 */
public enum BillcodeTransactionStatus {

	LOSS,
	PENDING,
	RECEIVED,
	REFUNDED,
	UNTRACEABLE;

	/**
	 * Gets the billcode transaction status for given name.
	 * 
	 * @param name
	 *            the name
	 * @return the payment status
	 */
	public static BillcodeTransactionStatus get(String name) {

		if (name == null) {
			return null;
		}

		for (BillcodeTransactionStatus status : values()) {
			if (status.name().equalsIgnoreCase(name)) {
				return status;
			}
		}

		throw new IllegalArgumentException("Unknown billcode transaction status: " + name);
	}
}
