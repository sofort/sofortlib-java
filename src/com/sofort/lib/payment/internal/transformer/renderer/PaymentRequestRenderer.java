package com.sofort.lib.payment.internal.transformer.renderer;

import java.util.List;

import com.sofort.lib.core.internal.transformer.renderer.XmlRequestRenderer;
import com.sofort.lib.core.internal.transformer.renderer.parts.BankAccountRenderer;
import com.sofort.lib.core.internal.transformer.renderer.parts.NotificationRenderer;
import com.sofort.lib.core.internal.utils.NumberUtilities;
import com.sofort.lib.core.internal.utils.xml.XmlElementRenderable;
import com.sofort.lib.core.products.common.BankAccount;
import com.sofort.lib.core.products.request.SofortLibRequest;
import com.sofort.lib.core.products.request.parts.Notification;
import com.sofort.lib.payment.products.request.PaymentRequest;
import com.sofort.lib.payment.products.request.SofortPayment;


/**
 * The renderer for {@link PaymentRequest}.
 */
public class PaymentRequestRenderer implements XmlRequestRenderer {

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
		render((PaymentRequest) request, element);
	}


	/**
	 * Render the {@link PaymentRequest} into given {@link XmlElementRenderable}
	 * element.
	 * 
	 * @param request
	 *            the payment request
	 * @param element
	 *            the renderable element
	 */
	public void render(PaymentRequest request, XmlElementRenderable element) {
		element.append("project_id", request.getProjectId());
		element.append("interface_version", request.getInterfaceVersion());
		element.append("language_code", request.getLanguageCode());
		element.append("timeout", request.getTimeout());
		element.append("email_customer", request.getEmailCustomer());
		element.append("phone_customer", request.getPhoneCustomer());
		element.append("user_variables", "user_variable", request.getUserVariables());
		append(element, "sender", request.getSender());
		element.append("amount", new NumberUtilities().formatAmount(request.getAmount()));
		element.append("currency_code", request.getCurrencyCode());
		element.append("reasons", "reason", request.getReasons());
		element.append("success_url", request.getSuccessUrl());
		element.append("success_link_redirect", request.getSuccessLinkRedirect());
		element.append("abort_url", request.getAbortUrl());
		element.append("timeout_url", request.getTimeoutUrl());
		append(element, "notification_urls", "notification_url", request.getNotificationUrls());
		append(element, "notification_emails", "notification_email", request.getNotificationEmails());

		renderSofortPayment(request.getSofortPayment(), element);
	}


	private void renderSofortPayment(SofortPayment sofortPayment, XmlElementRenderable element) {
		XmlElementRenderable su = element.append("su");
		if (sofortPayment.isConsumerProtection() != null) {
			su.append("customer_protection", sofortPayment.isConsumerProtection());
		}
	}


	/**
	 * Append the {@link BankAccount} to the renderable element.
	 * 
	 * @param element
	 *            the renderable element
	 * @param childName
	 *            the child name for the bank account
	 * @param bankAccount
	 *            the bank account
	 */
	private static void append(XmlElementRenderable element, String childName, BankAccount bankAccount) {
		if (bankAccount != null) {
			new BankAccountRenderer().render(bankAccount, element.append(childName));
		}
	}


	/**
	 * Append the {@link Notification} list wrapped into one parent element with
	 * the given name to the renderable element.
	 * 
	 * @param element
	 *            the renderable element
	 * @param parentName
	 *            the parent/wrapper name
	 * @param childName
	 *            the child name for each notification
	 * @param notifications
	 *            the notifications
	 */
	private static void append(XmlElementRenderable element, String parentName, String childName, List<Notification> notifications) {
		if (notifications != null) {

			XmlElementRenderable parent = element.append(parentName);
			for (Notification notification : notifications) {
				new NotificationRenderer().render(notification, parent.append(childName));
			}
		}
	}

}
