package com.sofort.lib.core.internal.utils.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.CharArrayReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * XML parser helper.
 */
public class XmlParserHelper {

    /**
     * The verifier.
     */
    private final XmlVerifier verifier;


    /**
     * Defines a XML parser helper. Additionally a XML verifier is defined too.
     * The verifier checks if the content of all parsed nodes was picked up.
     */
    protected XmlParserHelper() {
        verifier = new XmlVerifier();
    }


    /**
     * Checks if the content of all parsed nodes was picked up.
     *
     * @throws XmlVerifierException thrown if the content of not all nodes was picked up.
     */
    public void verify() throws XmlVerifierException {
        verifier.verify();
    }


    /**
     * Parses the text XML to a {@link Document}.
     *
     * @param xml the xml
     * @return the document
     * @throws XmlParserHelperException thrown in case of any XML parsing exceptions
     */
    public Document parseXml(String xml) throws XmlParserHelperException {
        CharArrayReader reader = new CharArrayReader(xml.toCharArray());
        Document document = parseXml(new InputSource(reader));

        verifier.init(document.getDocumentElement());

        return document;
    }


    /**
     * Parses the XML stream to a {@link Document}.
     *
     * @param inputSource the input source
     * @return the document
     * @throws XmlParserHelperException thrown in case of ParserConfiguration, SAX and IO exceptions
     */
    public Document parseXml(InputSource inputSource) throws XmlParserHelperException {
//			XmlParserCreator parserCreator = new XmlParserCreator() {
//			XmlParserCreator parserCreator = new XmlParserCreator() {
//				@Override
//				public XmlPullParser createParser() {
//					try {
//						return XmlPullParserFactory.newInstance().newPullParser();
//					} catch (Exception e) {
//						throw new RuntimeException(e);
//					}
//				}
//			};
//
//			GsonXml gsonXml = new GsonXmlBuilder()
//					.setXmlParserCreator(parserCreator)
//					.create();
//			DocumentBuilderFactory.newInstance();
//			final DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
//			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
//
//			return docBuilder.parse(inputSource);
//
//		} catch (ParserConfigurationException e) {
//			throw new XmlParserHelperException("Could not prepare for parsing the XML:", e);
//
//		} catch (SAXException e) {
//			throw new XmlParserHelperException("Could not parse the XML:", e);
//
//		} catch (IOException e) {
//			throw new XmlParserHelperException("Could not read the XML:", e);
//		}

        return null;
    }


    /**
     * Returns the content of the node and removes their path from the verifier.
     *
     * @param n node
     * @return the content as text
     */
    public String getText(Node n) {
        if (n == null) {
            return null;
        }

        verifier.removePath(n);
        return n.getTextContent();
    }


    /**
     * Returns the name of the node.
     *
     * @param n node
     * @return the name of the node
     */
    public String getName(Node n) {
        if (n == null) {
            return null;
        }

        return n.getNodeName();
    }


    /**
     * Get from parent the direct child with the given tag name.
     *
     * @param parent parent node
     * @param name   child tag name
     * @return found node or null if not found
     */
    public Element getChild(Element parent, String name) {

        if (parent == null) {
            return null;
        }

        NodeList children = parent.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {

            final Node child = children.item(i);

            /* skip non elements */
            if (child.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }

            /* skip child nodes with different names */
            if (!child.getNodeName().equalsIgnoreCase(name)) {
                continue;
            }

            return (Element) child;
        }

        return null;
    }


    /**
     * Get from parent all children for the given tag name. Remove the path from
     * verifier if the parent node hasn't any children.
     *
     * @param parent parent node
     * @param name   the children tag name
     * @return the list of found children
     */
    public List<Element> getChildren(Element parent, String name) {

        if (parent == null) {
            return null;
        }

        NodeList children = parent.getChildNodes();
        List<Element> list = new ArrayList<Element>(children.getLength());

        for (int i = 0; i < children.getLength(); i++) {

            final Node child = children.item(i);

            /* skip non elements */
            if (child.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }

            /* skip child nodes with different names */
            if (!child.getNodeName().equalsIgnoreCase(name)) {
                continue;
            }

            list.add((Element) child);
        }

        if (list.isEmpty()) {
            verifier.removePath(parent);
        }

        return list;
    }


    /**
     * Safe getting of the content as a text value from the child with the given
     * tag name.
     *
     * @param parent parent XML element
     * @param name   the child name the content should be picked up
     * @return the child content as a text value
     */
    public String getChildText(Element parent, String name) {
        return getText(getChild(parent, name));
    }


    /**
     * Safe getting of the content as a double value from the child with the
     * given tag name.
     *
     * @param parent parent XML element
     * @param name   the child name the content should be picked up
     * @return the child content as a double value
     */
    public double getChildTextAsDouble(Element parent, String name) {
        return XmlFormatter.parseDouble(getChildText(parent, name), 0);
    }


    /**
     * Safe getting of the content as a long value from the child with the given
     * tag name.
     *
     * @param parent parent XML element
     * @param name   the child name the content should be picked up
     * @return the child content as a long value
     */
    public long getChildTextAsLong(Element parent, String name) {
        return XmlFormatter.parseLong(getChildText(parent, name), 0);
    }


    /**
     * Safe getting of the content as a integer value from the child with the
     * given tag name.
     *
     * @param parent parent XML element
     * @param name   the child name the content should be picked up
     * @return the child content as a integer value
     */
    public int getChildTextAsInt(Element parent, String name) {
        return XmlFormatter.parseInt(getChildText(parent, name), 0);
    }


    /**
     * Safe getting of the content as a boolean value from the child with the
     * given tag name.
     *
     * @param parent parent XML element
     * @param name   the child name the content should be picked up
     * @return the child content as a boolean value
     */
    public boolean getChildTextAsBoolean(Element parent, String name) {
        return XmlFormatter.parseBoolean(getChildText(parent, name), false);
    }


    /**
     * Safe getting of the content as a date value from the child with the given
     * tag name.
     *
     * @param parent parent XML element
     * @param name   the child name the content should be picked up
     * @return the child content as a date value
     */
    public Date getChildTextAsDate(Element parent, String name) {
        return XmlFormatter.parseDate(getChildText(parent, name), null);
    }


    /**
     * Safe getting of the content as a short value from the child with the
     * given tag name.
     *
     * @param parent parent XML element
     * @param name   the child name the content should be picked up
     * @return the child content as a short value
     */
    public Date getChildTextAsShortDate(Element parent, String name) {
        return XmlFormatter.parseShortDate(getChildText(parent, name), null);
    }

}
