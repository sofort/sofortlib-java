package com.sofort.lib.ideal.internal.net.http;

import com.sofort.lib.core.internal.net.http.BasicHttpAuthorization;
import com.sofort.lib.core.internal.net.http.HttpConnectionConfig;
import com.sofort.lib.core.internal.net.http.HttpConnector;
import com.sofort.lib.ideal.products.request.IDealBanksRequest;
import com.sofort.lib.ideal.products.request.IDealRequest;


/**
 * Defines current SofortLib API connection configuration for iDEAL requests.
 */
public class HttpConnectionConfigIDeal extends HttpConnectionConfig {

    /**
     * An instance with connector and basic authorization.
     *
     * @param connector     low level API communicator
     * @param authorization basic HTTP authorization
     */
    public HttpConnectionConfigIDeal(HttpConnector connector, BasicHttpAuthorization authorization) {
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
        addConnection(IDealBanksRequest.class, "https://www.sofort.com/payment/ideal/banks");
        addConnection(IDealRequest.class, "https://www.sofort.com/payment/ideal");
    }

}
