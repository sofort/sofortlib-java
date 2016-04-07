package com.sofort.lib.core.internal.transformer.parser;

import com.sofort.lib.core.internal.utils.xml.XmlElementParsable;
import com.sofort.lib.core.products.response.SofortLibResponse;


/**
 * Definition of a XML to SofortLib response parser.
 * 
 * @param <T>
 *            The implementation of a SofortLib request.
 */
public interface XmlResponseParser<T extends SofortLibResponse> {

	/**
	 * Converts the given XML node element to the defined SofortLib response.
	 * 
	 * @param element
	 *            a XML node
	 * @return converted XML structure as a SofortLib response.
	 */
	public T parse(XmlElementParsable element);

}
