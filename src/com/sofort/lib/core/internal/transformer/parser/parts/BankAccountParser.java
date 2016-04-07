package com.sofort.lib.core.internal.transformer.parser.parts;

import com.sofort.lib.core.internal.utils.xml.XmlElementParsable;
import com.sofort.lib.core.internal.utils.xml.XmlElementParser;
import com.sofort.lib.core.products.common.BankAccount;


/**
 * The Class BankAccountParser.
 */
public class BankAccountParser extends XmlElementParser<BankAccount> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sofort.lib.ideal.ideal.refund.refund.billcode.billcode.paycode.
	 * paycode.payment.payment.core.core.internal.utils.xml.XmlElementParser#
	 * parseChildImpl(com .sofort.lib.internal.utils.xml.XmlElementParsable)
	 */
	@SuppressWarnings("deprecation")
	@Override
	protected BankAccount parseChildImpl(XmlElementParsable e) {
		BankAccount bankAccount = new BankAccount();

		bankAccount.setHolder(e.getChildText("holder"));
		bankAccount.setAccountNumber(e.getChildText("account_number"));
		bankAccount.setBankCode(e.getChildText("bank_code"));
		bankAccount.setBankName(e.getChildText("bank_name"));
		bankAccount.setIban(e.getChildText("iban"));
		bankAccount.setBic(e.getChildText("bic"));
		bankAccount.setCountryCode(e.getChildText("country_code"));

		return bankAccount;
	}

}
