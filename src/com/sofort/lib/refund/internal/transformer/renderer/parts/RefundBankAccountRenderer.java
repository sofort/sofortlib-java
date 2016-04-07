package com.sofort.lib.refund.internal.transformer.renderer.parts;

import com.sofort.lib.core.internal.utils.xml.XmlElementRenderable;
import com.sofort.lib.core.internal.utils.xml.XmlElementRenderer;
import com.sofort.lib.refund.products.RefundBankAccount;


/**
 * The Class REfundBankAccountRenderer.
 */
public class RefundBankAccountRenderer extends XmlElementRenderer<RefundBankAccount> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sofort.lib.ideal.ideal.refund.refund.billcode.billcode.paycode.paycode.payment.payment.core.core.internal.utils.xml.XmlElementRenderer#render(java.lang
	 * .Object, com.sofort.lib.ideal.ideal.refund.refund.billcode.billcode.paycode.paycode.payment.payment.core.core.internal.utils.xml.XmlElementRenderable)
	 */
	@Override
	public void render(RefundBankAccount bankAccount, XmlElementRenderable element) {
		element.append("holder", bankAccount.getHolder());
		element.append("bank_name", bankAccount.getBankName());
		element.append("iban", bankAccount.getIban());
		element.append("bic", bankAccount.getBic());
	}
}
