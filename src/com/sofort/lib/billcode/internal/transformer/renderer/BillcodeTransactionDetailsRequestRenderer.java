package com.sofort.lib.billcode.internal.transformer.renderer;

import java.util.List;

import com.sofort.lib.billcode.internal.transformer.common.BillcodeStatusReasonRenderer;
import com.sofort.lib.billcode.internal.transformer.common.BillcodeStatusRenderer;
import com.sofort.lib.billcode.products.request.BillcodeTransactionDetailsRequest;
import com.sofort.lib.core.internal.transformer.renderer.XmlRequestRenderer;
import com.sofort.lib.core.internal.utils.xml.XmlElementRenderable;
import com.sofort.lib.core.products.request.SofortLibRequest;


/**
 * The renderer for {@link BillcodeTransactionDetailsRequest}.
 */
public class BillcodeTransactionDetailsRequestRenderer implements XmlRequestRenderer {

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
		render((BillcodeTransactionDetailsRequest) request, element);
	}


	/**
	 * Render the given {@link BillcodeTransactionDetailsRequest} into the given
	 * renderable element.
	 * 
	 * @param request
	 *            the transaction details billcode request
	 * @param element
	 *            the renderable element
	 */
	public void render(BillcodeTransactionDetailsRequest request, XmlElementRenderable element) {

		List<String> transIds = request.getTransIds();
		if (transIds != null && !transIds.isEmpty()) {

			for (String transId : transIds) {
				element.append("transaction", transId);
			}

		} else {
			element.append("from_time", request.getFromTime());
			element.append("to_time", request.getToTime());
			element.append("from_status_modified_time", request.getFromStatusModifiedTime());
			element.append("to_status_modified_time", request.getToStatusModifiedTime());
			if (request.getStatus() != null) {
				new BillcodeStatusRenderer().render(request.getStatus(), element);
			}
			if (request.getStatusReason() != null) {
				new BillcodeStatusReasonRenderer().render(request.getStatusReason(), element);
			}
			element.append("number", request.getNumber());
			element.append("page", request.getPage());
			element.append("product", BillcodeTransactionDetailsRequest.product);
		}
	}

}
