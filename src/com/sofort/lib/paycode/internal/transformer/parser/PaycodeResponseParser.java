package com.sofort.lib.paycode.internal.transformer.parser;

import java.util.List;

import com.sofort.lib.core.internal.transformer.parser.SofortLibResponseParser;
import com.sofort.lib.core.internal.transformer.parser.parts.FailureMessageParser;
import com.sofort.lib.core.internal.utils.xml.XmlElementParsable;
import com.sofort.lib.core.products.response.parts.FailureMessage;
import com.sofort.lib.paycode.products.response.PaycodeResponse;


/**
 * The parser for {@link PaycodeResponse}.
 */
public class PaycodeResponseParser extends SofortLibResponseParser<PaycodeResponse> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sofort.lib.ideal.ideal.refund.refund.billcode.billcode.paycode.paycode.payment.payment.core.core.internal.transformer.parser.XmlResponseParser#parse(com
	 * .sofort.lib.internal.utils.xml.XmlElementParsable)
	 */
	@Override
	public PaycodeResponse parseResponse(XmlElementParsable element) {

		PaycodeResponse response = new PaycodeResponse();

		response.setPaycode(element.getChildText("paycode"));
		response.setPaycodeUrl(element.getChildText("paycode_url"));

		XmlElementParsable warningsRoot = element.getChild("warnings");
		List<FailureMessage> warnings = new FailureMessageParser().parseChildren(warningsRoot, "warning");
		response.setWarnings(warnings == null || warnings.isEmpty() ? null : warnings);

		return response;
	}
}
