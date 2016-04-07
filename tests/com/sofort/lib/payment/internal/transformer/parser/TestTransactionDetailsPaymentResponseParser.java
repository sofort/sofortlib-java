package com.sofort.lib.payment.internal.transformer.parser;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertNotNull;
import static org.testng.AssertJUnit.assertNotSame;
import static org.testng.AssertJUnit.assertNull;
import static org.testng.AssertJUnit.assertTrue;

import java.text.SimpleDateFormat;
import java.util.List;

import org.testng.annotations.Test;

import com.sofort.lib.core.internal.ResourceContentReader;
import com.sofort.lib.core.internal.transformer.parser.parts.FailureMessageParser;
import com.sofort.lib.core.internal.utils.xml.XmlDocumentParsable;
import com.sofort.lib.core.internal.utils.xml.XmlDocumentRenderable;
import com.sofort.lib.core.internal.utils.xml.XmlParserHelperException;
import com.sofort.lib.core.internal.utils.xml.XmlRendererHelperException;
import com.sofort.lib.core.internal.utils.xml.XmlVerifierException;
import com.sofort.lib.core.products.response.parts.FailureMessage;
import com.sofort.lib.payment.internal.transformer.parser.parts.PaymentTransactionDetailsParser;
import com.sofort.lib.payment.products.response.PaymentTransactionDetailsResponse;
import com.sofort.lib.payment.products.response.parts.PaymentStatus;
import com.sofort.lib.payment.products.response.parts.PaymentStatusReason;
import com.sofort.lib.payment.products.response.parts.PaymentTransactionDetails;


public class TestTransactionDetailsPaymentResponseParser {

	@Test
	public void testNotSupportedProduct() throws XmlRendererHelperException, XmlParserHelperException {
		XmlDocumentRenderable doc = new XmlDocumentRenderable("transaction_details");
		doc.getRoot().append("payment_method", "not_su");
		assertNull(new PaymentTransactionDetailsParser().parseChild(new XmlDocumentParsable(doc.getXml()).getRoot()));
	}


	@Test
	public void testError() throws XmlParserHelperException, XmlVerifierException {
		final String xml = new ResourceContentReader("/xml/payment/TransactionDetailsError.xml").getContent();

		XmlDocumentParsable doc = new XmlDocumentParsable(xml);

		assertEquals("errors", doc.getRoot().getName());

		List<FailureMessage> errors = new FailureMessageParser().parseChildren(doc.getRoot(), "error");
		doc.verify();

		assertNotNull(errors);

		assertEquals(1, errors.size());

		assertEquals("7000", errors.get(0).getCode());
		assertEquals("> required. line: 7, char: 42, tag: transaction_request", errors.get(0).getMessage());
	}


	@SuppressWarnings("deprecation")
	@Test
	public void testPayment() throws XmlParserHelperException, XmlVerifierException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HHmmss Z");

		final String xml = new ResourceContentReader("/xml/payment/TransactionDetailsPayment.xml").getContent();

		XmlDocumentParsable doc = new XmlDocumentParsable(xml);

		assertNotSame("errors", doc.getRoot().getName());

		PaymentTransactionDetailsResponse response = new PaymentTransactionDetailsResponseParser().parse(doc.getRoot());
		doc.verify();

		assertEquals(2, response.getTransactions().size());

		PaymentTransactionDetails t = response.getTransactions().get(0);
		assertEquals(1222, t.getProjectId());
		assertEquals("00907-01222-4CAC6137-7437", t.getTransId());
		assertFalse(t.isTest());
		assertEquals("20101006 134455 +0200", sdf.format(t.getTime()));

		assertEquals(PaymentStatus.LOSS, t.getStatus());
		assertEquals(PaymentStatusReason.NOT_CREDITED, t.getStatusReason());
		assertEquals("20110405 180603 +0200", sdf.format(t.getStatusModified()));

		assertNotNull(t.getStatusHistoryItems());
		assertEquals(1, t.getStatusHistoryItems().size());
		assertEquals(PaymentStatus.LOSS, t.getStatusHistoryItems().get(0).getStatus());
		assertEquals(PaymentStatusReason.NOT_CREDITED, t.getStatusHistoryItems().get(0).getStatusReason());
		assertEquals("20110405 180603 +0200", sdf.format(t.getStatusHistoryItems().get(0).getTime()));

		assertEquals("su", t.getPaymentMethod());
		assertEquals("en", t.getLanguageCode());
		assertEquals(4.34, t.getAmount(), 0.001);
		assertEquals(0.00, t.getAmountRefunded(), 0.001);
		assertEquals("EUR", t.getCurrencyCode());

		assertNotNull(t.getReasons());
		assertEquals(2, t.getReasons().size());
		assertEquals("Default Testüberweisung1", t.getReasons().get(0));
		assertEquals("Default Testüberweisung2", t.getReasons().get(1));

		assertNotNull(t.getUserVariables());
		assertEquals(0, t.getUserVariables().size());

		assertNotNull(t.getSender());
		assertEquals("", t.getSender().getHolder());
		assertEquals("", t.getSender().getAccountNumber());
		assertEquals("50050500", t.getSender().getBankCode());
		assertEquals("", t.getSender().getBankName());
		assertEquals("", t.getSender().getBic());
		assertEquals("", t.getSender().getIban());
		assertEquals("DE", t.getSender().getCountryCode());

		assertNotNull(t.getRecipient());
		assertEquals("Jan Pan", t.getRecipient().getHolder());
		assertEquals("12345678", t.getRecipient().getAccountNumber());
		assertEquals("50050500", t.getRecipient().getBankCode());
		assertEquals("Bank Bank", t.getRecipient().getBankName());
		assertEquals("BIC12BIC12", t.getRecipient().getBic());
		assertEquals("DE12345678901234567890", t.getRecipient().getIban());
		assertEquals("DE", t.getRecipient().getCountryCode());

		assertEquals("foobar@example.org", t.getEmailCustomer());
		assertEquals("", t.getPhoneCustomer());
		assertEquals(0, t.getExchangeRate(), 0.0001);

		assertNotNull(t.getCosts());
		assertEquals(0, t.getCosts().getFees(), 0.0001);
		assertEquals("EUR", t.getCosts().getCurrencyCode());
		assertEquals(1, t.getCosts().getExchangeRate(), 0.0001);

		assertTrue(t.isConsumerProtection());

		t = response.getTransactions().get(1);
		assertEquals(1222, t.getProjectId());
		assertEquals("00907-01222-4CAC79C4-A10D", t.getTransId());
		assertFalse(t.isTest());
		assertEquals("20101006 152940 +0200", sdf.format(t.getTime()));

		assertEquals(PaymentStatus.LOSS, t.getStatus());
		assertEquals(PaymentStatusReason.NOT_CREDITED, t.getStatusReason());
		assertEquals("20110405 180603 +0200", sdf.format(t.getStatusModified()));

		assertNotNull(t.getStatusHistoryItems());
		assertEquals(1, t.getStatusHistoryItems().size());
		/* no need to test the same values again */
	}


	@SuppressWarnings("deprecation")
	@Test
	public void testTransactionDetailsSeveralResponse() throws XmlParserHelperException, XmlVerifierException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");

		final String xml = new ResourceContentReader("/xml/payment/TransactionDetailsPaymentTime.xml").getContent();

		XmlDocumentParsable doc = new XmlDocumentParsable(xml);

		assertNotSame("errors", doc.getRoot().getName());

		PaymentTransactionDetailsResponse response = new PaymentTransactionDetailsResponseParser().parse(doc.getRoot());
		doc.verify();

		assertEquals(20, response.getTransactions().size());
		PaymentTransactionDetails t0 = response.getTransactions().get(0);

		assertEquals(1737, t0.getProjectId());
		assertEquals("12345-67890-A1B2C3D4-D995", t0.getTransId());
		assertTrue(t0.isTest());
		assertEquals("2013-02-22T13:55:32+0100", sdf.format(t0.getTime()));

		assertEquals(PaymentStatus.PENDING, t0.getStatus());
		assertEquals(PaymentStatusReason.NOT_CREDITED_YET, t0.getStatusReason());
		assertEquals("2013-02-22T13:55:32+0100", sdf.format(t0.getStatusModified()));

		assertEquals("su", t0.getPaymentMethod());
		assertEquals("de", t0.getLanguageCode());
		assertEquals(2.20, t0.getAmount(), 0.001);
		assertEquals(0.00, t0.getAmountRefunded(), 0.001);
		assertEquals("EUR", t0.getCurrencyCode());

		assertNotNull(t0.getReasons());
		assertEquals(2, t0.getReasons().size());
		assertEquals("SU Testueberweisung", t0.getReasons().get(0));
		assertEquals("Test VZ", t0.getReasons().get(1));

		assertNotNull(t0.getUserVariables());
		assertEquals(0, t0.getUserVariables().size());

		assertNotNull(t0.getSender());
		assertEquals("Max Mustermann", t0.getSender().getHolder());
		assertEquals("23456789", t0.getSender().getAccountNumber());
		assertEquals("00000", t0.getSender().getBankCode());
		assertEquals("Demo Bank", t0.getSender().getBankName());
		assertEquals("BIC12BIC12", t0.getSender().getBic());
		assertEquals("DE06000000000023456789", t0.getSender().getIban());
		assertEquals("DE", t0.getSender().getCountryCode());

		assertEquals("foobar@example.org", t0.getEmailCustomer());
		assertEquals("01234 / 5678 - 910,911", t0.getPhoneCustomer());
		assertEquals(1, t0.getExchangeRate(), 0.0001);

		assertNotNull(t0.getRecipient());
		assertEquals("Jan Pan", t0.getRecipient().getHolder());
		assertEquals("12345678", t0.getRecipient().getAccountNumber());
		assertEquals("50050500", t0.getRecipient().getBankCode());
		assertEquals("Bank Bank", t0.getRecipient().getBankName());
		assertEquals("BIC12BIC12", t0.getRecipient().getBic());
		assertEquals("DE12345678901234567890", t0.getRecipient().getIban());
		assertEquals("DE", t0.getRecipient().getCountryCode());

		assertNotNull(t0.getCosts());
		assertEquals(0, t0.getCosts().getFees(), 0.0001);
		assertEquals("EUR", t0.getCosts().getCurrencyCode());
		assertEquals(1, t0.getCosts().getExchangeRate(), 0.0001);

		assertFalse(t0.isConsumerProtection());

		assertNotNull(t0.getStatusHistoryItems());
		assertEquals(1, t0.getStatusHistoryItems().size());
		assertEquals(PaymentStatus.PENDING, t0.getStatusHistoryItems().get(0).getStatus());
		assertEquals(PaymentStatusReason.NOT_CREDITED_YET, t0.getStatusHistoryItems().get(0).getStatusReason());
		assertEquals("2013-02-22T13:55:32+0100", sdf.format(t0.getStatusHistoryItems().get(0).getTime()));
	}

}
