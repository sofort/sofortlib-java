package com.sofort.lib.payment.internal.transformer.parser.parts;

import com.sofort.lib.core.internal.utils.xml.XmlElementParsable;
import com.sofort.lib.core.internal.utils.xml.XmlElementParser;
import com.sofort.lib.payment.products.response.parts.PaymentStatus;
import com.sofort.lib.payment.products.response.parts.PaymentStatusHistoryItem;
import com.sofort.lib.payment.products.response.parts.PaymentStatusReason;


/**
 * The parser for {@link PaymentStatusHistoryItem}.
 */
public class PaymentStatusHistoryItemParser extends XmlElementParser<PaymentStatusHistoryItem> {

    /*
     * (non-Javadoc)
     *
     * @see
     * com.sofort.lib.ideal.ideal.refund.refund.billcode.billcode.paycode.paycode.payment.payment.core.core.internal.utils.xml.XmlElementParser#parseChildImpl(com
     * .sofort.lib.internal.utils.xml.XmlElementParsable)
     */
    @Override
    protected PaymentStatusHistoryItem parseChildImpl(XmlElementParsable element) {
        PaymentStatusHistoryItem statusHistoryItem = new PaymentStatusHistoryItem();

        statusHistoryItem.setStatus(PaymentStatus.get(element.getChildText("status")));
        statusHistoryItem.setStatusReason(PaymentStatusReason.get(element.getChildText("status_reason")));
        statusHistoryItem.setTime(element.getChildTextAsDate("time"));

        return statusHistoryItem;
    }
}
