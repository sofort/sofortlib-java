package com.sofort.lib.paycode.internal.transformer.xml;

import com.sofort.lib.core.internal.transformer.xml.XmlConfig;
import com.sofort.lib.core.internal.transformer.xml.XmlRootEntry;
import com.sofort.lib.core.internal.utils.Attribute;
import com.sofort.lib.paycode.internal.transformer.parser.PaycodeResponseParser;
import com.sofort.lib.paycode.internal.transformer.parser.PaycodeStatusResponseParser;
import com.sofort.lib.paycode.internal.transformer.parser.PaycodeTransactionDetailsResponseParser;
import com.sofort.lib.paycode.internal.transformer.renderer.PaycodeRequestRenderer;
import com.sofort.lib.paycode.internal.transformer.renderer.PaycodeStatusRequestRenderer;
import com.sofort.lib.paycode.internal.transformer.renderer.PaycodeTransactionDetailsRequestRenderer;
import com.sofort.lib.paycode.products.request.PaycodeRequest;
import com.sofort.lib.paycode.products.request.PaycodeStatusRequest;
import com.sofort.lib.paycode.products.request.PaycodeTransactionDetailsRequest;
import com.sofort.lib.paycode.products.response.PaycodeResponse;
import com.sofort.lib.paycode.products.response.PaycodeStatusResponse;
import com.sofort.lib.paycode.products.response.PaycodeTransactionDetailsResponse;


/**
 * The XML root/parser/renderer definition for SOFORT Paycode requests and
 * responses.
 */
public class XmlConfigPaycode extends XmlConfig {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sofort.lib.ideal.ideal.refund.refund.billcode.billcode.paycode.paycode.payment.payment.core.core.internal.transformer.xml.XmlConfig#initRootEntryMapping()
	 */
	@Override
	protected void initRootEntryMapping() {
		rootEntryMapping.put(PaycodeRequest.class, new XmlRootEntry("paycode"));
		rootEntryMapping.put(PaycodeStatusRequest.class, new XmlRootEntry("paycode_request"));
		rootEntryMapping.put(PaycodeTransactionDetailsRequest.class, new XmlRootEntry("transaction_request", new Attribute("version", "2.0")));
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sofort.lib.ideal.ideal.refund.refund.billcode.billcode.paycode.paycode.payment.payment.core.core.internal.transformer.xml.XmlConfig#initRendererMapping()
	 */
	@Override
	protected void initRendererMapping() {
		rendererMapping.put(PaycodeRequest.class, new PaycodeRequestRenderer());
		rendererMapping.put(PaycodeStatusRequest.class, new PaycodeStatusRequestRenderer());
		rendererMapping.put(PaycodeTransactionDetailsRequest.class, new PaycodeTransactionDetailsRequestRenderer());
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sofort.lib.ideal.ideal.refund.refund.billcode.billcode.paycode.paycode.payment.payment.core.core.internal.transformer.xml.XmlConfig#initParserMapping()
	 */
	@Override
	protected void initParserMapping() {
		parserMapping.put(PaycodeResponse.class, new PaycodeResponseParser());
		parserMapping.put(PaycodeStatusResponse.class, new PaycodeStatusResponseParser());
		parserMapping.put(PaycodeTransactionDetailsResponse.class, new PaycodeTransactionDetailsResponseParser());
	}

}
