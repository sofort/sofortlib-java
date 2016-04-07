package com.sofort.lib.core.internal.transformer.parser.parts;

import com.sofort.lib.core.internal.utils.xml.XmlElementParsable;
import com.sofort.lib.core.internal.utils.xml.XmlElementParser;


/**
 * Simple XML text parser.
 */
public class StringParser extends XmlElementParser<String> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sofort.lib.ideal.ideal.refund.refund.billcode.billcode.paycode.paycode.payment.payment.core.core.internal.utils.xml.XmlElementParser#parseChildImpl(com
	 * .sofort.lib.internal.utils.xml.XmlElementParsable)
	 */
	@Override
	protected String parseChildImpl(XmlElementParsable element) {
		return element.getText();
	}

}
