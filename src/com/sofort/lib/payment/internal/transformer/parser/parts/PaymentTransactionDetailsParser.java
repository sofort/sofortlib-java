package com.sofort.lib.payment.internal.transformer.parser.parts;

import com.sofort.lib.core.internal.transformer.parser.parts.TransactionDetailsParser;
import com.sofort.lib.core.internal.utils.xml.XmlElementParsable;
import com.sofort.lib.core.internal.utils.xml.XmlElementParser;
import com.sofort.lib.payment.products.response.parts.PaymentStatus;
import com.sofort.lib.payment.products.response.parts.PaymentStatusReason;
import com.sofort.lib.payment.products.response.parts.PaymentTransactionDetails;

import static com.sofort.lib.core.Logger.log;


/**
 * The parser for {@link PaymentTransactionDetails}.
 */
public class PaymentTransactionDetailsParser extends XmlElementParser<PaymentTransactionDetails> {

    /*
     * (non-Javadoc)
     *
     * @see
     * com.sofort.lib.ideal.ideal.refund.refund.billcode.billcode.paycode.paycode.payment.payment.core.core.internal.utils.xml.XmlElementParser#parseChildImpl(com
     * .sofort.lib.internal.utils.xml.XmlElementParsable)
     */
    @Override
    protected PaymentTransactionDetails parseChildImpl(XmlElementParsable element) {

        String paymentMethod = TransactionDetailsParser.getPaymentMethod(element);
        if (!paymentMethod.equalsIgnoreCase("su")) {
            log.warn("Product '" + paymentMethod + "' is not supported.");
            return null;
        }

        PaymentTransactionDetails details = new PaymentTransactionDetails();
        new TransactionDetailsParser().parseTransactionDetails(details, element);

        details.setStatus(PaymentStatus.get(element.getChildText("status")));
        details.setStatusReason(PaymentStatusReason.get(element.getChildText("status_reason")));
        details.setStatusHistoryItems(new PaymentStatusHistoryItemParser().parseChildren(element.getChild("status_history_items"), "status_history_item"));
        details.setStatusModified(element.getChildTextAsDate("status_modified"));
        details.setEmailCustomer(element.getChildText("email_customer"));
        details.setPhoneCustomer(element.getChildText("phone_customer"));

        XmlElementParsable child = element.getChild(paymentMethod);
        details.setConsumerProtection(child.getChildTextAsBoolean("consumer_protection"));

        return details;
    }
}
