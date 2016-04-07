package com.sofort.lib.core.internal.net.http;

import com.sofort.lib.core.internal.net.ConnectionException;


public class HttpConnectionException extends ConnectionException {

	private static final long serialVersionUID = 4870299963476899927L;

	/**
	 * See: http://en.wikipedia.org/wiki/List_of_HTTP_status_codes
	 */
	private final int responseCode;


	/**
	 * @param responseCode
	 *            the response/status code
	 * @param message
	 *            exception message
	 */
	public HttpConnectionException(int responseCode, String message) {
		super(message);
		this.responseCode = responseCode;
	}


	/**
	 * Returns the response code. More details:
	 * http://en.wikipedia.org/wiki/List_of_HTTP_status_codes
	 * 
	 * @return the HTTP response/status code
	 */
	public int getResponseCode() {
		return responseCode;
	}

}
