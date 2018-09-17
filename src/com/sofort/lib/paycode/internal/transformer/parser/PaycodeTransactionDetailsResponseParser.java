package com.sofort.lib.paycode.internal.transformer.parser;

import com.sofort.lib.core.internal.transformer.parser.SofortLibResponseParser;
import com.sofort.lib.core.internal.utils.xml.XmlElementParsable;
import com.sofort.lib.paycode.internal.transformer.parser.parts.PaycodeTransactionDetailsParser;
import com.sofort.lib.paycode.products.response.PaycodeTransactionDetailsResponse;


/**
 * The parser for {@link PaycodeTransactionDetailsResponse}.
 */
public class PaycodeTransactionDetailsResponseParser extends SofortLibResponseParser<PaycodeTransactionDetailsResponse> {

    /*
     * (non-Javadoc)
     *
     * @see
     * com.sofort.lib.ideal.ideal.refund.refund.billcode.billcode.paycode.paycode.payment.payment.core.core.internal.transformer.parser.XmlResponseParser#parse(com
     * .sofort.lib.internal.utils.xml.XmlElementParsable)
     */
    @Override
    public PaycodeTransactionDetailsResponse parseResponse(XmlElementParsable element) {
        PaycodeTransactionDetailsResponse response = new PaycodeTransactionDetailsResponse();

        response.setTransactionDetailsList(new PaycodeTransactionDetailsParser().parseChildren(element, "transaction_details"));

        return response;
    }
}
