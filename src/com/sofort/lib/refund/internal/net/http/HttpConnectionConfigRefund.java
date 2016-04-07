package com.sofort.lib.refund.internal.net.http;

import com.sofort.lib.core.internal.net.http.BasicHttpAuthorization;
import com.sofort.lib.core.internal.net.http.HttpConnectionConfig;
import com.sofort.lib.core.internal.net.http.HttpConnector;
import com.sofort.lib.refund.products.request.RefundRequest;


/**
 * Defines current SofortLib API connection configuration for the refund API
 * requests.
 */
public class HttpConnectionConfigRefund extends HttpConnectionConfig {

	/**
	 * An instance with connector and basic authorization.
	 * 
	 * @param connector
	 *            low level API communicator
	 * @param authorization
	 *            basic HTTP authorization
	 */
	public HttpConnectionConfigRefund(HttpConnector connector, BasicHttpAuthorization authorization) {
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
		addConnection(RefundRequest.class, "https://api.sofort.com/api/xml");
	}

}
