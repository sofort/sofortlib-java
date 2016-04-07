package com.sofort.lib.core.internal.utils.xml;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.Node;


/**
 * The Class XmlElementParsable.
 */
public class XmlElementParsable {

	/** The element. */
	private final Element element;

	/** The parser. */
	private final XmlParserHelper parser;


	/**
	 * Instantiates a new xml element parsable.
	 * 
	 * @param element
	 *            the element
	 * @param parser
	 *            the parser
	 */
	public XmlElementParsable(Element element, XmlParserHelper parser) {
		this.element = element;
		this.parser = parser;
	}


	/**
	 * Gets the children.
	 * 
	 * @param childName
	 *            the child name
	 * @return the children
	 */
	public List<XmlElementParsable> getChildren(String childName) {

		List<Element> list = parser.getChildren(element, childName);
		if (list == null) {
			return null;
		}

		if (list.isEmpty()) {
			return Collections.emptyList();
		}

		List<XmlElementParsable> result = new ArrayList<XmlElementParsable>();
		for (Element e : list) {
			result.add(new XmlElementParsable(e, parser));
		}

		return result;
	}


	/**
	 * Gets the name.
	 * 
	 * @return the name
	 */
	public String getName() {
		return parser.getName(element);
	}


	/**
	 * Gets the text.
	 * 
	 * @return the text
	 */
	public String getText() {
		return parser.getText(element);
	}


	/**
	 * Gets the child text.
	 * 
	 * @param childName
	 *            the child name
	 * @return the child text
	 */
	public String getChildText(String childName) {
		return parser.getChildText(element, childName);
	}


	/**
	 * Gets the child text as int.
	 * 
	 * @param childName
	 *            the child name
	 * @return the child text as int
	 */
	public int getChildTextAsInt(String childName) {
		return parser.getChildTextAsInt(element, childName);
	}


	/**
	 * Gets the child text as double.
	 * 
	 * @param childName
	 *            the child name
	 * @return the child text as double
	 */
	public double getChildTextAsDouble(String childName) {
		return parser.getChildTextAsDouble(element, childName);
	}


	/**
	 * Gets the child text as date.
	 * 
	 * @param childName
	 *            the child name
	 * @return the child text as date
	 */
	public Date getChildTextAsDate(String childName) {
		return parser.getChildTextAsDate(element, childName);
	}


	/**
	 * Gets the child text as boolean.
	 * 
	 * @param childName
	 *            the child name
	 * @return the child text as boolean
	 */
	public boolean getChildTextAsBoolean(String childName) {
		return parser.getChildTextAsBoolean(element, childName);
	}


	/**
	 * Gets the child.
	 * 
	 * @param string
	 *            the string
	 * @return the child
	 */
	public XmlElementParsable getChild(String string) {
		return new XmlElementParsable(parser.getChild(element, string), parser);
	}


	/**
	 * Checks if is empty.
	 * 
	 * @return true, if is empty
	 */
	public boolean isEmpty() {
		return element == null;
	}


	/**
	 * Returns the internal/wrapped node.
	 * 
	 * @return the internal/wrapped node
	 */
	protected Node getNode() {
		return element;
	}
}
