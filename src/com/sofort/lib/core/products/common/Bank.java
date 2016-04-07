package com.sofort.lib.core.products.common;

/**
 * The API sender bank data container.
 */
public class Bank {

	/** The bank code. */
	private String bankCode;

	/** The bic. */
	private String bic;

	/** The country code. */
	private String countryCode;


	/**
	 * Use the setter methods.
	 */
	public Bank() {
		/* noOp */
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
	 * @return the sender bank
	 */
	@Deprecated
	public Bank setBankCode(String bankCode) {
		this.bankCode = bankCode;
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
	 * @return the sender bank
	 */
	public Bank setBic(String bic) {
		this.bic = bic;
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
	 * @return the sender bank
	 */
	public Bank setCountryCode(String countryCode) {
		this.countryCode = countryCode;
		return this;
	}

}
