package com.sofort.lib.core.internal.transformer.parser.parts;

import com.sofort.lib.core.internal.utils.xml.XmlElementParsable;
import com.sofort.lib.core.products.response.parts.TransactionDetails;


/**
 * The Class TransactionDetailsCommonParser.
 */
public class TransactionDetailsParser {

	/**
	 * Parses the transaction details.
	 * 
	 * @param transaction
	 *            the transaction
	 * @param element
	 *            the element
	 */
	public void parseTransactionDetails(TransactionDetails transaction, XmlElementParsable element) {

		transaction.setProjectId(element.getChildTextAsInt("project_id"));
		transaction.setTransId(element.getChildText("transaction"));
		transaction.setTest(element.getChildTextAsBoolean("test"));
		transaction.setTime(element.getChildTextAsDate("time"));

		transaction.setPaymentMethod(getPaymentMethod(element));
		transaction.setLanguageCode(element.getChildText("language_code"));
		transaction.setAmount(element.getChildTextAsDouble("amount"));
		transaction.setAmountRefunded(element.getChildTextAsDouble("amount_refunded"));
		transaction.setCurrencyCode(element.getChildText("currency_code"));

		transaction.setReasons(new StringParser().parseChildren(element.getChild("reasons"), "reason"));

		transaction.setUserVariables(new StringParser().parseChildren(element.getChild("user_variables"), "user_variable"));

		transaction.setSender(new BankAccountParser().parseChild(element.getChild("sender")));
		transaction.setRecipient(new BankAccountParser().parseChild(element.getChild("recipient")));

		transaction.setExchangeRate(element.getChildTextAsDouble("exchange_rate"));

		transaction.setCosts(new CostsParser().parseChild(element.getChild("costs")));
	}


	/**
	 * Return the the payment method child value of the given parent element.
	 * 
	 * @param element
	 *            parent element
	 * @return the payment method
	 */
	public static String getPaymentMethod(XmlElementParsable element) {
		return element.getChildText("payment_method");
	}
}
