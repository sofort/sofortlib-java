package com.sofort.lib.refund.internal.transformer.parser;

import com.sofort.lib.core.internal.transformer.parser.SofortLibResponseParser;
import com.sofort.lib.core.internal.transformer.parser.parts.FailureMessageParser;
import com.sofort.lib.core.internal.utils.StringUtilities;
import com.sofort.lib.core.internal.utils.xml.XmlElementParsable;
import com.sofort.lib.refund.internal.transformer.parser.parts.RefundBankAccountParser;
import com.sofort.lib.refund.internal.transformer.parser.parts.RefundParser;
import com.sofort.lib.refund.products.response.RefundResponse;


/**
 * The parser for {@link RefundResponse}.
 */
public class RefundResponseParser extends SofortLibResponseParser<RefundResponse> {

    /*
     * (non-Javadoc)
     *
     * @see com.sofort.lib.ideal.ideal.refund.refund.billcode.billcode.paycode.
     * paycode.payment.payment.core.core.internal.transformer.parser.
     * SofortLibResponseParser#
     * parseResponse(com.sofort.lib.ideal.ideal.refund.refund.billcode.billcode.
     * paycode.paycode.payment.payment.core.core.internal.utils.xml.
     * XmlElementParsable)
     */
    @Override
    public RefundResponse parseResponse(XmlElementParsable element) {
        RefundResponse refundResponse = new RefundResponse(
                element.getChildText("title"),
                new RefundBankAccountParser().parseChild(element.getChild("sender")),
                new RefundParser().parseChildren(element, "refund"));

        String pain = element.getChildText("pain");
        refundResponse.setPain(pain != null ? new StringUtilities().base64decode(pain) : null);

        refundResponse.setErrors(new FailureMessageParser().parseChildren(element.getChild("errors"), "error"));

        return refundResponse;
    }
}
