package com.sofort.lib.paycode.internal.net.http;

import com.sofort.lib.core.internal.net.http.BasicHttpAuthorization;
import com.sofort.lib.core.internal.net.http.HttpConnectionConfig;
import com.sofort.lib.core.internal.net.http.HttpConnector;
import com.sofort.lib.paycode.products.request.PaycodeRequest;
import com.sofort.lib.paycode.products.request.PaycodeStatusRequest;
import com.sofort.lib.paycode.products.request.PaycodeTransactionDetailsRequest;


/**
 * Defines current SofortLib API connection configuration for SOFORT Payment
 * requests.
 */
public class HttpConnectionConfigPaycode extends HttpConnectionConfig {

    /**
     * An instance with connector and basic authorization.
     *
     * @param connector     low level API communicator
     * @param authorization basic HTTP authorization
     */
    public HttpConnectionConfigPaycode(HttpConnector connector, BasicHttpAuthorization authorization) {
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

        addConnection(PaycodeRequest.class, url);
        addConnection(PaycodeStatusRequest.class, url);
        addConnection(PaycodeTransactionDetailsRequest.class, url);
    }

}
