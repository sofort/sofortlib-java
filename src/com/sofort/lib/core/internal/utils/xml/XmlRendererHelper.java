package com.sofort.lib.core.internal.utils.xml;

import java.io.StringWriter;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.sofort.lib.core.internal.utils.Attribute;


/**
 * Collection of methods helps to build a XML structure.
 */
public class XmlRendererHelper {

	/** The doc builder. */
	private DocumentBuilder docBuilder;

	/** The transformer. */
	private Transformer transformer;

	/** The document. */
	private Document document;


	/**
	 * Initializes the internal XML structure.
	 * 
	 * @throws XmlRendererHelperException
	 *             thrown in case of XML parser or XML transformer exceptions
	 */
	protected XmlRendererHelper() throws XmlRendererHelperException {
		try {
			final DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			docBuilder = docFactory.newDocumentBuilder();
			document = docBuilder.newDocument();
			document.setXmlStandalone(true);

			transformer = TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
			transformer.setOutputProperty(OutputKeys.METHOD, "xml");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

		} catch (ParserConfigurationException e) {
			throw new XmlRendererHelperException("Parser configuration error:", e);

		} catch (TransformerConfigurationException e) {
			throw new XmlRendererHelperException("Transformer configuration error:", e);
		}
	}


	/**
	 * Defines the root element of the XML structure.
	 * 
	 * @param name
	 *            tag name of root element
	 * @param attributes
	 *            element attributes
	 * @return the built XML element
	 * @throws XmlRendererHelperException
	 *             thrown if the root element is already defined
	 */
	public Element appendRootElement(String name, Attribute... attributes) throws XmlRendererHelperException {
		if (document.getChildNodes().getLength() != 0) {
			throw new XmlRendererHelperException("The document already has children!");
		}

		Element root = createElement(name);
		for (Attribute attribute : attributes) {
			root.setAttribute(attribute.getKey(), attribute.getValue());
		}

		append(document, root);
		return root;
	}


	/**
	 * Safe appending of a child node to the parent one.
	 * 
	 * @param parent
	 *            the parent
	 * @param child
	 *            the child
	 */
	public void append(Node parent, Node child) {
		if (parent != null && child != null) {
			parent.appendChild(child);
		}
	}


	/**
	 * Creates an empty element with given tag name.
	 * 
	 * @param name
	 *            tag name
	 * @return null if name is not set or is null
	 */
	public Element createElement(String name) {
		return create(name, "");
	}


	/**
	 * Creates an element with the given content.
	 * 
	 * @param name
	 *            tag name
	 * @param content
	 *            content as text
	 * @return the created element
	 */
	public Element createElement(String name, String content) {
		return create(name, content);
	}


	/**
	 * Creates an element with rendered value from the given number.
	 * 
	 * @param name
	 *            tag name
	 * @param content
	 *            content as number
	 * @return the created element
	 */
	public Element createElement(String name, Number content) {
		return create(name, XmlFormatter.format(content));
	}


	/**
	 * Creates an element with rendered value from the given number.
	 * 
	 * @param name
	 *            tag name
	 * @param content
	 *            content as number
	 * @param invaldValue
	 *            defined the invalid value. It's needed to control if an
	 *            element should be created
	 * @return the created element or null if content is null or equals to the
	 *         invalid value
	 */
	public Element createElement(String name, Number content, Number invaldValue) {
		return create(name, XmlFormatter.format(content, invaldValue));
	}


	/**
	 * Creates an element with rendered value from the given date.
	 * 
	 * @param name
	 *            tag name
	 * @param content
	 *            content as date
	 * @return the created element
	 */
	public Element createElement(String name, Date content) {
		return create(name, XmlFormatter.format(content));
	}


	/**
	 * Creates an element with rendered value from the given date with the given
	 * format.
	 * 
	 * @param name
	 *            tag name
	 * @param content
	 *            content as date
	 * @param format
	 *            date format
	 * @return the created element
	 */
	public Element createElement(String name, Date content, String format) {
		return create(name, XmlFormatter.format(content, format));
	}


	/**
	 * Creates an element with rendered value from the given boolean.
	 * 
	 * @param name
	 *            tag name
	 * @param content
	 *            content as boolean
	 * @return the created element
	 */
	public Element createElement(String name, Boolean content) {
		return create(name, XmlFormatter.format(content));
	}


	/**
	 * Creates an parent element with child elements.
	 * 
	 * @param parentName
	 *            the tag name of the parent element
	 * @param childName
	 *            the tag name of child elements
	 * @param list
	 *            the list with the child element contents
	 * @return the parent node with the children nodes created from given values
	 */
	public Element createElement(String parentName, String childName, List<String> list) {
		if (list == null) {
			return null;
		}

		Element element = createElement(parentName);

		for (String childContent : list) {
			append(element, create(childName, childContent));
		}

		return element;
	}


	/**
	 * Safe creating of an element with given content.
	 * 
	 * @param name
	 *            the name
	 * @param content
	 *            the content
	 * @return the element
	 */
	private Element create(String name, String content) {
		if (name == null || name.isEmpty() || content == null) {
			return null;
		}

		/* create a element */
		Element element = document.createElement(name);

		/* append text if any present */
		if (!content.isEmpty()) {
			element.setTextContent(content);
		}

		return element;
	}


	/**
	 * Returns the built XML structure as text.
	 * 
	 * @param node
	 *            XML node
	 * @param transformer
	 *            XML transformer
	 * @return built XML as text
	 * @throws XmlRendererHelperException
	 *             thrown in case of transformer exception
	 */
	protected static String toXml(Node node, Transformer transformer) throws XmlRendererHelperException {
		try {
			StringWriter writer = new StringWriter();
			transformer.transform(new DOMSource(node), new StreamResult(writer));

			String xml = writer.getBuffer().toString();
			return fixIdent(xml);

		} catch (TransformerException e) {
			throw new XmlRendererHelperException("Transformer error:", e);
		}
	}


	/**
	 * Renders the built XML structure as text.
	 * 
	 * @return rendered XML structure as text
	 * @throws XmlRendererHelperException
	 *             thrown in case of transformer exception
	 */
	public String getXml() throws XmlRendererHelperException {
		return toXml(document, transformer);
	}

	/** Line break fix pattern. */
	private static final Pattern XML_HEAD = Pattern.compile("(<\\?xml[^\\?]+\\?>)<");
	/** Line break fix replacement */
	private static final String XML_HEAD_FIX = "$1\n<";


	/**
	 * Fixes the no line break after the XML declaration problem.
	 * 
	 * @param xml
	 *            the source xml
	 * @return the xml with the fixed line break
	 */
	private static String fixIdent(String xml) {
		return XML_HEAD.matcher(xml).replaceFirst(XML_HEAD_FIX);
	}


	/**
	 * Safe setting of an attribute to the node.
	 * 
	 * @param node
	 *            the node
	 * @param attribute
	 *            the attribute
	 */
	public void setAttribute(Element node, Attribute attribute) {
		if (attribute != null && attribute.getKey() != null && attribute.getValue() != null) {
			node.setAttribute(attribute.getKey(), attribute.getValue());
		}
	}


	/**
	 * Safe setting of a content to a XML element.
	 * 
	 * @param node
	 *            XML element
	 * @param content
	 *            content
	 */
	public void setContent(Element node, String content) {
		if (node == null || content == null) {
			return;
		}

		if (!content.isEmpty()) {
			node.setTextContent(content);
		}
	}
}
