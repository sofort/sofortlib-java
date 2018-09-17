package com.sofort.lib.payment.internal.transformer.parser;

import com.sofort.lib.core.internal.transformer.parser.SofortLibResponseParser;
import com.sofort.lib.core.internal.utils.xml.XmlElementParsable;
import com.sofort.lib.payment.internal.transformer.parser.parts.PaymentTransactionDetailsParser;
import com.sofort.lib.payment.products.response.PaymentTransactionDetailsResponse;


/**
 * The parser for {@link PaymentTransactionDetailsResponse}.
 */
public class PaymentTransactionDetailsResponseParser extends SofortLibResponseParser<PaymentTransactionDetailsResponse> {

    /*
     * (non-Javadoc)
     *
     * @see
     * com.sofort.lib.ideal.ideal.refund.refund.billcode.billcode.paycode.paycode.payment.payment.core.core.internal.transformer.parser.XmlResponseParser#parse(com
     * .sofort.lib.internal.utils.xml.XmlElementParsable)
     */
    @Override
    public PaymentTransactionDetailsResponse parseResponse(XmlElementParsable element) {

        return new PaymentTransactionDetailsResponse(
                new PaymentTransactionDetailsParser().parseChildren(element, "transaction_details"));
    }
}
