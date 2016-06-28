package com.sofort.lib.core.internal.utils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.Locale;


/**
 * Number conversion utilities.
 */
public class NumberUtilities {

	private final DecimalFormat amountFormat;


	public NumberUtilities() {
		amountFormat = new DecimalFormat("###0.00", new DecimalFormatSymbols(Locale.US));
	}


	/**
	 * Parses an amount to a double. Expected format x.xx
	 * 
	 * @param source
	 *            amount text formatted as x.xx
	 * @return amount as double or NaN on wrong number format
	 */
	public double parseAmount(final String source) {
		try {
			return amountFormat.parse(source).doubleValue();
		} catch (ParseException e) {
			return Double.NaN;
		}
	}


	/**
	 * Formats an amount to the text with 2 decimal places and at least one
	 * leading digit and a point as a decimal separator.
	 * 
	 * @param amount
	 *            the amount
	 * @return amount formatted as x.xx.
	 */
	public String formatAmount(final double amount) {
		return amountFormat.format(amount);
	}

}
