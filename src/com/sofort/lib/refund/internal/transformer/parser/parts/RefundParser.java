package com.sofort.lib.refund.internal.transformer.parser.parts;

import com.sofort.lib.core.internal.transformer.parser.parts.FailureMessageParser;
import com.sofort.lib.core.internal.utils.xml.XmlElementParsable;
import com.sofort.lib.core.internal.utils.xml.XmlElementParser;
import com.sofort.lib.refund.products.response.parts.Refund;


/**
 * The parser for {@link Refund}.
 */
public class RefundParser extends XmlElementParser<Refund> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sofort.lib.ideal.ideal.refund.refund.billcode.billcode.paycode.paycode.payment.payment.core.core.internal.utils.xml.XmlElementParser#parseChildImpl(com
	 * .sofort.lib.internal.utils.xml.XmlElementParsable)
	 */
	@Override
	protected Refund parseChildImpl(XmlElementParsable element) {

		Refund refund = new Refund(
				element.getChildText("transaction"),
				element.getChildTextAsDouble("amount"),
				element.getChildText("partial_refund_id"),
				element.getChildText("status"));

		refund.setRecipient(new RefundBankAccountParser().parseChild(element.getChild("recipient")));
		refund.setComment(element.getChildText("comment"));
		refund.setReason1(element.getChildText("reason_1"));
		refund.setReason2(element.getChildText("reason_2"));
		refund.setTime(element.getChildTextAsDate("time"));

		refund.setErrors(new FailureMessageParser().parseChildren(element.getChild("errors"), "error"));

		return refund;
	}
}
