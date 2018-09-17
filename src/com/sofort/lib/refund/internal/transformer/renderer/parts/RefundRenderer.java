package com.sofort.lib.refund.internal.transformer.renderer.parts;

import com.sofort.lib.core.internal.utils.xml.XmlElementRenderable;
import com.sofort.lib.core.internal.utils.xml.XmlElementRenderer;
import com.sofort.lib.refund.products.request.parts.Refund;


/**
 * The renderer for {@link Refund}.
 */
public class RefundRenderer extends XmlElementRenderer<Refund> {

    /*
     * (non-Javadoc)
     *
     * @see
     * com.sofort.lib.ideal.ideal.refund.refund.billcode.billcode.paycode.paycode.payment.payment.core.core.internal.utils.xml.XmlElementRenderer#render(java.lang
     * .Object, com.sofort.lib.ideal.ideal.refund.refund.billcode.billcode.paycode.paycode.payment.payment.core.core.internal.utils.xml.XmlElementRenderable)
     */
    @Override
    public void render(Refund refund, XmlElementRenderable element) {
        element.append("transaction", refund.getTransId());
        element.append("amount", refund.getAmount());
        element.append("comment", refund.getComment());
        element.append("reason_1", refund.getReason1());
        element.append("reason_2", refund.getReason2());
        element.append("partial_refund_id", refund.getPartialRefundId());
    }
}
