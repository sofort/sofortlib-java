package com.sofort.lib.billcode.internal.transformer.common;

import com.sofort.lib.billcode.products.common.BillcodeTransactionStatusReason;
import com.sofort.lib.core.internal.utils.xml.XmlElementRenderable;
import com.sofort.lib.core.internal.utils.xml.XmlElementRenderer;


/**
 * The renderer for {@link BillcodeTransactionStatusReason}.
 */
public class BillcodeStatusReasonRenderer extends XmlElementRenderer<BillcodeTransactionStatusReason> {

    /*
     * (non-Javadoc)
     *
     * @see
     * com.sofort.lib.ideal.ideal.refund.refund.billcode.billcode.paycode.paycode.payment.payment.core.core.internal.utils.xml.XmlElementRenderer#render(java.lang
     * .Object, com.sofort.lib.ideal.ideal.refund.refund.billcode.billcode.paycode.paycode.payment.payment.core.core.internal.utils.xml.XmlElementRenderable)
     */
    @Override
    public void render(BillcodeTransactionStatusReason statusReason, XmlElementRenderable element) {
        element.append("status_reason", statusReason.name().toLowerCase());
    }
}
