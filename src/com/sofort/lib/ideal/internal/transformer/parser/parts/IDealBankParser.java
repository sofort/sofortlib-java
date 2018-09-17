package com.sofort.lib.ideal.internal.transformer.parser.parts;

import com.sofort.lib.core.internal.utils.xml.XmlElementParsable;
import com.sofort.lib.core.internal.utils.xml.XmlElementParser;
import com.sofort.lib.ideal.products.response.parts.IDealBank;


/**
 * The parser for {@link IDealBank}.
 */
public class IDealBankParser extends XmlElementParser<IDealBank> {

    /*
     * (non-Javadoc)
     *
     * @see
     * com.sofort.lib.ideal.ideal.refund.refund.billcode.billcode.paycode.paycode.payment.payment.core.core.internal.utils.xml.XmlElementParser#parseChildImpl(com
     * .sofort.lib.internal.utils.xml.XmlElementParsable)
     */
    @Override
    protected IDealBank parseChildImpl(XmlElementParsable element) {
        IDealBank bank = new IDealBank();

        bank.setCode(element.getChildText("code"));
        bank.setName(element.getChildText("name"));

        return bank;
    }

}
