package com.sofort.lib.paycode.internal.transformer.common;

import com.sofort.lib.core.internal.utils.xml.XmlElementRenderable;
import com.sofort.lib.core.internal.utils.xml.XmlElementRenderer;
import com.sofort.lib.paycode.products.common.PaycodeTransactionStatusReason;


/**
 * The renderer for {@link PaycodeTransactionStatusReason}.
 */
public class PaycodeStatusReasonRenderer extends XmlElementRenderer<PaycodeTransactionStatusReason> {

    /*
     * (non-Javadoc)
     *
     * @see
     * com.sofort.lib.ideal.ideal.refund.refund.billcode.billcode.paycode.paycode.payment.payment.core.core.internal.utils.xml.XmlElementRenderer#render(java.lang
     * .Object, com.sofort.lib.ideal.ideal.refund.refund.billcode.billcode.paycode.paycode.payment.payment.core.core.internal.utils.xml.XmlElementRenderable)
     */
    @Override
    public void render(PaycodeTransactionStatusReason statusReason, XmlElementRenderable element) {
        element.append("status_reason", statusReason.name().toLowerCase());
    }
}
