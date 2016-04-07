package com.sofort.lib.core.internal.utils.xml;

/**
 * The Class XmlRendererHelperException.
 */
public class XmlRendererHelperException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8804671128723636467L;


	/**
	 * Instantiates a new xml renderer helper exception.
	 * 
	 * @param message
	 *            the message
	 */
	public XmlRendererHelperException(String message) {
		this(message, null);
	}


	/**
	 * Instantiates a new xml renderer helper exception.
	 * 
	 * @param message
	 *            the message
	 * @param cause
	 *            the cause
	 */
	public XmlRendererHelperException(String message, Throwable cause) {
		super(message, cause);
	}

}
