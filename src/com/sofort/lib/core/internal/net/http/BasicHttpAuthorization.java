package com.sofort.lib.core.internal.net.http;

import com.sofort.lib.core.internal.utils.StringUtilities;


/**
 * Builds the standard HTTP base64 encoded authorization string from given
 * parameters. Basic authorization is needed for communication with API over
 * HTTP(S) protocol.
 */
public class BasicHttpAuthorization {

	private final String authorisation;


	/**
	 * Basic HTTP authorization. Used for authentication during API requests
	 * over HTTP(S) protocol.
	 * 
	 * @param userId
	 *            handler customer number, used as login
	 * @param apiKey
	 *            handler API key defined in control center, used as password
	 */
	public BasicHttpAuthorization(int userId, String apiKey) {
		authorisation = "Basic " + new StringUtilities().base64encode(userId + ":" + apiKey);
	}


	/**
	 * Returns the basic authorization as a base64 coded text.
	 * 
	 * @return the base64 coded basic authorization text
	 */
	public String getValue() {
		return authorisation;
	}

}
