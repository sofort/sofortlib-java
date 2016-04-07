package com.sofort.lib.core.internal.net;

import com.sofort.lib.core.internal.transformer.RawRequest;
import com.sofort.lib.core.internal.transformer.RawResponse;


/**
 * Connection data container unites the connector and connection data needed to
 * communicate with the API.
 */
public class Connection {

	private final Connector connector;
	private final ConnectionData data;


	/**
	 * Defines a connection with given connector and connection data.
	 * 
	 * @param connector
	 *            connector
	 * @param data
	 *            connection data
	 */
	public Connection(Connector connector, ConnectionData data) {
		this.connector = connector;
		this.data = data;
	}


	/**
	 * Return the connection data.
	 * 
	 * @return connection data
	 */
	public ConnectionData getConnectionData() {
		return data;
	}


	/**
	 * Send the given raw request with defined connector using defined
	 * connection data.
	 * 
	 * @param request
	 *            the raw request to be send using the connector and connection
	 *            data
	 * @return the raw response the connector returns
	 * @throws ConnectionException
	 *             a connection exception
	 */
	public RawResponse doRequest(RawRequest request) {
		return connector.doRequest(request, data);
	}
}
