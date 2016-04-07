package com.sofort.lib.core.internal.transformer.parser.parts;

import com.sofort.lib.core.internal.utils.xml.XmlElementParsable;
import com.sofort.lib.core.internal.utils.xml.XmlElementParser;
import com.sofort.lib.core.products.response.parts.FailureMessage;


/**
 * The Class FailureMessageParser.
 */
public class FailureMessageParser extends XmlElementParser<FailureMessage> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sofort.lib.ideal.ideal.refund.refund.billcode.billcode.paycode.paycode.payment.payment.core.core.internal.utils.xml.XmlElementParser#parseChildImpl(com
	 * .sofort.lib.internal.utils.xml.XmlElementParsable)
	 */
	@Override
	protected FailureMessage parseChildImpl(XmlElementParsable element) {

		String code = element.getChildText("code");
		String field = element.getChildText("field");
		String message = element.getChildText("message");

		return new FailureMessage(code, message, field);
	}

}
