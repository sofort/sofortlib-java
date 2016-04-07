package com.sofort.lib.payment.internal.transformer.renderer.parts;

import com.sofort.lib.core.internal.utils.xml.XmlElementRenderable;
import com.sofort.lib.core.internal.utils.xml.XmlElementRenderer;
import com.sofort.lib.payment.products.response.parts.PaymentStatusReason;


/**
 * The renderer for {@link PaymentStatusReason}.
 */
public class PaymentStatusReasonRenderer extends XmlElementRenderer<PaymentStatusReason> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sofort.lib.ideal.ideal.refund.refund.billcode.billcode.paycode.paycode.payment.payment.core.core.internal.utils.xml.XmlElementRenderer#render(java.lang
	 * .Object, com.sofort.lib.ideal.ideal.refund.refund.billcode.billcode.paycode.paycode.payment.payment.core.core.internal.utils.xml.XmlElementRenderable)
	 */
	@Override
	public void render(PaymentStatusReason statusReason, XmlElementRenderable element) {
		element.append("status_reason", statusReason.name().toLowerCase());
	}

}
