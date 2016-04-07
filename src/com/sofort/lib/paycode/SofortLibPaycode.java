package com.sofort.lib.paycode;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sofort.lib.core.SofortLibCommon;
import com.sofort.lib.core.internal.net.ConnectionConfig;
import com.sofort.lib.core.internal.transformer.DataHandler;
import com.sofort.lib.core.internal.transformer.RawResponse;
import com.sofort.lib.core.products.response.SofortTransactionStatusNotification;
import com.sofort.lib.paycode.products.request.PaycodeRequest;
import com.sofort.lib.paycode.products.request.PaycodeStatusRequest;
import com.sofort.lib.paycode.products.request.PaycodeTransactionDetailsRequest;
import com.sofort.lib.paycode.products.response.PaycodeResponse;
import com.sofort.lib.paycode.products.response.PaycodeStatusResponse;
import com.sofort.lib.paycode.products.response.PaycodeTransactionDetailsResponse;


/**
 * SofortLib implementation of SOFORT Paycode.
 * 
 * Implements the complete communication with the current SofortLib API for
 * SOFORT Paycode and tools for parsing of manually received notification
 * responses:
 * 
 * - initiate a SOFORT Paycode with
 * {@link SofortLibPaycode#sendPaycodeRequest(PaycodeRequest)}
 * 
 * - parse the received status notification to
 * {@link SofortTransactionStatusNotification} with
 * {@link SofortLibPaycode#parseStatusNotificationResponse(RawResponse)}
 * 
 * - get the payment transaction details with
 * {@link SofortLibPaycode#sendPaycodeTransactionDetailsRequest(PaycodeTransactionDetailsRequest)}
 */
public class SofortLibPaycode {

	/** The log. */
	private final Log log = LogFactory.getLog(getClass());

	/** The sofort lib common implementation. */
	private final SofortLibCommon sofortLibCommon;


	/**
	 * Instantiates a new sofort lib payment with the given communication and
	 * handler data.
	 * 
	 * @param connectionConfig
	 *            the connection config
	 * @param dataHandler
	 *            the data handler
	 */
	public SofortLibPaycode(ConnectionConfig connectionConfig, DataHandler dataHandler) {
		sofortLibCommon = new SofortLibCommon(connectionConfig, dataHandler, log);
	}


	/**
	 * Initiates a SOFORT Paycode for getting the transaction ID and redirection
	 * payment URL for buyer.
	 * 
	 * @param request
	 *            the filled payment request
	 * @return the sofort payment response with redirection URL and the
	 *         transaction ID
	 */
	public PaycodeResponse sendPaycodeRequest(PaycodeRequest request) {
		log.info("Send a SOFORT paycode request.");
		return sofortLibCommon.sendLibRequest(request, PaycodeResponse.class);
	}


	/**
	 * Parses the received status notification response to a
	 * {@link SofortTransactionStatusNotification}.
	 * 
	 * @param rawResponse
	 *            the raw response with the status notification as content
	 * @return the sofort transaction status notification
	 */
	public SofortTransactionStatusNotification parseStatusNotificationResponse(RawResponse rawResponse) {
		log.info("Parse the raw status notification response to the data response.");
		return sofortLibCommon.parseStatusNotificationResponse(rawResponse);
	}


	/**
	 * Gets the paycode status for the given paycode.
	 * 
	 * @param request
	 *            the request with the paycode status details
	 * @return the transaction details payment response
	 */
	public PaycodeStatusResponse sendPaycodeStatusRequest(PaycodeStatusRequest request) {
		log.info("Send a SOFORT transaction details request.");
		return sofortLibCommon.sendLibRequest(request, PaycodeStatusResponse.class);
	}


	/**
	 * Gets the transaction details for either the transaction IDs or the
	 * transaction time/status search parameters.
	 * 
	 * @param request
	 *            the request with transaction search parameters
	 * @return the transaction details payment response
	 */
	public PaycodeTransactionDetailsResponse sendPaycodeTransactionDetailsRequest(PaycodeTransactionDetailsRequest request) {
		log.info("Send a SOFORT transaction details request.");
		return sofortLibCommon.sendLibRequest(request, PaycodeTransactionDetailsResponse.class);
	}

}
