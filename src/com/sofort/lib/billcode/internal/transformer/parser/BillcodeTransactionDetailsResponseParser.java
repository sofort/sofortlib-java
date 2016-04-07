package com.sofort.lib.billcode.internal.transformer.parser;

import com.sofort.lib.billcode.internal.transformer.renderer.parts.BillcodeTransactionDetailsParser;
import com.sofort.lib.billcode.products.response.BillcodeTransactionDetailsResponse;
import com.sofort.lib.core.internal.transformer.parser.SofortLibResponseParser;
import com.sofort.lib.core.internal.utils.xml.XmlElementParsable;


/**
 * The parser for {@link BillcodeTransactionDetailsResponse}.
 */
public class BillcodeTransactionDetailsResponseParser extends SofortLibResponseParser<BillcodeTransactionDetailsResponse> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sofort.lib.ideal.ideal.refund.refund.billcode.billcode.paycode.paycode.payment.payment.core.core.internal.transformer.parser.XmlResponseParser#parse(com
	 * .sofort.lib.internal.utils.xml.XmlElementParsable)
	 */
	@Override
	public BillcodeTransactionDetailsResponse parseResponse(XmlElementParsable element) {
		BillcodeTransactionDetailsResponse response = new BillcodeTransactionDetailsResponse();

		response.setTransactionDetailsList(new BillcodeTransactionDetailsParser().parseChildren(element, "transaction_details"));

		return response;
	}
}
