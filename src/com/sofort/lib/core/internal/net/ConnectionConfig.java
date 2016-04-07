package com.sofort.lib.core.internal.net;

import com.sofort.lib.core.products.request.SofortLibRequest;


/**
 * Defines a container which stores the the communication settings for each
 * request of type {@link SofortLibRequest}.
 */
public interface ConnectionConfig {

	/**
	 * Returns the defined connection for given request class of type
	 * {@link SofortLibRequest}.
	 * 
	 * @param requestClass
	 *            request class of type {@link SofortLibRequest}
	 * @return the connection predefined for given request class of type
	 *         {@link SofortLibRequest}, or null if not predefined
	 */
	public Connection getConnection(Class<? extends SofortLibRequest> requestClass);

}
