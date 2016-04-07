package com.sofort.lib.billcode.internal.transformer.parser.parts;

import com.sofort.lib.billcode.products.common.BillcodeTransactionStatus;
import com.sofort.lib.billcode.products.common.BillcodeTransactionStatusReason;
import com.sofort.lib.billcode.products.response.parts.BillcodeStatusHistoryItem;
import com.sofort.lib.core.internal.utils.xml.XmlElementParsable;
import com.sofort.lib.core.internal.utils.xml.XmlElementParser;


/**
 * The parser for {@link BillcodeStatusHistoryItem}.
 */
public class BillcodeStatusHistoryItemParser extends XmlElementParser<BillcodeStatusHistoryItem> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sofort.lib.ideal.ideal.refund.refund.billcode.billcode.paycode.paycode.payment.payment.core.core.internal.utils.xml.XmlElementParser#parseChildImpl(com
	 * .sofort.lib.internal.utils.xml.XmlElementParsable)
	 */
	@Override
	protected BillcodeStatusHistoryItem parseChildImpl(XmlElementParsable element) {
		BillcodeStatusHistoryItem statusHistoryItem = new BillcodeStatusHistoryItem();

		statusHistoryItem.setStatus(BillcodeTransactionStatus.get(element.getChildText("status")));
		statusHistoryItem.setStatusReason(BillcodeTransactionStatusReason.get(element.getChildText("status_reason")));
		statusHistoryItem.setTime(element.getChildTextAsDate("time"));

		return statusHistoryItem;
	}
}
