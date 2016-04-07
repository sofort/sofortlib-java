package com.sofort.lib.core.products.common;

/**
 * The API bank account container.
 */
public class BankAccount {

	/** The holder. */
	private String holder;

	/** The account number. */
	private String accountNumber;

	/** The bank code. */
	private String bankCode;

	/** The bank name. */
	private String bankName;

	/** The country code. */
	private String countryCode;

	/** The bic. */
	private String bic;

	/** The iban. */
	private String iban;


	/**
	 * Use the setter methods.
	 */
	public BankAccount() {
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
	public BankAccount setHolder(String holder) {
		this.holder = holder;
		return this;
	}


	/**
	 * Gets the account number. Return value might be empty, depending on which
	 * information is available.
	 * 
	 * @return the account number
	 */
	@Deprecated
	public String getAccountNumber() {
		return accountNumber;
	}


	/**
	 * Sets the account number.
	 * 
	 * @param accountNumber
	 *            the account number
	 * @return the bank account
	 */
	@Deprecated
	public BankAccount setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
		return this;
	}


	/**
	 * Gets the bank code. Return value might be empty, depending on which
	 * information is available.
	 * 
	 * @return the bank code
	 */
	@Deprecated
	public String getBankCode() {
		return bankCode;
	}


	/**
	 * Sets the bank code.
	 * 
	 * @param bankCode
	 *            the bank code
	 * @return the bank account
	 */
	@Deprecated
	public BankAccount setBankCode(String bankCode) {
		this.bankCode = bankCode;
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
	public BankAccount setBankName(String bankName) {
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
	public BankAccount setBic(String bic) {
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
	public BankAccount setIban(String iban) {
		this.iban = iban;
		return this;
	}


	/**
	 * Gets the country code.
	 * 
	 * @return the country code
	 */
	public String getCountryCode() {
		return countryCode;
	}


	/**
	 * Sets the country code.
	 * 
	 * @param countryCode
	 *            the country code
	 * @return the bank account
	 */
	public BankAccount setCountryCode(String countryCode) {
		this.countryCode = countryCode;
		return this;
	}

}
