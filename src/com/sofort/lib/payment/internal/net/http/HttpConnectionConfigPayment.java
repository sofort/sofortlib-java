package com.sofort.lib.payment.internal.net.http;

import com.sofort.lib.core.internal.net.http.BasicHttpAuthorization;
import com.sofort.lib.core.internal.net.http.HttpConnectionConfig;
import com.sofort.lib.core.internal.net.http.HttpConnector;
import com.sofort.lib.payment.products.request.PaymentRequest;
import com.sofort.lib.payment.products.request.PaymentTransactionDetailsRequest;


/**
 * Defines current SofortLib API connection configuration for SOFORT Payment
 * (SOFORT Überweisung) requests.
 */
public class HttpConnectionConfigPayment extends HttpConnectionConfig {

	/**
	 * An instance with connector and basic authorization.
	 * 
	 * @param connector
	 *            low level API communicator
	 * @param authorization
	 *            basic HTTP authorization
	 */
	public HttpConnectionConfigPayment(HttpConnector connector, BasicHttpAuthorization authorization) {
		super(connector, authorization);
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sofort.lib.ideal.ideal.refund.refund.billcode.billcode.paycode.paycode.payment.payment.core.core.internal.net.http.HttpConnectionConfig#initRequestConnections
	 * ()
	 */
	@Override
	protected void initRequestConnections() {

		String url = "https://api.sofort.com/api/xml";

		addConnection(PaymentRequest.class, url);
		addConnection(PaymentTransactionDetailsRequest.class, url);

	}

}
