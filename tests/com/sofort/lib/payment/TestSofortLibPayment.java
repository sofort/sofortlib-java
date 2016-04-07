package com.sofort.lib.payment;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertNotNull;
import static org.testng.AssertJUnit.assertNull;
import static org.testng.AssertJUnit.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.testng.annotations.Test;

import com.sofort.lib.core.ConnectionConfigTest;
import com.sofort.lib.core.ResourceConnector;
import com.sofort.lib.core.internal.net.ConnectionConfig;
import com.sofort.lib.core.internal.net.Connector;
import com.sofort.lib.core.internal.transformer.DataHandler;
import com.sofort.lib.core.internal.transformer.xml.XmlDataHandler;
import com.sofort.lib.core.products.common.BankAccount;
import com.sofort.lib.core.products.request.parts.Notification;
import com.sofort.lib.payment.internal.net.http.HttpConnectionConfigPayment;
import com.sofort.lib.payment.internal.transformer.xml.XmlConfigPayment;
import com.sofort.lib.payment.products.request.PaymentRequest;
import com.sofort.lib.payment.products.request.PaymentTransactionDetailsRequest;
import com.sofort.lib.payment.products.response.PaymentResponse;
import com.sofort.lib.payment.products.response.PaymentTransactionDetailsResponse;
import com.sofort.lib.payment.products.response.parts.PaymentStatus;
import com.sofort.lib.payment.products.response.parts.PaymentStatusReason;
import com.sofort.lib.payment.products.response.parts.PaymentTransactionDetails;


public class TestSofortLibPayment {

	private static final Connector connector = new ResourceConnector(new String[] {
			"/xml/payment/SofortPaymentRequest.xml",
			"/xml/payment/TransactionDetailsPaymentRequest.xml"
	});


	@Test
	public void testHttpConnectionConfig() {
		HttpConnectionConfigPayment config = new HttpConnectionConfigPayment(null, null);
		assertNotNull(config.getConnection(PaymentRequest.class));
		assertNotNull(config.getConnection(PaymentTransactionDetailsRequest.class));
	}


	@SuppressWarnings("deprecation")
	@Test
	public void testSofortPayment() {

		/* build the request */
		PaymentRequest request = new PaymentRequest(1234, 2.20, "EUR", Arrays.asList("a b c d e f g h", "z z z z z z z"), true)
				.setInterfaceVersion("pn_test_1")
				.setLanguageCode("DE")
				.setTimeout(0)
				.setEmailCustomer("lib@domain.com")
				.setPhoneCustomer("0644112345678")
				.setUserVariables(Arrays.asList("1337", "LEET"))
				.setSender(new BankAccount()
						.setHolder("Max Mustermann")
						.setAccountNumber("A1B2C3D4")
						.setBankCode("XYZA1234")
						.setCountryCode("DE")
						.setIban("DE4912345678901234567890")
						.setBic("BIC1BIC2"))
				.setSuccessUrl("http://shop.com/success")
				.setAbortUrl("http://shop.com/abort")
				.setTimeoutUrl("http://shop.com/timeout")
				.setNotificationUrls(Arrays.asList(
						new Notification("http://shop.com/notify")))
				.setNotificationEmails(Arrays.asList(
						new Notification("api@domain.com").setNotifyOn("loss"),
						new Notification("api@domain.de").setNotifyOn("pending,refunded"),
						new Notification("api@domain.net")));

		/* send the request */
		ConnectionConfig config = new ConnectionConfigTest(connector);
		DataHandler dataHandler = new XmlDataHandler(new XmlConfigPayment());
		SofortLibPayment sofortLib = new SofortLibPayment(config, dataHandler);
		PaymentResponse response = sofortLib.sendPaymentRequest(request);

		/* test the response */
		assertNotNull(response);
		assertNotNull(response.getTransId());
		assertEquals("00001-10000-A1B2C3D4-E5F6", response.getTransId());
		assertEquals("https://domain.com/payment/go/" + response.getTransId(), response.getPaymentUrl());
		assertNull(response.getNewPaymentWarnings());
		assertNull(response.getResponseErrors());
		assertNull(response.getResponseWarnings());
	}


	@SuppressWarnings("deprecation")
	@Test
	public void testTransactionDetailsPayment() {

		/* build the transaction details request */
		PaymentTransactionDetailsRequest request = new PaymentTransactionDetailsRequest()
				.setTransIds(Arrays.asList("00003-30000-A1B2C3D4-E5F6"));

		/* send the transaction details response */
		ConnectionConfig config = new ConnectionConfigTest(connector);
		DataHandler dataHandler = new XmlDataHandler(new XmlConfigPayment());
		SofortLibPayment sofortLib = new SofortLibPayment(config, dataHandler);
		PaymentTransactionDetailsResponse response = sofortLib.sendTransactionDetailsRequest(request);

		/* test the transaction details response */
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
		assertEquals(1, response.getTransactions().size());
		PaymentTransactionDetails t = response.getTransactions().get(0);

		assertEquals(1234, t.getProjectId());
		assertEquals("00002-20000-A1B2C3D4-E5F6", t.getTransId());
		assertTrue(t.isTest());
		assertEquals("2013-03-20T11:23:21+0100", sdf.format(t.getTime()));

		assertEquals(PaymentStatus.PENDING, t.getStatus());
		assertEquals(PaymentStatusReason.NOT_CREDITED_YET, t.getStatusReason());
		assertEquals("2013-03-20T11:23:21+0100", sdf.format(t.getStatusModified()));

		assertEquals("su", t.getPaymentMethod());
		assertEquals("de", t.getLanguageCode());
		assertEquals(2.20, t.getAmount(), 0.001);
		assertEquals(0.00, t.getAmountRefunded(), 0.001);
		assertEquals("EUR", t.getCurrencyCode());

		assertNotNull(t.getReasons());
		assertEquals(2, t.getReasons().size());
		assertEquals("SU Testueberweisung", t.getReasons().get(0));
		assertEquals("Test VZ", t.getReasons().get(1));

		assertNotNull(t.getUserVariables());
		assertEquals(0, t.getUserVariables().size());

		assertNotNull(t.getSender());
		assertEquals("Max Mustermann", t.getSender().getHolder());
		assertEquals("23456789", t.getSender().getAccountNumber());
		assertEquals("00000", t.getSender().getBankCode());
		assertEquals("Demo Bank", t.getSender().getBankName());
		assertEquals("BIC1BIC2", t.getSender().getBic());
		assertEquals("DE4912345678901234567890", t.getSender().getIban());
		assertEquals("DE", t.getSender().getCountryCode());

		assertEquals("foobar@example.org", t.getEmailCustomer());
		assertEquals("01223 / 4654 - 130,134", t.getPhoneCustomer());
		assertEquals(1, t.getExchangeRate(), 0.0001);

		assertNotNull(t.getRecipient());
		assertEquals("Max Mustermann", t.getRecipient().getHolder());
		assertEquals("1234567890", t.getRecipient().getAccountNumber());
		assertEquals("50050500", t.getRecipient().getBankCode());
		assertEquals("Bank Bank", t.getRecipient().getBankName());
		assertEquals("BIC1BIC2", t.getRecipient().getBic());
		assertEquals("DE4912345678901234567890", t.getRecipient().getIban());
		assertEquals("DE", t.getRecipient().getCountryCode());

		assertNotNull(t.getCosts());
		assertEquals(0, t.getCosts().getFees(), 0.0001);
		assertEquals("EUR", t.getCosts().getCurrencyCode());
		assertEquals(1, t.getCosts().getExchangeRate(), 0.0001);

		assertNotNull(t.getStatusHistoryItems());
		assertEquals(1, t.getStatusHistoryItems().size());
		assertEquals(PaymentStatus.PENDING, t.getStatusHistoryItems().get(0).getStatus());
		assertEquals(PaymentStatusReason.NOT_CREDITED_YET, t.getStatusHistoryItems().get(0).getStatusReason());
		assertEquals("2013-03-20T11:23:21+0100", sdf.format(t.getStatusHistoryItems().get(0).getTime()));

		assertFalse(t.isConsumerProtection());
	}
}
