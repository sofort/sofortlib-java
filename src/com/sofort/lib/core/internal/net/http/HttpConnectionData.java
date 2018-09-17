package com.sofort.lib.core.internal.net.http;

import com.sofort.lib.core.internal.net.ConnectionData;
import com.sofort.lib.core.internal.utils.StringUtilities;

import java.net.URL;


/**
 * The HTTP Connection data container implementation for HTTP API requests.
 */
public class HttpConnectionData implements ConnectionData {

    private final URL target;
    private final BasicHttpAuthorization authorisation;


    /**
     * HTTP connection data with target URL and basic HTTP authorization.
     *
     * @param target        target HTTP URL as text
     * @param authorisation authorization data used during target calls
     */
    public HttpConnectionData(String target, BasicHttpAuthorization authorisation) {
        this(new StringUtilities().toUrl(target), authorisation);
    }


    /**
     * HTTP connection data with target URL and basic HTTP authorization.
     *
     * @param target        target HTTP URL
     * @param authorisation authorization data used during target calls
     */
    public HttpConnectionData(URL target, BasicHttpAuthorization authorisation) {
        this.target = target;
        this.authorisation = authorisation;
    }


    /**
     * Returns the target URL.
     *
     * @return target URL
     */
    public URL getTarget() {
        return target;
    }


    /**
     * Returns the authorization data.
     *
     * @return authorization data
     */
    public BasicHttpAuthorization getAuthorisation() {
        return authorisation;
    }

}
