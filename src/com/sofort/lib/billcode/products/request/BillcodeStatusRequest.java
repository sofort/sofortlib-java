package com.sofort.lib.billcode.products.request;

import com.sofort.lib.core.products.request.SofortLibRequest;

/**
 * The API billcode status request container.
 */
public class BillcodeStatusRequest extends SofortLibRequest {

	/** The billcode. */
	private final String billcode;


	/**
	 * Instantiates a new sofort billcode status request.
	 * 
	 * @param billcode
	 *            the billcode
	 */
	public BillcodeStatusRequest(String billcode) {
		this.billcode = billcode;
	}


	/**
	 * Gets the billcode.
	 * 
	 * @return the billcode
	 */
	public String getBillcode() {
		return billcode;
	}

}