package com.sofort.lib.core.internal.transformer.renderer.parts;

import com.sofort.lib.core.internal.utils.xml.XmlElementRenderable;
import com.sofort.lib.core.internal.utils.xml.XmlElementRenderer;
import com.sofort.lib.core.products.common.Bank;


/**
 * The Class senderBankRenderer.
 */
public class BankRenderer extends XmlElementRenderer<Bank> {

    /*
     * (non-Javadoc)
     *
     * @see com.sofort.lib.ideal.ideal.refund.refund.billcode.billcode.paycode.
     * paycode.payment.payment.core.core.internal.utils.xml.XmlElementRenderer#
     * render(java.lang .Object,
     * com.sofort.lib.ideal.ideal.refund.refund.billcode.billcode.paycode.
     * paycode.payment.payment.core.core.internal.utils.xml.
     * XmlElementRenderable)
     */
    @SuppressWarnings("deprecation")
    @Override
    public void render(Bank senderBank, XmlElementRenderable element) {
        element.append("bank_code", senderBank.getBankCode());
        element.append("bic", senderBank.getBic());
        element.append("country_code", senderBank.getCountryCode());
    }
}
