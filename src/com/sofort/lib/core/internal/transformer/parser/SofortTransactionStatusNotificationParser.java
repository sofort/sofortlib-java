package com.sofort.lib.core.internal.transformer.parser;

import com.sofort.lib.core.internal.utils.xml.XmlElementParsable;
import com.sofort.lib.core.products.response.SofortTransactionStatusNotification;


/**
 * The Class SofortTransactionStatusNotificationParser.
 */
public class SofortTransactionStatusNotificationParser extends SofortLibResponseParser<SofortTransactionStatusNotification> {

    /*
     * (non-Javadoc)
     *
     * @see com.sofort.lib.ideal.ideal.refund.refund.billcode.billcode.paycode.
     * paycode.payment.payment.core.core.internal.transformer.parser.
     * XmlResponseParser#parse(com
     * .sofort.lib.internal.utils.xml.XmlElementParsable)
     */
    @Override
    public SofortTransactionStatusNotification parseResponse(XmlElementParsable element) {

        return new SofortTransactionStatusNotification(
                element.getChildText("transaction"),
                element.getChildTextAsDate("time"));
    }

}
