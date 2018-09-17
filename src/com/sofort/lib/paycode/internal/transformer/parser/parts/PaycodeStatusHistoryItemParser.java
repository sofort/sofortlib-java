package com.sofort.lib.paycode.internal.transformer.parser.parts;

import com.sofort.lib.core.internal.utils.xml.XmlElementParsable;
import com.sofort.lib.core.internal.utils.xml.XmlElementParser;
import com.sofort.lib.paycode.products.common.PaycodeTransactionStatus;
import com.sofort.lib.paycode.products.common.PaycodeTransactionStatusReason;
import com.sofort.lib.paycode.products.response.parts.PaycodeStatusHistoryItem;


/**
 * The parser for {@link PaycodeStatusHistoryItem}.
 */
public class PaycodeStatusHistoryItemParser extends XmlElementParser<PaycodeStatusHistoryItem> {

    /*
     * (non-Javadoc)
     *
     * @see
     * com.sofort.lib.ideal.ideal.refund.refund.billcode.billcode.paycode.paycode.payment.payment.core.core.internal.utils.xml.XmlElementParser#parseChildImpl(com
     * .sofort.lib.internal.utils.xml.XmlElementParsable)
     */
    @Override
    protected PaycodeStatusHistoryItem parseChildImpl(XmlElementParsable element) {
        PaycodeStatusHistoryItem statusHistoryItem = new PaycodeStatusHistoryItem();

        statusHistoryItem.setStatus(PaycodeTransactionStatus.get(element.getChildText("status")));
        statusHistoryItem.setStatusReason(PaycodeTransactionStatusReason.get(element.getChildText("status_reason")));
        statusHistoryItem.setTime(element.getChildTextAsDate("time"));

        return statusHistoryItem;
    }
}
