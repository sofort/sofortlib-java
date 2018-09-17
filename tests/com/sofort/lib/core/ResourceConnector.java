package com.sofort.lib.core;

import java.util.HashMap;
import java.util.Map;

import com.sofort.lib.core.internal.ResourceContentReader;
import com.sofort.lib.core.internal.net.ConnectionData;
import com.sofort.lib.core.internal.net.Connector;
import com.sofort.lib.core.internal.transformer.RawRequest;
import com.sofort.lib.core.internal.transformer.RawResponse;
import com.sofort.lib.core.internal.transformer.RawResponse.Status;
import com.sofort.lib.core.internal.utils.HashBuilder;
import com.sofort.lib.core.internal.utils.XmlNormalizer;

import static com.sofort.lib.core.Logger.log;
/**
 * A file implementation of SofortLib connector. Used to test the SofortLib with
 * stored SofortLib responses into files.
 */
public class ResourceConnector implements Connector {

	private final Map<String, String> requestHashResponseMap;

	public ResourceConnector(String[] requestResources) {

		requestHashResponseMap = new HashMap<String, String>(16, 1);

		build(requestResources);
	}


	private void build(String[] requestResources) {
		for (final String requestResource : requestResources) {
			log.info("Put response for request: " + requestResource);

			final String hash = getHash(requestResource);
			final String responseResource = requestResource.replace("Request.xml", "Response.xml");

			requestHashResponseMap.put(hash, responseResource);
		}
	}


	private static String getHash(String resource) {
		return generateHash(new ResourceContentReader(resource).getContent());
	}


	private static String generateHash(final String content) {
		return new HashBuilder(XmlNormalizer.removeIndents(content.trim())).getSha256();
	}


	@Override
	public RawResponse doRequest(RawRequest request, ConnectionData cd) {
		final long start = System.currentTimeMillis();

		final String hash = generateHash(request.getContent());
		log.debug("Get response for hash: " + hash);
		final String responseResource = requestHashResponseMap.get(hash);

		if (responseResource == null) {
			log.warn("No response found!");
			return new RawResponse(Status.ERROR, "No response for hash: " + hash);
		}

		log.debug("Read response from: " + responseResource);
		final String content = new ResourceContentReader(responseResource).getContent();

		return new RawResponse(Status.OK, content, System.currentTimeMillis() - start);
	}
}
