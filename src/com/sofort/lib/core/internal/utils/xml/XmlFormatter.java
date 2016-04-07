package com.sofort.lib.core.internal.utils.xml;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


/**
 * A class helps to convert the Java Objects to the proper XML text and vice
 * versa.
 */
public class XmlFormatter {

	/** The Constant DATE_FORMAT. */
	public static final Locale DATE_LOCALE = Locale.GERMANY;

	/** The Constant DATE_FORMAT. */
	public static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ";

	/** The Constant SHORT_DATE_FORMAT. */
	public static final String SHORT_DATE_FORMAT = "yyyy-MM-dd";


	/**
	 * Formats given date to the conform XML text format.
	 * 
	 * @param date
	 *            the date
	 * @return the string
	 */
	static String format(Date date) {
		String formatted = format(date, DATE_FORMAT);

		return formatted != null ? formatted.replaceFirst("(\\d{2})$", ":$1") : null;
	}


	/**
	 * Formats given date to the given text format.
	 * 
	 * @param date
	 *            the date
	 * @return the string
	 */
	static String format(Date date, String format) {
		if (date == null) {
			return null;
		}

		return new SimpleDateFormat(format, DATE_LOCALE).format(date);
	}


	/**
	 * Formats given number to the conform XML text format.
	 * 
	 * @param number
	 *            the number
	 * @return the string
	 */
	static String format(Number number) {
		return format(number, null);
	}


	/**
	 * Formats given number to the conform XML text format.
	 * 
	 * @param number
	 *            the number
	 * @param invalidValue
	 *            return null if given number equals to the given invalid value.
	 * @return the string
	 */
	static String format(Number number, Number invalidValue) {

		if (number == null || number.equals(invalidValue)) {
			return null;
		}

		return number.toString();
	}


	/**
	 * Formats given boolean to the conform XML text format.
	 * 
	 * @param b
	 *            the b
	 * @return the string
	 */
	static String format(Boolean b) {
		if (b == null) {
			return null;
		}

		return b.booleanValue() ? "1" : "0";
	}


	/**
	 * Parses the date text. Return the backup value if parsing failed.
	 * 
	 * @param date
	 *            the date
	 * @param backup
	 *            the backup
	 * @return the date
	 */
	static Date parseDate(String date, Date backup) {
		if (date == null) {
			return backup;
		}

		try {
			/* fix for SDF */
			date = date.trim().replaceFirst(":(\\d{2})$", "$1");

			return new SimpleDateFormat(DATE_FORMAT, DATE_LOCALE).parse(date);
		} catch (Exception e) {
			return backup;
		}
	}


	/**
	 * Parses the short date text. Return the backup value if parsing failed.
	 * 
	 * @param date
	 *            the date
	 * @param backup
	 *            the backup
	 * @return the date
	 */
	static Date parseShortDate(String date, Date backup) {
		if (date == null) {
			return backup;
		}

		try {
			return new SimpleDateFormat(SHORT_DATE_FORMAT, DATE_LOCALE).parse(date);
		} catch (Exception e) {
			return backup;
		}

	}


	/**
	 * Parses the number text. Return the backup value if parsing failed.
	 * 
	 * @param number
	 *            the number
	 * @param backup
	 *            the backup
	 * @return the double
	 */
	static double parseDouble(String number, double backup) {
		if (number == null) {
			return backup;
		}

		try {
			return Double.parseDouble(number);
		} catch (Exception e) {
			return backup;
		}
	}


	/**
	 * Parses the long text. Return the backup value if parsing failed.
	 * 
	 * @param number
	 *            the number
	 * @param backup
	 *            the backup
	 * @return the long
	 */
	static long parseLong(String number, long backup) {
		if (number == null) {
			return backup;
		}

		try {
			return Long.parseLong(number.replaceFirst("^\\+\\s*", ""));
		} catch (Exception e) {
			return backup;
		}
	}


	/**
	 * Parses the integer text. Return the backup value if parsing failed.
	 * 
	 * @param number
	 *            the number
	 * @param backup
	 *            the backup
	 * @return the int
	 */
	static int parseInt(String number, int backup) {
		if (number == null) {
			return backup;
		}

		try {
			return Integer.parseInt(number.replaceFirst("^\\+\\s*", ""));
		} catch (Exception e) {
			return backup;
		}
	}


	/**
	 * Parses the boolean text. Return the backup value if parsing failed.
	 * 
	 * @param bool
	 *            the bool
	 * @param backup
	 *            the backup
	 * @return true, if successful
	 */
	static boolean parseBoolean(String bool, boolean backup) {
		if (bool == null) {
			return backup;
		}

		final String s = bool.trim().toLowerCase();

		if (s.isEmpty()) {
			return backup;
		}

		if (s.equals("true") || s.equals("1") || s.equals("yes") || s.equals("on")) {
			return true;
		}

		if (s.equals("false") || s.equals("0") || s.equals("no") || s.equals("off")) {
			return false;
		}

		return backup;
	}

}
