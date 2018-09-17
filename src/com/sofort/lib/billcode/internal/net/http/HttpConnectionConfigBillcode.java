package com.sofort.lib.billcode.internal.net.http;

import com.sofort.lib.billcode.products.request.BillcodeRequest;
import com.sofort.lib.billcode.products.request.BillcodeStatusRequest;
import com.sofort.lib.billcode.products.request.BillcodeTransactionDetailsRequest;
import com.sofort.lib.core.internal.net.http.BasicHttpAuthorization;
import com.sofort.lib.core.internal.net.http.HttpConnectionConfig;
import com.sofort.lib.core.internal.net.http.HttpConnector;


/**
 * Defines current SofortLib API connection configuration for SOFORT Billcode
 * requests.
 */
public class HttpConnectionConfigBillcode extends HttpConnectionConfig {

    /**
     * An instance with connector and basic authorization.
     *
     * @param connector     low level API communicator
     * @param authorization basic HTTP authorization
     */
    public HttpConnectionConfigBillcode(HttpConnector connector, BasicHttpAuthorization authorization) {
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

        addConnection(BillcodeRequest.class, url);
        addConnection(BillcodeStatusRequest.class, url);
        addConnection(BillcodeTransactionDetailsRequest.class, url);
    }

}
