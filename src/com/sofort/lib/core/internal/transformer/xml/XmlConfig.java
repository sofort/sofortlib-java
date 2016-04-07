package com.sofort.lib.core.internal.transformer.xml;

import java.util.HashMap;
import java.util.Map;

import com.sofort.lib.core.internal.transformer.parser.SofortTransactionStatusNotificationParser;
import com.sofort.lib.core.internal.transformer.parser.XmlResponseParser;
import com.sofort.lib.core.internal.transformer.renderer.XmlRequestRenderer;
import com.sofort.lib.core.products.request.SofortLibRequest;
import com.sofort.lib.core.products.response.SofortLibResponse;
import com.sofort.lib.core.products.response.SofortTransactionStatusNotification;


/**
 * XML configuration container for following mapping data for each SofortLib
 * request/response:
 * 
 * 1) the XML root entry for each API request
 * 
 * 2) the XML request render for each API request
 * 
 * 3) the XML response parser for each API response
 */
public abstract class XmlConfig {

	protected final Map<Class<? extends SofortLibRequest>, XmlRootEntry> rootEntryMapping;
	protected final Map<Class<? extends SofortLibRequest>, XmlRequestRenderer> rendererMapping;
	protected final Map<Class<? extends SofortLibResponse>, XmlResponseParser<? extends SofortLibResponse>> parserMapping;


	/**
	 * The default constructor initializes maps and calls all sub initialization
	 * methods.
	 */
	public XmlConfig() {
		rootEntryMapping = new HashMap<Class<? extends SofortLibRequest>, XmlRootEntry>();
		rendererMapping = new HashMap<Class<? extends SofortLibRequest>, XmlRequestRenderer>();
		parserMapping = new HashMap<Class<? extends SofortLibResponse>, XmlResponseParser<? extends SofortLibResponse>>();

		/* core parser initialization */
		parserMapping.put(SofortTransactionStatusNotification.class, new SofortTransactionStatusNotificationParser());

		initRootEntryMapping();
		initRendererMapping();
		initParserMapping();
	}


	/**
	 * Define the root entry mappings for SofortLib requests.
	 */
	protected abstract void initRootEntryMapping();


	/**
	 * Define the renderer mappings for SofortLib requests.
	 */
	protected abstract void initRendererMapping();


	/**
	 * Define the parser mappings for SofortLib responses.
	 */
	protected abstract void initParserMapping();


	/**
	 * Returns the XML root entry defined for given {@link SofortLibRequest}
	 * class.
	 * 
	 * @param requestClass
	 *            {@link SofortLibRequest} class
	 * @return defined XML root entry
	 */
	public XmlRootEntry getConfigEntry(Class<? extends SofortLibRequest> requestClass) {
		return rootEntryMapping.get(requestClass);
	}


	/**
	 * Returns the XML request render defined for given {@link SofortLibRequest}
	 * class.
	 * 
	 * @param requestClass
	 *            request class
	 * @return defined XML request renderer
	 */
	public XmlRequestRenderer getRequestRenderer(Class<? extends SofortLibRequest> requestClass) {
		return rendererMapping.get(requestClass);
	}


	/**
	 * Returns the XML response parser defined for given
	 * {@link SofortLibResponse} class.
	 * 
	 * @param responseClass
	 *            response class
	 * @return defined XML response parser
	 */
	public XmlResponseParser<? extends SofortLibResponse> getResponseParser(Class<? extends SofortLibResponse> responseClass) {
		return parserMapping.get(responseClass);
	}

}
