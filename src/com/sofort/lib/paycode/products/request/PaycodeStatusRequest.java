package com.sofort.lib.paycode.products.request;

import com.sofort.lib.core.products.request.SofortLibRequest;

/**
 * The API paycode status request container.
 */
public class PaycodeStatusRequest extends SofortLibRequest {

	/** The paycode. */
	private final String paycode;


	/**
	 * Instantiates a new sofort paycode status request.
	 * 
	 * @param paycode
	 *            the paycode
	 */
	public PaycodeStatusRequest(String paycode) {
		this.paycode = paycode;
	}


	/**
	 * Gets the paycode.
	 * 
	 * @return the paycode
	 */
	public String getPaycode() {
		return paycode;
	}

}