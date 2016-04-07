package com.sofort.lib.core.internal.transformer;

import com.sofort.lib.core.products.request.SofortLibRequest;
import com.sofort.lib.core.products.response.SofortLibResponse;


/**
 * A data handler builds a middle layer between pure API XML requests/responses
 * and SofortLib Java request/response objects.
 * 
 * The data handler controls:
 * 
 * 1) rendering of Java {@link SofortLibRequest} to an API XML request wrapped
 * in a {@link RawRequest}
 * 
 * 2) parsing of an API XML response wrapped in {@link RawResponse} to
 * {@link SofortLibResponse}
 */
public interface DataHandler {

	/**
	 * Renders {@link SofortLibRequest} to the API XML request wrapped into
	 * {@link RawRequest}.
	 * 
	 * @param dataRequest
	 *            a {@link SofortLibRequest}
	 * @return API XML request wrapped into {@link RawRequest}
	 */
	public RawRequest render(SofortLibRequest dataRequest);


	/**
	 * Parses the API XML response wrapped into {@link RawResponse} to
	 * {@link SofortLibResponse} of given type.
	 * 
	 * @param rawResponse
	 *            API XML response wrapped into {@link RawResponse}
	 * @param responseClass
	 *            expected response class
	 * @return parsed {@link SofortLibResponse} of given type
	 */
	public SofortLibResponse parse(RawResponse rawResponse, Class<? extends SofortLibResponse> responseClass);

}
