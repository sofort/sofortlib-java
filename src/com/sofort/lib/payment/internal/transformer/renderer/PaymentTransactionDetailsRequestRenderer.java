package com.sofort.lib.payment.internal.transformer.renderer;

import java.util.List;

import com.sofort.lib.core.internal.transformer.renderer.XmlRequestRenderer;
import com.sofort.lib.core.internal.utils.xml.XmlElementRenderable;
import com.sofort.lib.core.products.request.SofortLibRequest;
import com.sofort.lib.payment.internal.transformer.renderer.parts.PaymentStatusReasonRenderer;
import com.sofort.lib.payment.internal.transformer.renderer.parts.PaymentStatusRenderer;
import com.sofort.lib.payment.products.request.PaymentTransactionDetailsRequest;


/**
 * The renderer for {@link PaymentTransactionDetailsRequest}.
 */
public class PaymentTransactionDetailsRequestRenderer implements XmlRequestRenderer {

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
		render((PaymentTransactionDetailsRequest) request, element);
	}


	/**
	 * Render the given {@link PaymentTransactionDetailsRequest} into the given
	 * renderable element.
	 * 
	 * @param request
	 *            the transaction details payment request
	 * @param element
	 *            the renderable element
	 */
	public void render(PaymentTransactionDetailsRequest request, XmlElementRenderable element) {

		List<String> transIds = request.getTransIds();
		if (transIds != null) {
			for (String transId : transIds) {
				element.append("transaction", transId);
			}

		} else {
			element.append("from_time", request.getFromTime());
			element.append("to_time", request.getToTime());
			element.append("from_status_modified_time", request.getFromStatusModifiedTime());
			element.append("to_status_modified_time", request.getToStatusModifiedTime());
			if (request.getStatus() != null) {
				new PaymentStatusRenderer().render(request.getStatus(), element);
			}
			if (request.getStatusReason() != null) {
				new PaymentStatusReasonRenderer().render(request.getStatusReason(), element);
			}
			element.append("number", request.getNumber());
			element.append("page", request.getPage());
			element.append("product", PaymentTransactionDetailsRequest.product);
		}

	}

}
