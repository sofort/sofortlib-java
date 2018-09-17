package com.sofort.lib.paycode.internal.transformer.common;

import com.sofort.lib.core.internal.utils.xml.XmlElementRenderable;
import com.sofort.lib.core.internal.utils.xml.XmlElementRenderer;
import com.sofort.lib.paycode.products.common.PaycodeTransactionStatus;


/**
 * The renderer for PaycodeStatus.
 */
public class PaycodeStatusRenderer extends XmlElementRenderer<PaycodeTransactionStatus> {

    /*
     * (non-Javadoc)
     *
     * @see
     * com.sofort.lib.ideal.ideal.refund.refund.billcode.billcode.paycode.paycode.payment.payment.core.core.internal.utils.xml.XmlElementRenderer#render(java.lang
     * .Object, com.sofort.lib.ideal.ideal.refund.refund.billcode.billcode.paycode.paycode.payment.payment.core.core.internal.utils.xml.XmlElementRenderable)
     */
    @Override
    public void render(PaycodeTransactionStatus status, XmlElementRenderable element) {
        element.append("status", status.name().toLowerCase());
    }
}
