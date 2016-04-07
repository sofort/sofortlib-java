package com.sofort.lib.core.internal.transformer.parser.parts;

import com.sofort.lib.core.internal.utils.xml.XmlElementParsable;
import com.sofort.lib.core.internal.utils.xml.XmlElementParser;
import com.sofort.lib.core.products.common.Bank;


/**
 * The parser for {@link Bank}.
 */
public class BankParser extends XmlElementParser<Bank> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sofort.lib.ideal.ideal.refund.refund.billcode.billcode.paycode.
	 * paycode.payment.payment.core.core.internal.utils.xml.XmlElementParser#
	 * parseChildImpl(com .sofort.lib.internal.utils.xml.XmlElementParsable)
	 */
	@SuppressWarnings("deprecation")
	@Override
	protected Bank parseChildImpl(XmlElementParsable element) {
		Bank senderBank = new Bank();

		senderBank.setBankCode(element.getChildText("bank_code"));
		senderBank.setBic(element.getChildText("bic"));
		senderBank.setCountryCode(element.getChildText("country_code"));

		return senderBank;
	}
}
