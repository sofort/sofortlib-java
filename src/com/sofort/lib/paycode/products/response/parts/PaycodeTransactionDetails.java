package com.sofort.lib.paycode.products.response.parts;

import java.util.Date;
import java.util.List;

import com.sofort.lib.core.products.response.parts.TransactionDetails;
import com.sofort.lib.paycode.products.common.PaycodeTransactionStatus;
import com.sofort.lib.paycode.products.common.PaycodeTransactionStatusReason;


/**
 * The API SOFORT Paycode transaction details container based on the
 * {@link TransactionDetails}.
 */
public class PaycodeTransactionDetails extends TransactionDetails {

	/** The status. */
	private PaycodeTransactionStatus status;

	/** The status reason. */
	private PaycodeTransactionStatusReason statusReason;

	/** The status modified. */
	private Date statusModified;

	/** The email customer. */
	private String emailCustomer;

	/** The phone customer. */
	private String phoneCustomer;

	/** The paycode code. */
	private String paycode;

	/** The status history items. */
	private List<PaycodeStatusHistoryItem> statusHistoryItems;


	/**
	 * Gets the status.
	 * 
	 * @return the status
	 */
	public PaycodeTransactionStatus getStatus() {
		return status;
	}


	/**
	 * Sets the status (read the API doc to find out the available statuses).
	 * 
	 * @param status
	 *            the new status
	 */
	public void setStatus(PaycodeTransactionStatus status) {
		this.status = status;
	}


	/**
	 * Gets the status reason (read the API doc to find out the available status
	 * reasons).
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
	 * Gets the status history items.
	 * 
	 * @return the status history items
	 */
	public List<PaycodeStatusHistoryItem> getStatusHistoryItems() {
		return statusHistoryItems;
	}


	/**
	 * Sets the status history items.
	 * 
	 * @param statusHistoryItems
	 *            the new status history items
	 */
	public void setStatusHistoryItems(List<PaycodeStatusHistoryItem> statusHistoryItems) {
		this.statusHistoryItems = statusHistoryItems;
	}


	/**
	 * Gets the status modified.
	 * 
	 * @return the status modified
	 */
	public Date getStatusModified() {
		return statusModified;
	}


	/**
	 * Sets the status modified.
	 * 
	 * @param statusModified
	 *            the new status modified
	 */
	public void setStatusModified(Date statusModified) {
		this.statusModified = statusModified;
	}


	/**
	 * Gets the email customer.
	 * 
	 * @return the email customer
	 */
	public String getEmailCustomer() {
		return emailCustomer;
	}


	/**
	 * Sets the email customer.
	 * 
	 * @param emailCustomer
	 *            the new email customer
	 */
	public void setEmailCustomer(String emailCustomer) {
		this.emailCustomer = emailCustomer;
	}


	/**
	 * Gets the phone customer.
	 * 
	 * @return the phone customer
	 */
	public String getPhoneCustomer() {
		return phoneCustomer;
	}


	/**
	 * Sets the phone customer.
	 * 
	 * @param phoneCustomer
	 *            the new phone customer
	 */
	public void setPhoneCustomer(String phoneCustomer) {
		this.phoneCustomer = phoneCustomer;
	}


	/**
	 * Gets the paycode code.
	 * 
	 * @return paycode code
	 */
	public String getPaycode() {
		return paycode;
	}


	/**
	 * Sets the paycode code.
	 * 
	 * @param paycode
	 *            paycode code
	 */
	public void setPaycode(String paycode) {
		this.paycode = paycode;
	}

}
