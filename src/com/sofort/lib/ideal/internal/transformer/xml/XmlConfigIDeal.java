package com.sofort.lib.ideal.internal.transformer.xml;

import com.sofort.lib.core.internal.transformer.xml.XmlConfig;
import com.sofort.lib.core.internal.transformer.xml.XmlRootEntry;
import com.sofort.lib.ideal.internal.transformer.parser.IDealBanksResponseParser;
import com.sofort.lib.ideal.internal.transformer.renderer.IDealBanksRequestRenderer;
import com.sofort.lib.ideal.products.request.IDealBanksRequest;
import com.sofort.lib.ideal.products.response.IDealBanksResponse;


/**
 * The XML root/renderer/parser definition for iDEAL bank request and response.
 */
public class XmlConfigIDeal extends XmlConfig {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sofort.lib.ideal.ideal.refund.refund.billcode.billcode.paycode.paycode.payment.payment.core.core.internal.transformer.xml.XmlConfig#initRootEntryMapping()
	 */
	@Override
	protected void initRootEntryMapping() {
		rootEntryMapping.put(IDealBanksRequest.class, new XmlRootEntry(null));
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sofort.lib.ideal.ideal.refund.refund.billcode.billcode.paycode.paycode.payment.payment.core.core.internal.transformer.xml.XmlConfig#initRendererMapping()
	 */
	@Override
	protected void initRendererMapping() {
		rendererMapping.put(IDealBanksRequest.class, new IDealBanksRequestRenderer());
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sofort.lib.ideal.ideal.refund.refund.billcode.billcode.paycode.paycode.payment.payment.core.core.internal.transformer.xml.XmlConfig#initParserMapping()
	 */
	@Override
	protected void initParserMapping() {
		parserMapping.put(IDealBanksResponse.class, new IDealBanksResponseParser());
	}

}
