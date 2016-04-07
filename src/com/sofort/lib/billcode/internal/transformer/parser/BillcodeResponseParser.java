package com.sofort.lib.billcode.internal.transformer.parser;

import java.util.List;

import com.sofort.lib.billcode.products.response.BillcodeResponse;
import com.sofort.lib.core.internal.transformer.parser.SofortLibResponseParser;
import com.sofort.lib.core.internal.transformer.parser.parts.FailureMessageParser;
import com.sofort.lib.core.internal.utils.xml.XmlElementParsable;
import com.sofort.lib.core.products.response.parts.FailureMessage;


/**
 * The parser for {@link BillcodeResponse}.
 */
public class BillcodeResponseParser extends SofortLibResponseParser<BillcodeResponse> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sofort.lib.ideal.ideal.refund.refund.billcode.billcode.paycode.paycode.payment.payment.core.core.internal.transformer.parser.XmlResponseParser#parse(com
	 * .sofort.lib.internal.utils.xml.XmlElementParsable)
	 */
	@Override
	public BillcodeResponse parseResponse(XmlElementParsable element) {

		BillcodeResponse response = new BillcodeResponse();

		response.setBillcode(element.getChildText("billcode"));
		response.setBillcodeUrl(element.getChildText("billcode_url"));

		XmlElementParsable warningsRoot = element.getChild("warnings");
		List<FailureMessage> warnings = new FailureMessageParser().parseChildren(warningsRoot, "warning");
		response.setWarnings(warnings == null || warnings.isEmpty() ? null : warnings);

		return response;
	}
}
