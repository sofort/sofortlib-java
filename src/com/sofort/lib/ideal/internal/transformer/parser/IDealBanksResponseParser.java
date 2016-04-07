package com.sofort.lib.ideal.internal.transformer.parser;

import java.util.List;

import com.sofort.lib.core.internal.transformer.parser.SofortLibResponseParser;
import com.sofort.lib.core.internal.transformer.parser.parts.FailureMessageParser;
import com.sofort.lib.core.internal.utils.xml.XmlElementParsable;
import com.sofort.lib.core.products.response.parts.FailureMessage;
import com.sofort.lib.ideal.internal.transformer.parser.parts.IDealBankParser;
import com.sofort.lib.ideal.products.response.IDealBanksResponse;


/**
 * The parser for {@link IDealBanksResponse}.
 */
public class IDealBanksResponseParser extends SofortLibResponseParser<IDealBanksResponse> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sofort.lib.ideal.ideal.refund.refund.billcode.billcode.paycode.
	 * paycode.payment.payment.core.core.internal.transformer.parser.
	 * XmlResponseParser#parse(com
	 * .sofort.lib.internal.utils.xml.XmlElementParsable)
	 */
	@Override
	public IDealBanksResponse parseResponse(XmlElementParsable element) {
		IDealBanksResponse response = new IDealBanksResponse();

		XmlElementParsable banksRoot = element.getChild("banks");
		response.setBanks(new IDealBankParser().parseChildren(banksRoot, "bank"));

		List<FailureMessage> errors = new FailureMessageParser().parseChildren(element, "error");
		response.setErrors(errors == null || errors.isEmpty() ? null : errors);

		return response;
	}
}
