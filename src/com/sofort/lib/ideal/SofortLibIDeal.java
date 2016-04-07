package com.sofort.lib.ideal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sofort.lib.core.SofortLibCommon;
import com.sofort.lib.core.internal.net.ConnectionConfig;
import com.sofort.lib.core.internal.net.http.HttpConnectionData;
import com.sofort.lib.core.internal.transformer.DataHandler;
import com.sofort.lib.core.internal.utils.HashAlgorithm;
import com.sofort.lib.ideal.products.ideal.IDealNotificationResponseHashCalculator;
import com.sofort.lib.ideal.products.ideal.IDealRequestHashCalculator;
import com.sofort.lib.ideal.products.ideal.IDealRequestPaymentUrlBuilder;
import com.sofort.lib.ideal.products.request.IDealBanksRequest;
import com.sofort.lib.ideal.products.request.IDealRequest;
import com.sofort.lib.ideal.products.response.IDealBanksResponse;
import com.sofort.lib.ideal.products.response.IDealNotificationResponse;


/**
 * SofortLib implementation of SOFORT iDEAL.
 * 
 * Implements the communication with the current SofortLib API for SOFORT iDEAL
 * and tools for signing and checking the transaction data and manually received
 * responses:
 * 
 * - gets the current available iDEAL Banks with names and codes with
 * {@link SofortLibIDeal#sendIDealBanksRequest()}
 * 
 * - generate the payment redirection URL for an iDEAL payment (GET method) with
 * {@link SofortLibIDeal#getPaymentUrl(IDealRequest, String)}
 * 
 * - generate the hash value for the iDEAL payment form (POST method) with
 * {@link SofortLibIDeal#calculateHash(IDealRequest, String)}
 * 
 * - check the integrity of the manually received iDEAL notification with
 * {@link SofortLibIDeal#calculateHash(IDealNotificationResponse, String)}
 */
public class SofortLibIDeal {

	/** The log. */
	private final Log log = LogFactory.getLog(getClass());

	/** The sofort lib. */
	private final SofortLibCommon sofortLib;

	/** The hash algorithm. */
	private final HashAlgorithm hashAlgorithm;


	/**
	 * Instantiates a new sofort lib iDEAL with given communication, handler and
	 * defined hash algorithm data.
	 * 
	 * @param connectionDataConfig
	 *            the connection data config
	 * @param dataHandler
	 *            the data handler
	 * @param hashAlgorithm
	 *            the hash algorithm you defined in control center for
	 *            generation of hash value needed for communication with the
	 *            API.
	 */
	public SofortLibIDeal(ConnectionConfig connectionDataConfig, DataHandler dataHandler, HashAlgorithm hashAlgorithm) {
		sofortLib = new SofortLibCommon(connectionDataConfig, dataHandler, log);
		this.hashAlgorithm = hashAlgorithm;
	}


	/**
	 * Gets the current available iDEAL bank list with codes and names.
	 * 
	 * @return the current available iDEAL bank list
	 */
	public IDealBanksResponse sendIDealBanksRequest() {
		log.info("Send an iDeal Banks request.");
		return sofortLib.sendLibRequest(new IDealBanksRequest(), IDealBanksResponse.class);
	}


	/**
	 * Generates the payment redirection URL for an iDEAL payment (GET method).
	 * The hash value will be calculated and integrated into the paylemnt URL
	 * automatically.
	 * 
	 * @param request
	 *            the request with iDEAL payment data
	 * @param password
	 *            the password defined in control center
	 * @return the proper encoded redirection payment URL as text
	 */
	public String getPaymentUrl(IDealRequest request, String password) {
		log.info("Build the iDeal payment URL.");
		HttpConnectionData connData = (HttpConnectionData) sofortLib.getConnection(request).getConnectionData();
		return new IDealRequestPaymentUrlBuilder(request).getPaymentUrl(connData.getTarget(), password, hashAlgorithm);
	}


	/**
	 * Generates the hash value for the iDEAL payment form (POST method)
	 * 
	 * @param request
	 *            the request with iDEAL payment data
	 * @param password
	 *            the password defined in control center
	 * @return the generated hash value for given request
	 */
	public String calculateHash(IDealRequest request, String password) {
		log.info("Calculate the hash for the iDeal request.");
		return new IDealRequestHashCalculator(request).getHash(password, hashAlgorithm);
	}


	/**
	 * Calculates the hash value for checking the integrity of the manually
	 * received iDEAL notification.
	 * 
	 * @param response
	 *            the response filled with the manually received notification
	 *            data
	 * @param password
	 *            the password defined in control center
	 * @return the generated hash value for given response
	 */
	public String calculateHash(IDealNotificationResponse response, String password) {
		log.info("Calculate the hash for the iDeal notification response.");
		return new IDealNotificationResponseHashCalculator(response).getHash(password, hashAlgorithm);
	}

}
