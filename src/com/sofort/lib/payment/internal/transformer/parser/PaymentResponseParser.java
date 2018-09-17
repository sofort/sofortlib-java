package com.sofort.lib.payment.internal.transformer.parser;

import com.sofort.lib.core.internal.transformer.parser.SofortLibResponseParser;
import com.sofort.lib.core.internal.transformer.parser.parts.FailureMessageParser;
import com.sofort.lib.core.internal.utils.xml.XmlElementParsable;
import com.sofort.lib.core.products.response.parts.FailureMessage;
import com.sofort.lib.payment.products.response.PaymentResponse;

import java.util.List;


/**
 * The parser for {@link PaymentResponse}.
 */
public class PaymentResponseParser extends SofortLibResponseParser<PaymentResponse> {

    /*
     * (non-Javadoc)
     *
     * @see
     * com.sofort.lib.ideal.ideal.refund.refund.billcode.billcode.paycode.paycode.payment.payment.core.core.internal.transformer.parser.XmlResponseParser#parse(com
     * .sofort.lib.internal.utils.xml.XmlElementParsable)
     */
    @Override
    public PaymentResponse parseResponse(XmlElementParsable element) {

        PaymentResponse response = new PaymentResponse();

        XmlElementParsable su = element.getChild("su");
        if (su != null) {
            XmlElementParsable errorsRoot = su.getChild("errors");
            List<FailureMessage> errors = new FailureMessageParser().parseChildren(errorsRoot, "error");
            response.setResponsePaymentErrors(errors);
        }

        response.setTransId(element.getChildText("transaction"));
        response.setPaymentUrl(element.getChildText("payment_url"));

        XmlElementParsable warningsRoot = element.getChild("warnings");
        List<FailureMessage> warnings = new FailureMessageParser().parseChildren(warningsRoot, "warning");
        response.setNewPaymentWarnings(warnings == null || warnings.isEmpty() ? null : warnings);

        return response;
    }
}
