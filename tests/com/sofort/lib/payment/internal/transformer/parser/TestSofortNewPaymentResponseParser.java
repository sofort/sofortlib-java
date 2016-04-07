package com.sofort.lib.payment.internal.transformer.parser;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;
import static org.testng.AssertJUnit.assertNull;

import org.testng.annotations.Test;

import com.sofort.lib.core.internal.ResourceContentReader;
import com.sofort.lib.core.internal.utils.xml.XmlDocumentParsable;
import com.sofort.lib.core.internal.utils.xml.XmlParserHelperException;
import com.sofort.lib.payment.internal.transformer.parser.PaymentResponseParser;
import com.sofort.lib.payment.products.response.PaymentResponse;


public class TestSofortNewPaymentResponseParser {

	@Test
	public void testNewPayment() throws XmlParserHelperException {

		final String xml = new ResourceContentReader("/xml/payment/NewPayment.xml").getContent();
		PaymentResponse response = new PaymentResponseParser().parse(new XmlDocumentParsable(xml).getRoot());

		assertEquals("1-2-3-4-5-6", response.getTransId());
		assertEquals("https://sofort.com/su/1-2-3-4-5-6", response.getPaymentUrl());

		assertNotNull(response.getNewPaymentWarnings());
		assertEquals(1, response.getNewPaymentWarnings().size());
		assertEquals("4711", response.getNewPaymentWarnings().get(0).getCode());
		assertNull(response.getNewPaymentWarnings().get(0).getField());
		assertEquals("0815 Message", response.getNewPaymentWarnings().get(0).getMessage());
		assertNull(response.getResponseWarnings());
		assertNull(response.getResponseErrors());
	}

}
