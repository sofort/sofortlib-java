package com.sofort.lib.core.internal.transformer.renderer.parts;

import com.sofort.lib.core.internal.utils.xml.XmlElementRenderable;
import com.sofort.lib.core.internal.utils.xml.XmlElementRenderer;
import com.sofort.lib.core.products.common.BankAccount;


/**
 * The Class BankAccountRenderer.
 */
public class BankAccountRenderer extends XmlElementRenderer<BankAccount> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sofort.lib.ideal.ideal.refund.refund.billcode.billcode.paycode.
	 * paycode.payment.payment.core.core.internal.utils.xml.XmlElementRenderer#
	 * render(java.lang .Object,
	 * com.sofort.lib.ideal.ideal.refund.refund.billcode.billcode.paycode.
	 * paycode.payment.payment.core.core.internal.utils.xml.
	 * XmlElementRenderable)
	 */
	@SuppressWarnings("deprecation")
	@Override
	public void render(BankAccount bankAccount, XmlElementRenderable element) {
		element.append("holder", bankAccount.getHolder());
		element.append("account_number", bankAccount.getAccountNumber());
		element.append("bank_code", bankAccount.getBankCode());
		element.append("bank_name", bankAccount.getBankName());
		element.append("country_code", bankAccount.getCountryCode());
		element.append("iban", bankAccount.getIban());
		element.append("bic", bankAccount.getBic());
	}
}
