package com.sofort.lib.core;

import org.apache.commons.logging.Log;

import com.sofort.lib.core.internal.net.Connection;
import com.sofort.lib.core.internal.net.ConnectionConfig;
import com.sofort.lib.core.internal.transformer.DataHandler;
import com.sofort.lib.core.internal.transformer.RawRequest;
import com.sofort.lib.core.internal.transformer.RawResponse;
import com.sofort.lib.core.products.request.SofortLibRequest;
import com.sofort.lib.core.products.response.SofortLibResponse;
import com.sofort.lib.core.products.response.SofortTransactionStatusNotification;


/**
 * SofortLib common implementation which handles/controls the complete
 * communication with the SofortLib API.
 * 
 * Main communication steps:
 * 
 * 1) Developer creates an instance of needed SofortLib Request and passes this
 * to the SofortLib (sendRequest calls).
 * 
 * 2) SofortLib converts the given request with defined renderer to an API
 * conform request.
 * 
 * 3) SofortLib sends the converted request to API with the defined Connector
 * and ConnectionData defined for each SofortLib request.
 * 
 * 4) The Connector returns the API response which will be converted with the
 * defined parser to the expected SofotLib request.
 * 
 * For developer: use the end product implementations of SofortLib.
 */
public class SofortLibCommon {

	private final ConnectionConfig connectionConfig;
	private final DataHandler dataHandler;
	private final Log log;


	/**
	 * Defines a SofortLib with the given connection data, data handler and
	 * logger.
	 * 
	 * @param connectionConfig
	 *            connection specific communication settings container
	 * @param dataHandler
	 *            handler controls the parsing and rendering of the data
	 * @param log
	 *            logger of type {@link Log}. The SofortLibCommon can but should
	 *            not be used directly, therefore the logger should be passed
	 *            through from the product implementations
	 */
	public SofortLibCommon(ConnectionConfig connectionConfig, DataHandler dataHandler, Log log) {
		this.connectionConfig = connectionConfig;
		this.dataHandler = dataHandler;
		this.log = log;
	}


	/**
	 * Sends a defined SofortLib request to API and receives the expected
	 * SofortLib response.
	 * 
	 * @param request
	 *            the defined SofortLib request.
	 * @param responseClass
	 *            expected response class. Needed to
	 * @param <T>
	 *            the generic type of a sofort lib response
	 * @return an instance of an expected response
	 */
	@SuppressWarnings("unchecked")
	public <T extends SofortLibResponse> T sendLibRequest(SofortLibRequest request, Class<T> responseClass) {
		log.debug("Render the SOFORT request to a raw request.");
		RawRequest rawRequest = dataHandler.render(request);
		log.debug("Rendered raw request content:\n" + rawRequest.getContent());

		log.debug("Get the connection properties for the given request.");
		Connection connection = getConnection(request);

		log.debug("Send the raw request.");
		RawResponse rawResponse = connection.doRequest(rawRequest);
		log.debug("Received raw response content:\n" + rawResponse.getContent());

		log.debug("Parse the raw response to the data response.");
		return (T) dataHandler.parse(rawResponse, responseClass);
	}


	/**
	 * Parses received raw response to the sofort transaction notification.
	 * 
	 * @param rawResponse
	 *            the raw response
	 * @return the sofort transaction notification.
	 */
	public SofortTransactionStatusNotification parseStatusNotificationResponse(RawResponse rawResponse) {
		return (SofortTransactionStatusNotification) dataHandler.parse(rawResponse, SofortTransactionStatusNotification.class);
	}


	/**
	 * Returns the defined connection for given kind of the SofortLib request.
	 * 
	 * @param request
	 *            the defined SofortLib request.
	 * @return the defined connection for given kind of the SofortLib request.
	 */
	public Connection getConnection(SofortLibRequest request) {
		return connectionConfig.getConnection(request.getClass());
	}
}
