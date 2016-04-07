package com.sofort.lib.core.products.response;

import java.util.Date;


/**
 * The API status notification response.
 */
public class SofortTransactionStatusNotification extends SofortLibResponse {

	/** The trans id. */
	private final String transId;

	/** The time. */
	private final Date time;


	/**
	 * Instantiates a new sofort transaction status notification.
	 * 
	 * @param transId
	 *            the trans id
	 * @param time
	 *            the time
	 */
	public SofortTransactionStatusNotification(String transId, Date time) {
		this.transId = transId;
		this.time = time;
	}


	/**
	 * Gets the trans id.
	 * 
	 * @return the trans id
	 */
	public String getTransId() {
		return transId;
	}


	/**
	 * Gets the time.
	 * 
	 * @return the time
	 */
	public Date getTime() {
		return time;
	}

}
