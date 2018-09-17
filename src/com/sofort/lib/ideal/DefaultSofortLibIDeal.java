package com.sofort.lib.ideal;

import com.sofort.lib.core.internal.net.ConnectionConfig;
import com.sofort.lib.core.internal.net.http.BasicHttpAuthorization;
import com.sofort.lib.core.internal.net.http.HttpConnector;
import com.sofort.lib.core.internal.transformer.DataHandler;
import com.sofort.lib.core.internal.transformer.xml.XmlDataHandler;
import com.sofort.lib.core.internal.utils.HashAlgorithm;
import com.sofort.lib.ideal.internal.net.http.HttpConnectionConfigIDeal;
import com.sofort.lib.ideal.internal.transformer.xml.XmlConfigIDeal;


/**
 * A default configured {@link SofortLibIDeal}.
 * <p>
 * Use this class if you want to integrate the {@link SofortLibIDeal} without
 * changing or fitting any parts and parameters of SofortLib Java.
 */
public class DefaultSofortLibIDeal extends SofortLibIDeal {

    /**
     * Instantiates the default configured {@link SofortLibIDeal}.
     *
     * @param customerId    the customer id
     * @param apiKey        the api key
     * @param hashAlgorithm the hash algorithm
     */
    public DefaultSofortLibIDeal(int customerId, String apiKey, HashAlgorithm hashAlgorithm) {
        /* Define the SofortLib with prepared parameters. */
        super(getConnectionConfig(customerId, apiKey), getDataHandler(), hashAlgorithm);
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
        BasicHttpAuthorization auth = new BasicHttpAuthorization(customerId, apiKey);

        /* The handler must authorize yourself at each API call. */
        HttpConnector connector = new HttpConnector();

        /* Define the communication configuration. */
        return new HttpConnectionConfigIDeal(connector, auth);
    }


    /**
     * Gets the data handler.
     *
     * @return the data handler
     */
    private static DataHandler getDataHandler() {
        /* Define the data handler between API and Java. */
        return new XmlDataHandler(new XmlConfigIDeal());
    }

}
