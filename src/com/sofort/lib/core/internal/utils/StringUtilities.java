package com.sofort.lib.core.internal.utils;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Collection;

import javax.xml.bind.DatatypeConverter;


/**
 * Text utilities.
 */
public class StringUtilities {

	/**
	 * Encodes given text to URL conform form with UTF-8 text encoding.
	 * 
	 * @param str
	 *            the str
	 * @return the string
	 */
	public String urlEncode(final String str) {
		return urlEncode(str, "UTF-8");
	}


	/**
	 * Encodes given text to URL conform form with given text encoding.
	 * 
	 * @param txt
	 *            the text
	 * @param encoding
	 *            the encoding
	 * @return the string
	 */
	protected String urlEncode(final String txt, String encoding) {
		try {
			return URLEncoder.encode(txt, encoding);
		} catch (final UnsupportedEncodingException e) {
			throw new IllegalArgumentException("Not proper encoding " + encoding + ".");
		}
	}


	/**
	 * Converts the given text to the base64 coded one.
	 * 
	 * @param txt
	 *            the text
	 * @return the string
	 */
	public String base64encode(final String txt) {
		return DatatypeConverter.printBase64Binary(txt.getBytes(Charset.forName("UTF-8")));
	}


	/**
	 * Converts the given base64 encoded text to the plain one.
	 * 
	 * @param txt
	 *            the base64 encoded text text
	 * @return the decoded string
	 */
	public String base64decode(final String txt) {
		return new String(DatatypeConverter.parseBase64Binary(txt), Charset.forName("UTF-8"));
	}


	/**
	 * Converts the given URL text to an {@link URL}.
	 * 
	 * @param urlText
	 *            the url text
	 * @return the url
	 */
	public URL toUrl(final String urlText) {
		try {
			return new URL(urlText);
		} catch (MalformedURLException e) {
			throw new IllegalArgumentException("Not proper url " + urlText + ". " + e.getMessage());
		}
	}


	/**
	 * Converts a list of Object to one String using the given separator.
	 * 
	 * @param col
	 *            a collection. Elements must have implemented the toString()
	 * @param separator
	 *            a separator inserted between
	 * @return a string with separated String interpretations of elements
	 */
	public String glue(Collection<?> col, String separator) {

		StringBuilder sb = new StringBuilder();
		for (Object o : col) {

			if (sb.length() > 0) {
				sb.append(separator);
			}

			sb.append(o.toString());

		}

		return sb.toString();
	}
}
