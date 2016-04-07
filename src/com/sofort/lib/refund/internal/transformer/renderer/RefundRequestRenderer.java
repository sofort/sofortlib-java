package com.sofort.lib.refund.internal.transformer.renderer;

import java.util.List;

import com.sofort.lib.core.internal.transformer.renderer.XmlRequestRenderer;
import com.sofort.lib.core.internal.utils.xml.XmlElementRenderable;
import com.sofort.lib.core.products.request.SofortLibRequest;
import com.sofort.lib.refund.internal.transformer.renderer.parts.RefundBankAccountRenderer;
import com.sofort.lib.refund.internal.transformer.renderer.parts.RefundRenderer;
import com.sofort.lib.refund.products.RefundBankAccount;
import com.sofort.lib.refund.products.request.RefundRequest;
import com.sofort.lib.refund.products.request.parts.Refund;


/**
 * The renderer for {@link RefundRequest}.
 */
public class RefundRequestRenderer implements XmlRequestRenderer {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sofort.lib.ideal.ideal.refund.refund.billcode.billcode.paycode.
	 * paycode.payment.payment.core.core.internal.transformer.renderer.
	 * XmlRequestRenderer#render
	 * (com.sofort.lib.ideal.ideal.refund.refund.billcode.billcode.paycode.
	 * paycode.payment.payment.core.core.products.request.SofortLibRequest,
	 * com.sofort.lib.ideal.ideal.refund.refund.billcode.billcode.paycode.
	 * paycode.payment.payment.core.core.internal.utils.xml.
	 * XmlElementRenderable)
	 */
	@Override
	public void render(SofortLibRequest request, XmlElementRenderable element) {
		render((RefundRequest) request, element);
	}


	/**
	 * Render the {@link RefundRequest} data into renderaböle element.
	 * 
	 * @param request
	 *            the request
	 * @param element
	 *            the element
	 */
	public void render(RefundRequest request, XmlElementRenderable element) {
		element.append("title", request.getTitle());
		append(element, "sender", request.getSender());
		append(element, request.getRefunds());
	}


	/**
	 * Append.
	 * 
	 * @param element
	 *            the element
	 * @param childName
	 *            the child name
	 * @param bankAccount
	 *            the bank account
	 */
	private static void append(XmlElementRenderable element, String childName, RefundBankAccount bankAccount) {
		if (bankAccount != null) {
			new RefundBankAccountRenderer().render(bankAccount, element.append(childName));
		}
	}


	/**
	 * Append.
	 * 
	 * @param element
	 *            the element
	 * @param refunds
	 *            the refunds
	 */
	private static void append(XmlElementRenderable element, List<Refund> refunds) {
		for (Refund refund : refunds) {
			new RefundRenderer().render(refund, element.append("refund"));
		}
	}

}
