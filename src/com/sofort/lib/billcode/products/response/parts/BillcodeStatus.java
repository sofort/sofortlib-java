package com.sofort.lib.billcode.products.response.parts;

public enum BillcodeStatus {

	OPEN, USED, EXPIRED;

	/**
	 * Gets the billcode status for given name.
	 * 
	 * @param name
	 *            the name
	 * @return the payment status
	 */
	public static BillcodeStatus get(String name) {

		if (name == null) {
			return null;
		}

		for (BillcodeStatus status : values()) {
			if (status.name().equalsIgnoreCase(name)) {
				return status;
			}
		}

		throw new IllegalArgumentException("Unknown billcode status: " + name);
	}

}
