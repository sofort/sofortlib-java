package com.sofort.lib.billcode.internal.transformer.parser;

import com.sofort.lib.billcode.products.response.BillcodeResponse;
import com.sofort.lib.billcode.products.response.BillcodeStatusResponse;
import com.sofort.lib.billcode.products.response.parts.BillcodeStatus;
import com.sofort.lib.core.internal.transformer.parser.SofortLibResponseParser;
import com.sofort.lib.core.internal.transformer.parser.parts.BankParser;
import com.sofort.lib.core.internal.transformer.parser.parts.StringParser;
import com.sofort.lib.core.internal.utils.xml.XmlElementParsable;


/**
 * The parser for {@link BillcodeResponse}.
 */
public class BillcodeStatusResponseParser extends SofortLibResponseParser<BillcodeStatusResponse> {

    /*
     * (non-Javadoc)
     *
     * @see
     * com.sofort.lib.ideal.ideal.refund.refund.billcode.billcode.paycode.paycode.payment.payment.core.core.internal.transformer.parser.XmlResponseParser#parse(com
     * .sofort.lib.internal.utils.xml.XmlElementParsable)
     */
    @Override
    public BillcodeStatusResponse parseResponse(XmlElementParsable element) {

        BillcodeStatusResponse response = new BillcodeStatusResponse();

        response.setStatus(BillcodeStatus.get(element.getChildText("status")));
        response.setBillcode(element.getChildText("billcode"));
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
