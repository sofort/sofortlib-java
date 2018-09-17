package com.sofort.lib.paycode.internal.transformer.parser.parts;


import com.sofort.lib.core.internal.transformer.parser.parts.TransactionDetailsParser;
import com.sofort.lib.core.internal.utils.xml.XmlElementParsable;
import com.sofort.lib.core.internal.utils.xml.XmlElementParser;
import com.sofort.lib.paycode.products.common.PaycodeTransactionStatus;
import com.sofort.lib.paycode.products.common.PaycodeTransactionStatusReason;
import com.sofort.lib.paycode.products.response.parts.PaycodeTransactionDetails;

import static com.sofort.lib.core.Logger.log;


/**
 * The parser for {@link PaycodeTransactionDetails}.
 */
public class PaycodeTransactionDetailsParser extends XmlElementParser<PaycodeTransactionDetails> {

    /*
     * (non-Javadoc)
     *
     * @see
     * com.sofort.lib.ideal.ideal.refund.refund.billcode.billcode.paycode.paycode.payment.payment.core.core.internal.utils.xml.XmlElementParser#parseChildImpl(com
     * .sofort.lib.internal.utils.xml.XmlElementParsable)
     */
    @Override
    protected PaycodeTransactionDetails parseChildImpl(XmlElementParsable element) {

        String paymentMethod = TransactionDetailsParser.getPaymentMethod(element);
        if (paymentMethod == null || !paymentMethod.equalsIgnoreCase("paycode")) {
            log.warn("The product (payment method: '" + paymentMethod + "') is not supported.");
            return null;
        }

        PaycodeTransactionDetails details = new PaycodeTransactionDetails();

        new TransactionDetailsParser().parseTransactionDetails(details, element);

        details.setStatus(PaycodeTransactionStatus.get(element.getChildText("status")));
        details.setStatusReason(PaycodeTransactionStatusReason.get(element.getChildText("status_reason")));
        details.setStatusHistoryItems(new PaycodeStatusHistoryItemParser().parseChildren(element.getChild("status_history_items"), "status_history_item"));
        details.setStatusModified(element.getChildTextAsDate("status_modified"));
        details.setEmailCustomer(element.getChildText("email_customer"));
        details.setPhoneCustomer(element.getChildText("phone_customer"));

        details.setPaycode(element.getChild("paycode").getChildText("code"));

        return details;
    }
}
