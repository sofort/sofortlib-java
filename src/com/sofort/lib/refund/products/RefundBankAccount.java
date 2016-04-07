package com.sofort.lib.refund.products;

/**
 * The API bank account container.
 */
public class RefundBankAccount {

	/** The holder. */
	private String holder;

	/** The bank name. */
	private String bankName;

	/** The bic. */
	private String bic;

	/** The iban. */
	private String iban;


	/**
	 * Use the setter methods.
	 */
	public RefundBankAccount() {
		/* noOp */
	}


	/**
	 * Gets the holder.
	 * 
	 * @return the holder
	 */
	public String getHolder() {
		return holder;
	}


	/**
	 * Sets the holder.
	 * 
	 * @param holder
	 *            the holder
	 * @return the bank account
	 */
	public RefundBankAccount setHolder(String holder) {
		this.holder = holder;
		return this;
	}


	/**
	 * Gets the bank name. Return value might be empty, depending on which
	 * information is available.
	 * 
	 * @return the bank name
	 */
	public String getBankName() {
		return bankName;
	}


	/**
	 * Sets the bank name.
	 * 
	 * @param bankName
	 *            the bank name
	 * @return the bank account
	 */
	public RefundBankAccount setBankName(String bankName) {
		this.bankName = bankName;
		return this;
	}


	/**
	 * Gets the bic. Return value might be empty, depending on which information
	 * is available.
	 * 
	 * @return the bic
	 */
	public String getBic() {
		return bic;
	}


	/**
	 * Sets the bic.
	 * 
	 * @param bic
	 *            the bic
	 * @return the bank account
	 */
	public RefundBankAccount setBic(String bic) {
		this.bic = bic;
		return this;
	}


	/**
	 * Gets the iban. Return value might be empty, depending on which
	 * information is available.
	 * 
	 * @return the iban
	 */
	public String getIban() {
		return iban;
	}


	/**
	 * Sets the iban.
	 * 
	 * @param iban
	 *            the iban
	 * @return the bank account
	 */
	public RefundBankAccount setIban(String iban) {
		this.iban = iban;
		return this;
	}

}
