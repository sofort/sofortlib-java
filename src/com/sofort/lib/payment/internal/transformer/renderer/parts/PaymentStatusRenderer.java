package com.sofort.lib.payment.internal.transformer.renderer.parts;

import com.sofort.lib.core.internal.utils.xml.XmlElementRenderable;
import com.sofort.lib.core.internal.utils.xml.XmlElementRenderer;
import com.sofort.lib.payment.products.response.parts.PaymentStatus;


/**
 * The renderer for PaymentStatus.
 */
public class PaymentStatusRenderer extends XmlElementRenderer<PaymentStatus> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sofort.lib.ideal.ideal.refund.refund.billcode.billcode.paycode.paycode.payment.payment.core.core.internal.utils.xml.XmlElementRenderer#render(java.lang
	 * .Object, com.sofort.lib.ideal.ideal.refund.refund.billcode.billcode.paycode.paycode.payment.payment.core.core.internal.utils.xml.XmlElementRenderable)
	 */
	@Override
	public void render(PaymentStatus status, XmlElementRenderable element) {
		element.append("status", status.name().toLowerCase());
	}

}
