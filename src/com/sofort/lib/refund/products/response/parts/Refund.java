package com.sofort.lib.refund.products.response.parts;

import java.util.Date;
import java.util.List;

import com.sofort.lib.core.products.response.parts.FailureMessage;
import com.sofort.lib.refund.products.RefundBankAccount;


/**
 * The API refund container (a part of the API refund response).
 */
public class Refund {

	/** The recipient. */
	private RefundBankAccount recipient;

	/** The trans id. */
	private final String transId;

	/** The amount. */
	private final double amount;

	/** The comment. */
	private String comment;

	/** The reason1. */
	private String reason1;

	/** The reason2. */
	private String reason2;

	/** The refund creation time. */
	private Date time;

	/** The partial refund id. */
	private final String partialRefundId;

	/** The status. */
	private final String status;

	/** The errors. */
	private List<FailureMessage> errors;


	/**
	 * Instantiates a new refund.
	 * 
	 * @param transId
	 *            the trans id
	 * @param amount
	 *            the amount
	 * @param partialRefundId
	 *            the partial refund id
	 * @param status
	 *            the status
	 */
	public Refund(String transId, double amount, String partialRefundId, String status) {
		this.transId = transId;
		this.amount = amount;
		this.partialRefundId = partialRefundId;
		this.status = status;
	}


	/**
	 * Gets the recipient.
	 * 
	 * @return the recipient
	 */
	public RefundBankAccount getRecipient() {
		return recipient;
	}


	/**
	 * Sets the recipient.
	 * 
	 * @param recipient
	 *            the recipient to set
	 */
	public void setRecipient(RefundBankAccount recipient) {
		this.recipient = recipient;
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
	 * Gets the amount.
	 * 
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}


	/**
	 * Gets the comment.
	 * 
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}


	/**
	 * Sets the comment.
	 * 
	 * @param comment
	 *            the new comment
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}


	/**
	 * Gets the status.
	 * 
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}


	/**
	 * Gets the reason1.
	 * 
	 * @return the reason1
	 */
	public String getReason1() {
		return reason1;
	}


	/**
	 * Sets the reason1.
	 * 
	 * @param reason1
	 *            the new reason1
	 */
	public void setReason1(String reason1) {
		this.reason1 = reason1;
	}


	/**
	 * Gets the reason2.
	 * 
	 * @return the reason2
	 */
	public String getReason2() {
		return reason2;
	}


	/**
	 * Sets the reason2.
	 * 
	 * @param reason2
	 *            the new reason2
	 */
	public void setReason2(String reason2) {
		this.reason2 = reason2;
	}


	/**
	 * Gets the refund creation time.
	 * 
	 * @return the refund creation time
	 */
	public Date getTime() {
		return time;
	}


	/**
	 * Sets the refund creation time.
	 * 
	 * @param time
	 *            the refund creation time to set
	 */
	public void setTime(Date time) {
		this.time = time;
	}


	/**
	 * Gets the partial refund id.
	 * 
	 * @return the partial refund id
	 */
	public String getPartialRefundId() {
		return partialRefundId;
	}


	/**
	 * Gets the errors.
	 * 
	 * @return the errors
	 */
	public List<FailureMessage> getErrors() {
		return errors;
	}


	/**
	 * Sets the errors.
	 * 
	 * @param errors
	 *            the new errors
	 */
	public void setErrors(List<FailureMessage> errors) {
		this.errors = errors;
	}


	/**
	 * Checks for errors.
	 * 
	 * @return true, if successful
	 */
	public boolean hasErrors() {
		return errors != null && !errors.isEmpty();
	}
}
