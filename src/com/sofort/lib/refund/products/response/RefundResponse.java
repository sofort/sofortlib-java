package com.sofort.lib.refund.products.response;

import java.util.List;

import com.sofort.lib.core.products.response.SofortLibResponse;
import com.sofort.lib.core.products.response.parts.FailureMessage;
import com.sofort.lib.refund.products.RefundBankAccount;
import com.sofort.lib.refund.products.response.parts.Refund;


/**
 * The API refund response container.
 */
public class RefundResponse extends SofortLibResponse {

	/** The title. */
	private final String title;

	/** The sender. */
	private final RefundBankAccount sender;

	/** The refunds. */
	private final List<Refund> refunds;

	/** The pain. */
	private String pain;

	/** The refund errors. */
	private List<FailureMessage> errors;


	/**
	 * Instantiates a new refund response.
	 * 
	 * @param title
	 *            the title
	 * @param sender
	 *            the sender
	 * @param refunds
	 *            the refunds
	 */
	public RefundResponse(String title, RefundBankAccount sender, List<Refund> refunds) {
		this.title = title;
		this.sender = sender;
		this.refunds = refunds;
	}


	/**
	 * Gets the title.
	 * 
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}


	/**
	 * Gets the sender.
	 * 
	 * @return the sender
	 */
	public RefundBankAccount getSender() {
		return sender;
	}


	/**
	 * Gets the refunds.
	 * 
	 * @return the refunds
	 */
	public List<Refund> getRefunds() {
		return refunds;
	}


	/**
	 * Gets the plain pain value (already decoded from BASE64 format!).
	 * 
	 * @return the pain
	 */
	public String getPain() {
		return pain;
	}


	/**
	 * Sets the plain pain value.
	 * 
	 * @param pain
	 *            the new pain
	 */
	public void setPain(String pain) {
		this.pain = pain;
	}


	/**
	 * Gets the refund errors.
	 * 
	 * @return the refund errors
	 */
	public List<FailureMessage> getErrors() {
		return errors;
	}


	/**
	 * Sets the refund errors.
	 * 
	 * @param errors
	 *            the new refund errors
	 */
	public void setErrors(List<FailureMessage> errors) {
		this.errors = errors;
	}


	/**
	 * Checks for refund errors.
	 * 
	 * @return true, if refund errors are present
	 */
	public boolean hasErrors() {
		return errors != null && !errors.isEmpty();
	}

}
