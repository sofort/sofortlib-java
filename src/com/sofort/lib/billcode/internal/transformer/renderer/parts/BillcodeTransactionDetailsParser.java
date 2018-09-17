package com.sofort.lib.billcode.internal.transformer.renderer.parts;

import com.sofort.lib.billcode.products.common.BillcodeTransactionStatus;
import com.sofort.lib.billcode.products.common.BillcodeTransactionStatusReason;
import com.sofort.lib.billcode.products.response.parts.BillcodeTransactionDetails;
import com.sofort.lib.core.internal.transformer.parser.parts.TransactionDetailsParser;
import com.sofort.lib.core.internal.utils.xml.XmlElementParsable;
import com.sofort.lib.core.internal.utils.xml.XmlElementParser;

import static com.sofort.lib.core.Logger.log;


/**
 * The parser for {@link BillcodeTransactionDetails}.
 */
public class BillcodeTransactionDetailsParser extends XmlElementParser<BillcodeTransactionDetails> {

    /*
     * (non-Javadoc)
     *
     * @see com.sofort.lib.ideal.ideal.refund.refund.billcode.billcode.paycode.
     * paycode.payment.payment.core.core.internal.utils.xml.XmlElementParser#
     * parseChildImpl(com .sofort.lib.internal.utils.xml.XmlElementParsable)
     */
    @Override
    protected BillcodeTransactionDetails parseChildImpl(XmlElementParsable element) {

        String paymentMethod = TransactionDetailsParser.getPaymentMethod(element);
        if (paymentMethod == null || !paymentMethod.equalsIgnoreCase("billcode")) {
            log.warn("The product (payment method: '" + paymentMethod + "') is not supported.");
            return null;
        }

        BillcodeTransactionDetails details = new BillcodeTransactionDetails();

        new TransactionDetailsParser().parseTransactionDetails(details, element);

        details.setStatus(BillcodeTransactionStatus.get(element.getChildText("status")));
        details.setStatusReason(BillcodeTransactionStatusReason.get(element.getChildText("status_reason")));
        details.setStatusHistoryItems(new BillcodeStatusHistoryItemParser().parseChildren(element.getChild("status_history_items"), "status_history_item"));
        details.setStatusModified(element.getChildTextAsDate("status_modified"));
        details.setEmailCustomer(element.getChildText("email_customer"));
        details.setPhoneCustomer(element.getChildText("phone_customer"));

        details.setBillcode(element.getChild("billcode").getChildText("code"));

        return details;
    }
}
