package com.sofort.lib.billcode.internal.transformer.common;

import com.sofort.lib.billcode.products.common.BillcodeTransactionStatus;
import com.sofort.lib.core.internal.utils.xml.XmlElementRenderable;
import com.sofort.lib.core.internal.utils.xml.XmlElementRenderer;


/**
 * The renderer for BillcodeStatus.
 */
public class BillcodeStatusRenderer extends XmlElementRenderer<BillcodeTransactionStatus> {

    /*
     * (non-Javadoc)
     *
     * @see
     * com.sofort.lib.ideal.ideal.refund.refund.billcode.billcode.paycode.paycode.payment.payment.core.core.internal.utils.xml.XmlElementRenderer#render(java.lang
     * .Object, com.sofort.lib.ideal.ideal.refund.refund.billcode.billcode.paycode.paycode.payment.payment.core.core.internal.utils.xml.XmlElementRenderable)
     */
    @Override
    public void render(BillcodeTransactionStatus status, XmlElementRenderable element) {
        element.append("status", status.name().toLowerCase());
    }
}
