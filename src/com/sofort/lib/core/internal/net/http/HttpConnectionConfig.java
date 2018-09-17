package com.sofort.lib.core.internal.net.http;

import com.sofort.lib.core.internal.net.Connection;
import com.sofort.lib.core.internal.net.ConnectionConfig;
import com.sofort.lib.core.products.request.SofortLibRequest;

import java.util.HashMap;
import java.util.Map;


/**
 * Stores and handles the HTTP connection configuration for specified SofortLib
 * product requests.
 * <p>
 * For developer: use the
 * {@link HttpConnectionConfig#addConnection(Class, String)} to define and
 * {@link HttpConnectionConfig#getConnection(Class)} to get defined
 * configurations for requests you need.
 */
public abstract class HttpConnectionConfig implements ConnectionConfig {

    private final BasicHttpAuthorization authorization;
    private final HttpConnector connector;

    private final Map<Class<? extends SofortLibRequest>, Connection> entryMapping;


    /**
     * Defines an instance of configuration with defined HTTP connector and
     * authorization used for every new per
     * {@link HttpConnectionConfig#addConnection(Class, String)} added
     * connection.
     *
     * @param connector     HTTP connector implementation used for HTTP POST requests
     * @param authorization basic HTTP authorization used for authentication while API
     *                      requests
     */
    public HttpConnectionConfig(HttpConnector connector, BasicHttpAuthorization authorization) {
        this.connector = connector;
        this.authorization = authorization;
        this.entryMapping = new HashMap<Class<? extends SofortLibRequest>, Connection>(8, 1);

        initRequestConnections();
    }


    /**
     * Initializes the entry mappings for specified SofotLib Requests.
     * <p>
     * For developer: use
     * {@link HttpConnectionConfig#addConnection(Class, String)} to add the
     * configurations per request and URL.
     */
    protected abstract void initRequestConnections();


    /**
     * Adds a new HTTP connection configuration with pre-defined connector and
     * authorization and given url for given object of type
     * {@link SofortLibRequest}.
     *
     * @param requestClass a class of the sofort lib request
     * @param url          API url used for requests for given object of type
     *                     {@link SofortLibRequest}.
     */
    protected void addConnection(Class<? extends SofortLibRequest> requestClass, String url) {
        entryMapping.put(requestClass, new Connection(connector, new HttpConnectionData(url, authorization)));
    }


    /*
     * (non-Javadoc)
     *
     * @see com.sofort.lib.ideal.ideal.refund.refund.billcode.billcode.paycode.
     * paycode.payment.payment.core.core.internal.net.ConnectionConfig#
     * getConnection(java.lang. Class)
     */
    @Override
    public Connection getConnection(Class<? extends SofortLibRequest> requestClass) {
        return entryMapping.get(requestClass);
    }

}
