package com.sofort.lib.paycode.internal.transformer.renderer;

import com.sofort.lib.core.internal.transformer.renderer.XmlRequestRenderer;
import com.sofort.lib.core.internal.utils.xml.XmlElementRenderable;
import com.sofort.lib.core.products.request.SofortLibRequest;
import com.sofort.lib.paycode.products.request.PaycodeRequest;
import com.sofort.lib.paycode.products.request.PaycodeStatusRequest;


/**
 * The renderer for {@link PaycodeRequest}.
 */
public class PaycodeStatusRequestRenderer implements XmlRequestRenderer {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sofort.lib.ideal.ideal.refund.refund.billcode.billcode.paycode.paycode.payment.payment.core.core.internal.transformer.renderer.XmlRequestRenderer#render
	 * (com.sofort.lib.ideal.ideal.refund.refund.billcode.billcode.paycode.paycode.payment.payment.core.core.products.request.SofortLibRequest,
	 * com.sofort.lib.ideal.ideal.refund.refund.billcode.billcode.paycode.paycode.payment.payment.core.core.internal.utils.xml.XmlElementRenderable)
	 */
	@Override
	public void render(SofortLibRequest request, XmlElementRenderable element) {
		render((PaycodeStatusRequest) request, element);
	}


	/**
	 * Render the {@link PaycodeRequest} into given {@link XmlElementRenderable}
	 * element.
	 * 
	 * @param request
	 *            the paycode request
	 * @param element
	 *            the renderable element
	 */
	public void render(PaycodeStatusRequest request, XmlElementRenderable element) {
		element.append("paycode", request.getPaycode());
	}
}
