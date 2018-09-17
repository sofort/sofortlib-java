package com.sofort.lib.core.internal.transformer.parser;

import com.sofort.lib.core.internal.transformer.parser.parts.FailureMessageParser;
import com.sofort.lib.core.internal.utils.xml.XmlElementParsable;
import com.sofort.lib.core.products.response.SofortLibResponse;
import com.sofort.lib.core.products.response.parts.FailureMessage;

import java.util.List;


public abstract class SofortLibResponseParser<T extends SofortLibResponse> implements XmlResponseParser<T> {

    /*
     * (non-Javadoc)
     *
     * @see
     * com.sofort.lib.ideal.ideal.refund.refund.billcode.billcode.paycode.paycode.payment.payment.core.core.internal.transformer.parser.XmlResponseParser#parse(com
     * .sofort.lib.internal.utils.xml.XmlElementParsable)
     */
    @Override
    public T parse(XmlElementParsable element) {
        T response = parseResponse(element);

        if (element.getName().equals("errors")) {
            List<FailureMessage> errors = new FailureMessageParser().parseChildren(element, "error");
            response.setResponseErrors(errors);
        }

        if (element.getName().equals("warnings")) {
            List<FailureMessage> warnings = new FailureMessageParser().parseChildren(element, "warning");
            response.setResponseWarnings(warnings);
        }

        return response;
    }


    protected abstract T parseResponse(XmlElementParsable element);
}
