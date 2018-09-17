package com.sofort.lib.core.internal.net;

import com.sofort.lib.core.internal.transformer.RawRequest;
import com.sofort.lib.core.internal.transformer.RawResponse;


/**
 * Definition of a connector.
 * <p>
 * A connector is needed to communicate with the API on the lowest level. Sends
 * a raw request and receives and return a raw response.
 */
public interface Connector {

    /**
     * Send the raw request using the given connection data.
     *
     * @param request the raw request
     * @param cd      connection data needed for sending the request
     * @return the received raw request
     * @throws ConnectionException a connection exception
     */
    public RawResponse doRequest(RawRequest request, ConnectionData cd);

}
