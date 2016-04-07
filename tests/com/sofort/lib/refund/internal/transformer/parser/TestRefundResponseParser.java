package com.sofort.lib.refund.internal.transformer.parser;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;
import static org.testng.AssertJUnit.assertNull;

import org.testng.annotations.Test;

import com.sofort.lib.core.internal.ResourceContentReader;
import com.sofort.lib.core.internal.utils.xml.XmlDocumentParsable;
import com.sofort.lib.core.internal.utils.xml.XmlParserHelperException;
import com.sofort.lib.refund.products.response.RefundResponse;
import com.sofort.lib.refund.products.response.parts.Refund;


public class TestRefundResponseParser {

	@Test
	public void testParser1() throws XmlParserHelperException {

		final String xml = new ResourceContentReader("/xml/refund/RefundResponse1.xml").getContent();

		XmlDocumentParsable doc = new XmlDocumentParsable(xml);
		RefundResponse response = new RefundResponseParser().parse(doc.getRoot());

		assertNotNull(response);
		assertNull(response.getErrors());

		assertEquals("test", response.getTitle());

		assertNotNull(response.getSender());
		assertEquals("Max Mustermann", response.getSender().getHolder());
		assertEquals("Test Bank", response.getSender().getBankName());
		assertEquals("9999999999", response.getSender().getIban());
		assertEquals("88888888", response.getSender().getBic());

		assertEquals(2, response.getRefunds().size());

		Refund r1 = response.getRefunds().get(0);
		assertEquals("00000-00000-00000000-0000", r1.getTransId());
		assertEquals(1.11, r1.getAmount(), 0.001);
		assertEquals("Order cancelled by user. Commodities already sent back", r1.getComment());
		assertEquals("ok", r1.getStatus());
		assertNull(r1.getErrors());

		Refund r2 = response.getRefunds().get(1);
		assertEquals("00000-00000-00000000-0001", r2.getTransId());
		assertEquals(1.12, r2.getAmount(), 0.001);
		assertNull(r2.getComment());
		assertEquals("error", r2.getStatus());

		assertNotNull(r2.getErrors());

		assertEquals(1, r2.getErrors().size());

		assertEquals("5002", r2.getErrors().get(0).getCode());
		assertNull(r2.getErrors().get(0).getField());
		assertEquals("Transaction not found", r2.getErrors().get(0).getMessage());

		assertEquals("No pain, no gain!", response.getPain());

	}


	@Test
	public void testParser2() throws XmlParserHelperException {

		final String xml = new ResourceContentReader("/xml/refund/RefundResponse2.xml").getContent();

		XmlDocumentParsable doc = new XmlDocumentParsable(xml);
		RefundResponse response = new RefundResponseParser().parse(doc.getRoot());

		assertNotNull(response);
		assertNull(response.getErrors());

		assertEquals("Multipay API-Rückbuchung vom YYYY-MM-DD", response.getTitle());

		assertNotNull(response.getSender());
		assertEquals("Max Mustermann", response.getSender().getHolder());
		assertEquals("Test Bank", response.getSender().getBankName());
		assertEquals("9999999999", response.getSender().getIban());
		assertEquals("88888888", response.getSender().getBic());

		assertEquals(1, response.getRefunds().size());

		Refund refund = response.getRefunds().get(0);
		assertEquals("00000-00000-00000000-0001", refund.getTransId());
		assertEquals(0.50, refund.getAmount(), 0.001);
		assertEquals("partial refund", refund.getComment());
		assertEquals("1st Reason", refund.getReason1());
		assertEquals("2nd Reason", refund.getReason2());
		assertEquals("xyz", refund.getPartialRefundId());
		assertEquals("error", refund.getStatus());

		assertNotNull(refund.getErrors());
		assertEquals(1, refund.getErrors().size());

		assertEquals("5002", refund.getErrors().get(0).getCode());
		assertNull(refund.getErrors().get(0).getField());
		assertEquals("Transaction could not be found.", refund.getErrors().get(0).getMessage());

		assertEquals("No pain, no gain!", response.getPain());
	}


	@Test
	public void testParser3() throws XmlParserHelperException {

		final String xml = new ResourceContentReader("/xml/refund/RefundResponse3.xml").getContent();

		XmlDocumentParsable doc = new XmlDocumentParsable(xml);
		RefundResponse response = new RefundResponseParser().parse(doc.getRoot());

		assertNotNull(response);
		assertNull(response.getErrors());

		assertNotNull(response.getSender());
		assertEquals("Max Mustermann", response.getSender().getHolder());
		assertEquals("Test Bank", response.getSender().getBankName());
		assertEquals("9999999999", response.getSender().getIban());
		assertEquals("88888888", response.getSender().getBic());

		assertEquals("Multipay API-Rückbuchung vom YYYY-MM-DD", response.getTitle());

		assertEquals("No pain, no gain!", response.getPain());

		assertEquals(1, response.getRefunds().size());

		com.sofort.lib.refund.products.response.parts.Refund refund = response.getRefunds().get(0);
		assertEquals("00907-01737-5127A100-7FD1", refund.getTransId());
		assertEquals(0.50, refund.getAmount(), 0.001);
		assertEquals("partial refund", refund.getComment());
		assertEquals("1st Reason", refund.getReason1());
		assertEquals("2nd Reason", refund.getReason2());
		assertEquals("xyz", refund.getPartialRefundId());
		assertEquals("ok", refund.getStatus());
		assertNull(refund.getTime());

		assertNull(refund.getErrors());
		assertEquals("No pain, no gain!", response.getPain());

	}
}
