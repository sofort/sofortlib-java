package com.sofort.lib.billcode.internal.transformer.renderer;

import java.util.List;

import com.sofort.lib.billcode.products.request.BillcodeRequest;
import com.sofort.lib.core.internal.transformer.renderer.XmlRequestRenderer;
import com.sofort.lib.core.internal.transformer.renderer.parts.BankRenderer;
import com.sofort.lib.core.internal.transformer.renderer.parts.NotificationRenderer;
import com.sofort.lib.core.internal.utils.xml.XmlElementRenderable;
import com.sofort.lib.core.products.common.Bank;
import com.sofort.lib.core.products.common.BankAccount;
import com.sofort.lib.core.products.request.SofortLibRequest;
import com.sofort.lib.core.products.request.parts.Notification;


/**
 * The renderer for {@link BillcodeRequest}.
 */
public class BillcodeRequestRenderer implements XmlRequestRenderer {

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
		render((BillcodeRequest) request, element);
	}


	/**
	 * Render the {@link BillcodeRequest} into given
	 * {@link XmlElementRenderable} element.
	 * 
	 * @param request
	 *            the billcode request
	 * @param element
	 *            the renderable element
	 */
	public void render(BillcodeRequest request, XmlElementRenderable element) {
		element.append("project_id", request.getProjectId());
		element.append("interface_version", request.getInterfaceVersion());
		element.append("language_code", request.getLanguageCode());
		element.append("start_date", request.getStartDate(), "yyyy-MM-dd HH:mm:ss");
		element.append("end_date", request.getEndDate(), "yyyy-MM-dd HH:mm:ss");
		element.append("amount", request.getAmount());
		element.append("currency_code", request.getCurrencyCode());
		append(element, "sender", request.getSender());
		element.append("reasons", "reason", request.getReasons());
		element.append("success_link_redirect", request.getSuccessLinkRedirect());
		append(element, "notification_urls", "notification_url", request.getNotificationUrls());
		append(element, "notification_emails", "notification_email", request.getNotificationEmails());
		element.append("user_variables", "user_variable", request.getUserVariables());
	}


	/**
	 * Append the {@link BankAccount} to the renderable element.
	 * 
	 * @param element
	 *            the renderable element
	 * @param childName
	 *            the child name for the bank account
	 * @param senderBank
	 *            the sender bank data
	 */
	private static void append(XmlElementRenderable element, String childName, Bank senderBank) {
		if (senderBank != null) {
			new BankRenderer().render(senderBank, element.append(childName));
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
