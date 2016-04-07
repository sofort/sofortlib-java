package com.sofort.lib.paycode.products.response.parts;

public enum PaycodeStatus {

	OPEN, USED, EXPIRED;

	/**
	 * Gets the paycode status for given name.
	 * 
	 * @param name
	 *            the name
	 * @return the payment status
	 */
	public static PaycodeStatus get(String name) {

		if (name == null) {
			return null;
		}

		for (PaycodeStatus status : values()) {
			if (status.name().equalsIgnoreCase(name)) {
				return status;
			}
		}

		throw new IllegalArgumentException("Unknown paycode status: " + name);
	}

}
