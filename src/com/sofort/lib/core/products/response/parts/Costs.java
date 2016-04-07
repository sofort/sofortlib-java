package com.sofort.lib.core.products.response.parts;

/**
 * The API costs response container.
 */
public class Costs {

	/** The fees. */
	private double fees;

	/** The currency code. */
	private String currencyCode;

	/** The exchange rate. */
	private double exchangeRate;


	/**
	 * Gets the fees.
	 * 
	 * @return the fees
	 */
	public double getFees() {
		return fees;
	}


	/**
	 * Sets the fees.
	 * 
	 * @param fees
	 *            the new fees
	 */
	public void setFees(double fees) {
		this.fees = fees;
	}


	/**
	 * Gets the currency code.
	 * 
	 * @return the currency code
	 */
	public String getCurrencyCode() {
		return currencyCode;
	}


	/**
	 * Sets the currency code.
	 * 
	 * @param currencyCode
	 *            the new currency code
	 */
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}


	/**
	 * Gets the exchange rate.
	 * 
	 * @return the exchange rate
	 */
	public double getExchangeRate() {
		return exchangeRate;
	}


	/**
	 * Sets the exchange rate.
	 * 
	 * @param exchangeRate
	 *            the new exchange rate
	 */
	public void setExchangeRate(double exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

}
