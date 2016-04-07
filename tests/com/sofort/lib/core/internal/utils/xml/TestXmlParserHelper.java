package com.sofort.lib.core.internal.utils.xml;

import static org.testng.AssertJUnit.assertEquals;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;


public class TestXmlParserHelper {

	private XmlParserHelper parser;


	@BeforeMethod
	public void before() {
		parser = new XmlParserHelper();
	}


	@Test
	public void testParser() throws XmlParserHelperException, XmlVerifierException {
		String xml = "<html><head><title>Title</title></head><body><div style=\"float:left;\">Content</div></body></html>";

		Document doc = parser.parseXml(xml);

		Element head = parser.getChild(doc.getDocumentElement(), "head");
		Element title = parser.getChild(head, "title");
		assertEquals("Title", parser.getText(title));

		Element body = parser.getChild(doc.getDocumentElement(), "body");
		Element div = parser.getChild(body, "div");
		assertEquals("Content", parser.getText(div));
		assertEquals("float:left;", div.getAttribute("style"));

		parser.verify();
	}


	@Test(expectedExceptions = XmlVerifierException.class)
	public void testParserValidationFail() throws XmlParserHelperException, XmlVerifierException {
		String xml = "<html>HTML<body></body></html>";
		Document doc = parser.parseXml(xml);

		assertEquals("HTML", parser.getText(doc.getDocumentElement()));

		parser.verify();
	}


	@Test(expectedExceptions = XmlParserHelperException.class)
	public void testParserFail() throws XmlParserHelperException {
		parser.parseXml("non-xml");
	}


	@Test(expectedExceptions = XmlParserHelperException.class)
	public void testParserParserFail() throws XmlParserHelperException {
		parser.parseXml(new InputSource());
	}
}
