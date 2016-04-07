package com.sofort.lib.refund.internal.transformer.parser.parts;

import com.sofort.lib.core.internal.utils.xml.XmlElementParsable;
import com.sofort.lib.core.internal.utils.xml.XmlElementParser;
import com.sofort.lib.refund.products.RefundBankAccount;


/**
 * The Class BankAccountParser.
 */
public class RefundBankAccountParser extends XmlElementParser<RefundBankAccount> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sofort.lib.ideal.ideal.refund.refund.billcode.billcode.paycode.paycode.payment.payment.core.core.internal.utils.xml.XmlElementParser#parseChildImpl(com
	 * .sofort.lib.internal.utils.xml.XmlElementParsable)
	 */
	@Override
	protected RefundBankAccount parseChildImpl(XmlElementParsable e) {
		RefundBankAccount bankAccount = new RefundBankAccount();

		bankAccount.setHolder(e.getChildText("holder"));
		bankAccount.setBankName(e.getChildText("bank_name"));
		bankAccount.setIban(e.getChildText("iban"));
		bankAccount.setBic(e.getChildText("bic"));

		return bankAccount;
	}

}
