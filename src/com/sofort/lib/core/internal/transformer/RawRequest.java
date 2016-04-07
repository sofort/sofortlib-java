package com.sofort.lib.core.internal.transformer;

/**
 * A container for the raw request data.
 */
public class RawRequest {

	/** The content. */
	private final String content;


	/**
	 * Defines a raw request with the given content.
	 * 
	 * @param content
	 *            the content
	 */
	public RawRequest(String content) {
		this.content = content;
	}


	/**
	 * Gets the content of the raw request.
	 * 
	 * @return the content of the raw request.
	 */
	public String getContent() {
		return content;
	}

}
