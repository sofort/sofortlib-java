package com.sofort.lib.paycode;

import com.sofort.lib.core.internal.net.ConnectionConfig;
import com.sofort.lib.core.internal.net.http.BasicHttpAuthorization;
import com.sofort.lib.core.internal.net.http.HttpConnector;
import com.sofort.lib.core.internal.transformer.DataHandler;
import com.sofort.lib.core.internal.transformer.xml.XmlDataHandler;
import com.sofort.lib.paycode.internal.net.http.HttpConnectionConfigPaycode;
import com.sofort.lib.paycode.internal.transformer.xml.XmlConfigPaycode;


/**
 * A default configured {@link SofortLibPaycode}.
 * <p>
 * Use this class if you want to integrate the {@link SofortLibPaycode} without
 * changing or fitting any parts and parameters of SofortLib Java.
 */
public class DefaultSofortLibPaycode extends SofortLibPaycode {

    /**
     * Instance of default configured {@link SofortLibPaycode}.
     *
     * @param customerId customer id of handler
     * @param apiKey     handlers API key of handler
     */
    public DefaultSofortLibPaycode(int customerId, String apiKey) {
        /* Define the SofortLib with prepared parameters. */
        super(getConnectionConfig(customerId, apiKey), getDataHandler());
    }


    /**
     * Gets the connection config.
     *
     * @param customerId the customer id
     * @param apiKey     the api key
     * @return the connection config
     */
    private static ConnectionConfig getConnectionConfig(int customerId, String apiKey) {
        /* Define the connector needed for data exchange with the API. */
        final HttpConnector connector = new HttpConnector();

        /* The handler must authorize yourself at each API call. */
        final BasicHttpAuthorization auth = new BasicHttpAuthorization(customerId, apiKey);

        /* Define the communication configuration. */
        return new HttpConnectionConfigPaycode(connector, auth);
    }


    /**
     * Gets the data handler.
     *
     * @return the data handler
     */
    private static DataHandler getDataHandler() {
        /* Define the data handler between API and Java. */
        return new XmlDataHandler(new XmlConfigPaycode());
    }
}
