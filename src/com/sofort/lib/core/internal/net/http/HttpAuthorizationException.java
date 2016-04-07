package com.sofort.lib.core.internal.net.http;

public class HttpAuthorizationException extends HttpConnectionException {

	private static final long serialVersionUID = 9000454800687270528L;


	/**
	 * @param message
	 *            exception message
	 */
	public HttpAuthorizationException(String message) {
		super(401, message);
	}

}
