package com.sofort.lib.core.internal.transformer.parser.parts;

import com.sofort.lib.core.internal.utils.xml.XmlElementParsable;
import com.sofort.lib.core.internal.utils.xml.XmlElementParser;
import com.sofort.lib.core.products.response.parts.Costs;


/**
 * The Class CostsParser.
 */
public class CostsParser extends XmlElementParser<Costs> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sofort.lib.ideal.ideal.refund.refund.billcode.billcode.paycode.paycode.payment.payment.core.core.internal.utils.xml.XmlElementParser#parseChildImpl(com
	 * .sofort.lib.internal.utils.xml.XmlElementParsable)
	 */
	@Override
	protected Costs parseChildImpl(XmlElementParsable e) {
		Costs costs = new Costs();

		costs.setFees(e.getChildTextAsDouble("fees"));
		costs.setCurrencyCode(e.getChildText("currency_code"));
		costs.setExchangeRate(e.getChildTextAsDouble("exchange_rate"));

		return costs;
	}
}
