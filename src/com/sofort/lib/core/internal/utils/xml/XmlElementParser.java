package com.sofort.lib.core.internal.utils.xml;

import java.util.ArrayList;
import java.util.List;


/**
 * An abstract implementation of an XML element parser for defined type T.
 * 
 * @param <T>
 *            the generic type
 */
public abstract class XmlElementParser<T> {

	/**
	 * Returns parsed children of the give root node. The root node name will be
	 * controlled.
	 * 
	 * @param root
	 *            the root node with a children
	 * @param tag
	 *            name of the child nodes to parse into the list, all other
	 *            nodes will be ignored. To parse all children set the name to
	 *            null.
	 * @return list of parsed children nodes of defined type or null if the name
	 *         root node is not equal to the defined one.
	 */
	public final List<T> parseChildren(XmlElementParsable root, String tag) {

		if (root == null || root.isEmpty()) {
			return null;
		}

		final List<XmlElementParsable> childList = root.getChildren(tag);
		if (childList == null) {
			return null;
		}

		final List<T> resultList = new ArrayList<T>(childList.size());
		for (XmlElementParsable child : childList) {

			/* parse single child nodes and add it to the list */
			final T parsed = parseChild(child);
			if (parsed != null) {
				resultList.add(parsed);
			}
		}

		return resultList;
	}


	/**
	 * Returns a parsed object of type T from the given XML element.
	 * 
	 * @param childElement
	 *            the XML element to parse to the defined object of type T
	 * @return the parsed object of type T or null if the given element is null
	 */
	public final T parseChild(XmlElementParsable childElement) {
		if (childElement == null || childElement.isEmpty()) {
			return null;
		}

		return parseChildImpl(childElement);
	}


	/**
	 * The implementation of the parseChild method.
	 * 
	 * @param childElement
	 *            the XML element to parse to the defined object of type T
	 * @return the parsed object of type T or null if the given element is null
	 */
	protected abstract T parseChildImpl(XmlElementParsable childElement);
}
