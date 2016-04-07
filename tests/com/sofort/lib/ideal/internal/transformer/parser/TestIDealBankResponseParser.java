package com.sofort.lib.ideal.internal.transformer.parser;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;
import static org.testng.AssertJUnit.assertNull;

import org.testng.annotations.Test;

import com.sofort.lib.core.internal.ResourceContentReader;
import com.sofort.lib.core.internal.utils.xml.XmlDocumentParsable;
import com.sofort.lib.core.internal.utils.xml.XmlParserHelperException;
import com.sofort.lib.core.internal.utils.xml.XmlVerifierException;
import com.sofort.lib.ideal.internal.transformer.parser.IDealBanksResponseParser;
import com.sofort.lib.ideal.products.response.IDealBanksResponse;


public class TestIDealBankResponseParser {

	@Test
	public void testParser() throws XmlParserHelperException, XmlVerifierException {

		final String xml = new ResourceContentReader("/xml/ideal/iDealBanks.xml").getContent();

		XmlDocumentParsable doc = new XmlDocumentParsable(xml);

		IDealBanksResponse response = new IDealBanksResponseParser().parse(doc.getRoot());
		doc.verify();

		assertNull(response.getErrors());

		assertNotNull(response.getBanks());
		assertEquals(10, response.getBanks().size());

		assertEquals("0031", response.getBanks().get(0).getCode());
		assertEquals("ABN Amro", response.getBanks().get(0).getName());

		assertEquals("0161", response.getBanks().get(9).getCode());
		assertEquals("Van Lanschot Bankiers", response.getBanks().get(9).getName());
	}


	@Test
	public void testParserError() throws XmlParserHelperException, XmlVerifierException {

		final String xml = new ResourceContentReader("/xml/ideal/iDealBanksError.xml").getContent();

		XmlDocumentParsable doc = new XmlDocumentParsable(xml);

		IDealBanksResponse response = new IDealBanksResponseParser().parse(doc.getRoot());
		doc.verify();

		assertNull(response.getBanks());

		assertNotNull(response.getErrors());
		assertEquals(1, response.getErrors().size());

		assertEquals("1000", response.getErrors().get(0).getCode());
		assertEquals("Invalid request.", response.getErrors().get(0).getMessage());
		assertNull(response.getErrors().get(0).getField());
	}
}
