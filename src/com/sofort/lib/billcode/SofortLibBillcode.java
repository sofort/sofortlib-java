package com.sofort.lib.billcode;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sofort.lib.billcode.products.request.BillcodeRequest;
import com.sofort.lib.billcode.products.request.BillcodeStatusRequest;
import com.sofort.lib.billcode.products.request.BillcodeTransactionDetailsRequest;
import com.sofort.lib.billcode.products.response.BillcodeResponse;
import com.sofort.lib.billcode.products.response.BillcodeStatusResponse;
import com.sofort.lib.billcode.products.response.BillcodeTransactionDetailsResponse;
import com.sofort.lib.core.SofortLibCommon;
import com.sofort.lib.core.internal.net.ConnectionConfig;
import com.sofort.lib.core.internal.transformer.DataHandler;
import com.sofort.lib.core.internal.transformer.RawResponse;
import com.sofort.lib.core.products.response.SofortTransactionStatusNotification;


/**
 * SofortLib implementation of SOFORT Billcode.
 * 
 * Implements the complete communication with the current SofortLib API for
 * SOFORT Billcode and tools for parsing of manually received notification
 * responses:
 * 
 * - initiate a SOFORT Billcode with
 * {@link SofortLibBillcode#sendBillcodeRequest(BillcodeRequest)}
 * 
 * - parse the received status notification to
 * {@link SofortTransactionStatusNotification} with
 * {@link SofortLibBillcode#parseStatusNotificationResponse(RawResponse)}
 * 
 * - get the payment transaction details with
 * {@link SofortLibBillcode#sendBillcodeTransactionDetailsRequest(BillcodeTransactionDetailsRequest)}
 */
public class SofortLibBillcode {

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
	public SofortLibBillcode(ConnectionConfig connectionConfig, DataHandler dataHandler) {
		sofortLibCommon = new SofortLibCommon(connectionConfig, dataHandler, log);
	}


	/**
	 * Initiates a SOFORT Billcode for getting the transaction ID and
	 * redirection payment URL for buyer.
	 * 
	 * @param request
	 *            the filled payment request
	 * @return the sofort payment response with redirection URL and the
	 *         transaction ID
	 */
	public BillcodeResponse sendBillcodeRequest(BillcodeRequest request) {
		log.info("Send a SOFORT billcode request.");
		return sofortLibCommon.sendLibRequest(request, BillcodeResponse.class);
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
	 * Gets the billcode status for the given billcode.
	 * 
	 * @param request
	 *            the request with the billcode status details
	 * @return the transaction details payment response
	 */
	public BillcodeStatusResponse sendBillcodeStatusRequest(BillcodeStatusRequest request) {
		log.info("Send a SOFORT transaction details request.");
		return sofortLibCommon.sendLibRequest(request, BillcodeStatusResponse.class);
	}


	/**
	 * Gets the transaction details for either the transaction IDs or the
	 * transaction time/status search parameters.
	 * 
	 * @param request
	 *            the request with transaction search parameters
	 * @return the transaction details payment response
	 */
	public BillcodeTransactionDetailsResponse sendBillcodeTransactionDetailsRequest(BillcodeTransactionDetailsRequest request) {
		log.info("Send a SOFORT transaction details request.");
		return sofortLibCommon.sendLibRequest(request, BillcodeTransactionDetailsResponse.class);
	}

}
