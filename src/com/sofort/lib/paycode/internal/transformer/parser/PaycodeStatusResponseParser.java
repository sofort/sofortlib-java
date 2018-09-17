package com.sofort.lib.paycode.internal.transformer.parser;

import com.sofort.lib.core.internal.transformer.parser.SofortLibResponseParser;
import com.sofort.lib.core.internal.transformer.parser.parts.BankParser;
import com.sofort.lib.core.internal.transformer.parser.parts.StringParser;
import com.sofort.lib.core.internal.utils.xml.XmlElementParsable;
import com.sofort.lib.paycode.products.response.PaycodeResponse;
import com.sofort.lib.paycode.products.response.PaycodeStatusResponse;
import com.sofort.lib.paycode.products.response.parts.PaycodeStatus;


/**
 * The parser for {@link PaycodeResponse}.
 */
public class PaycodeStatusResponseParser extends SofortLibResponseParser<PaycodeStatusResponse> {

    /*
     * (non-Javadoc)
     *
     * @see
     * com.sofort.lib.ideal.ideal.refund.refund.billcode.billcode.paycode.paycode.payment.payment.core.core.internal.transformer.parser.XmlResponseParser#parse(com
     * .sofort.lib.internal.utils.xml.XmlElementParsable)
     */
    @Override
    public PaycodeStatusResponse parseResponse(XmlElementParsable element) {

        PaycodeStatusResponse response = new PaycodeStatusResponse();

        response.setStatus(PaycodeStatus.get(element.getChildText("status")));
        response.setPaycode(element.getChildText("paycode"));
        response.setProjectId(element.getChildTextAsInt("project_id"));
        response.setTransId(element.getChildText("transaction"));
        response.setAmount(element.getChildTextAsDouble("amount"));
        response.setReasons(new StringParser().parseChildren(element.getChild("reasons"), "reason"));
        response.setTimeCreated(element.getChildTextAsDate("time_created"));
        response.setTimeUsed(element.getChildTextAsDate("time_used"));
        response.setStartDate(element.getChildTextAsDate("start_date"));
        response.setEndDate(element.getChildTextAsDate("end_date"));
        response.setCurrencyCode(element.getChildText("currency_code"));
        response.setLanguageCode(element.getChildText("language_code"));
        response.setSender(new BankParser().parseChild(element.getChild("sender")));
        response.setUserVariables(new StringParser().parseChildren(element.getChild("user_variables"), "variable"));

        return response;
    }
}
