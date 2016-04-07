package com.sofort.lib.paycode.products.response.parts;

import java.util.Date;

import com.sofort.lib.paycode.products.common.PaycodeTransactionStatus;
import com.sofort.lib.paycode.products.common.PaycodeTransactionStatusReason;


/**
 * The API payment status history item container.
 */
public class PaycodeStatusHistoryItem {

	/** The status. */
	private PaycodeTransactionStatus status;

	/** The status reason. */
	private PaycodeTransactionStatusReason statusReason;

	/** The time. */
	private Date time;


	/**
	 * Gets the status.
	 * 
	 * @return the status
	 */
	public PaycodeTransactionStatus getStatus() {
		return status;
	}


	/**
	 * Sets the status.
	 * 
	 * @param status
	 *            the new status
	 */
	public void setStatus(PaycodeTransactionStatus status) {
		this.status = status;
	}


	/**
	 * Gets the status reason.
	 * 
	 * @return the status reason
	 */
	public PaycodeTransactionStatusReason getStatusReason() {
		return statusReason;
	}


	/**
	 * Sets the status reason.
	 * 
	 * @param statusReason
	 *            the new status reason
	 */
	public void setStatusReason(PaycodeTransactionStatusReason statusReason) {
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
