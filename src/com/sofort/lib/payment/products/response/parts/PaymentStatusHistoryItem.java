package com.sofort.lib.payment.products.response.parts;

import java.util.Date;


/**
 * The API payment status history item container.
 */
public class PaymentStatusHistoryItem {

	/** The status. */
	private PaymentStatus status;

	/** The status reason. */
	private PaymentStatusReason statusReason;

	/** The time. */
	private Date time;


	/**
	 * Gets the status.
	 * 
	 * @return the status
	 */
	public PaymentStatus getStatus() {
		return status;
	}


	/**
	 * Sets the status.
	 * 
	 * @param status
	 *            the new status
	 */
	public void setStatus(PaymentStatus status) {
		this.status = status;
	}


	/**
	 * Gets the status reason.
	 * 
	 * @return the status reason
	 */
	public PaymentStatusReason getStatusReason() {
		return statusReason;
	}


	/**
	 * Sets the status reason.
	 * 
	 * @param statusReason
	 *            the new status reason
	 */
	public void setStatusReason(PaymentStatusReason statusReason) {
		this.statusReason = statusReason;
	}


	/**
	 * Gets the time.
	 * 
	 * @return the time
	 */
	public Date getTime() {
		return time;
	}


	/**
	 * Sets the time.
	 * 
	 * @param time
	 *            the new time
	 */
	public void setTime(Date time) {
		this.time = time;
	}
}
