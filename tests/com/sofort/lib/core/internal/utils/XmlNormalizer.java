package com.sofort.lib.core.internal.utils;

import java.util.regex.Pattern;


/**
 * Help class normalizes the XML text for proper comparison of master and
 * generated data.
 */
public class XmlNormalizer {

	private static final Pattern INDENT_PATTERN = Pattern.compile(">\\s+<", Pattern.DOTALL);
	private static final String INDENT_REPLACEMENT = "><";


	public static String removeIndents(String xml) {
		if (xml.isEmpty()) {
			return xml;
		}

		return INDENT_PATTERN.matcher(xml).replaceAll(INDENT_REPLACEMENT);
	}

}
