package com.sofort.lib.refund.products.request.parts;

/**
 * The API refund container (part of the API refund request).
 */
public class Refund {

	/** The trans id. */
	private final String transId;

	/** The amount. */
	private final Double amount;

	/** The comment. */
	private String comment;

	/** The reason1. */
	private String reason1;

	/** The reason2. */
	private String reason2;

	/** The partial refund id. */
	private String partialRefundId;


	/**
	 * Instantiates a new refund.
	 * 
	 * @param transId
	 *            the trans id
	 * @param amount
	 *            the amount
	 */
	public Refund(String transId, Double amount) {
		this.transId = transId;
		this.amount = amount;
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
	public Double getAmount() {
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
	 *            the comment
	 * @return the refund
	 */
	public Refund setComment(String comment) {
		this.comment = comment;
		return this;
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
	 *            the reason1
	 * @return the refund
	 */
	public Refund setReason1(String reason1) {
		this.reason1 = reason1;
		return this;
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
	 *            the reason2
	 * @return the refund
	 */
	public Refund setReason2(String reason2) {
		this.reason2 = reason2;
		return this;
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
	 * Sets the partial refund id.
	 * 
	 * @param partialRefundId
	 *            the partial refund id
	 * @return the refund
	 */
	public Refund setPartialRefundId(String partialRefundId) {
		this.partialRefundId = partialRefundId;
		return this;
	}

}
