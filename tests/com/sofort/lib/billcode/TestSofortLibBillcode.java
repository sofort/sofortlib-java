package com.sofort.lib.billcode;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;
import static org.testng.AssertJUnit.assertNull;
import static org.testng.AssertJUnit.assertTrue;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.testng.annotations.Test;

import com.sofort.lib.billcode.internal.net.http.HttpConnectionConfigBillcode;
import com.sofort.lib.billcode.internal.transformer.renderer.BillcodeRequestRenderer;
import com.sofort.lib.billcode.internal.transformer.renderer.BillcodeStatusRequestRenderer;
import com.sofort.lib.billcode.internal.transformer.renderer.BillcodeTransactionDetailsRequestRenderer;
import com.sofort.lib.billcode.internal.transformer.xml.XmlConfigBillcode;
import com.sofort.lib.billcode.products.common.BillcodeTransactionStatus;
import com.sofort.lib.billcode.products.common.BillcodeTransactionStatusReason;
import com.sofort.lib.billcode.products.request.BillcodeRequest;
import com.sofort.lib.billcode.products.request.BillcodeStatusRequest;
import com.sofort.lib.billcode.products.request.BillcodeTransactionDetailsRequest;
import com.sofort.lib.billcode.products.response.BillcodeResponse;
import com.sofort.lib.billcode.products.response.BillcodeStatusResponse;
import com.sofort.lib.billcode.products.response.BillcodeTransactionDetailsResponse;
import com.sofort.lib.billcode.products.response.parts.BillcodeStatus;
import com.sofort.lib.billcode.products.response.parts.BillcodeTransactionDetails;
import com.sofort.lib.core.ConnectionConfigTest;
import com.sofort.lib.core.ResourceConnector;
import com.sofort.lib.core.internal.ResourceContentReader;
import com.sofort.lib.core.internal.net.ConnectionConfig;
import com.sofort.lib.core.internal.net.Connector;
import com.sofort.lib.core.internal.transformer.DataHandler;
import com.sofort.lib.core.internal.transformer.xml.XmlDataHandler;
import com.sofort.lib.core.internal.utils.Attribute;
import com.sofort.lib.core.internal.utils.xml.XmlDocumentRenderable;
import com.sofort.lib.core.internal.utils.xml.XmlFormatter;
import com.sofort.lib.core.internal.utils.xml.XmlRendererHelperException;
import com.sofort.lib.core.products.common.Bank;
import com.sofort.lib.core.products.request.parts.Notification;
import com.sofort.lib.core.products.response.parts.FailureMessage;


public class TestSofortLibBillcode {

	private static final String XML_BILLCODE = "/xml/billcode/BillcodeRequest.xml";
	private static final String XML_BILLCODE_STATUS = "/xml/billcode/BillcodeStatusRequest.xml";
	private static final String XML_BILLCODE_DETAILS = "/xml/billcode/BillcodeTransactionDetailsRequest.xml";
	private static final String XML_BILLCODE_DETAILS_TIME = "/xml/billcode/BillcodeTransactionDetailsTimeRequest.xml";
	private static final String XML_BILLCODE_DETAILS_ERROR = "/xml/billcode/BillcodeTransactionDetailsErrorRequest.xml";

	private static final Connector connector = new ResourceConnector(new String[] {
			XML_BILLCODE,
			XML_BILLCODE_STATUS,
			XML_BILLCODE_DETAILS,
			XML_BILLCODE_DETAILS_TIME,
			XML_BILLCODE_DETAILS_ERROR
	});


	private static DateFormat getDateFormat() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", XmlFormatter.DATE_LOCALE);
	}


	@Test
	public void testHttpConnectionConfig() {
		HttpConnectionConfigBillcode config = new HttpConnectionConfigBillcode(null, null);
		assertNotNull(config.getConnection(BillcodeRequest.class));
		assertNotNull(config.getConnection(BillcodeStatusRequest.class));
		assertNotNull(config.getConnection(BillcodeTransactionDetailsRequest.class));
	}


	@SuppressWarnings("deprecation")
	@Test
	public void testSofortBillcode() throws ParseException, XmlRendererHelperException {

		/* build the request */
		BillcodeRequest request = new BillcodeRequest(1234, 1.66666666, Arrays.asList("a b c d e f g h", "z z z z z z z"))
				.setInterfaceVersion("billcode_test_1")
				.setLanguageCode("DE")
				.setStartDate(getDateFormat().parse("2013-08-22 01:02:03"))
				.setEndDate(getDateFormat().parse("2013-08-23 01:02:03"))
				.setCurrencyCode("EUR")
				.setSender(new Bank()
						.setBankCode("XYZA1234")
						.setCountryCode("DE")
						.setBic("BIC1BIC2"))
				.setSuccessLinkRedirect(true)
				.setNotificationUrls(Arrays.asList(
						new Notification("http://shop.com/notify")))
				.setNotificationEmails(Arrays.asList(
						new Notification("api@domain.com").setNotifyOn("loss"),
						new Notification("api@domain.de").setNotifyOn("pending,refunded"),
						new Notification("api@domain.net")))
				.setUserVariables(Arrays.asList("1337", "LEET"));

		/* check the request rendering */
		ResourceContentReader reader = new ResourceContentReader(XML_BILLCODE);
		XmlDocumentRenderable doc = new XmlDocumentRenderable("billcode");
		new BillcodeRequestRenderer().render(request, doc.getRoot());
		assertEquals(
				reader.getContent().trim(),
				doc.getXml().trim());

		/* send the request */
		ConnectionConfig config = new ConnectionConfigTest(connector);
		DataHandler dataHandler = new XmlDataHandler(new XmlConfigBillcode());
		SofortLibBillcode sofortLibBillcode = new SofortLibBillcode(config, dataHandler);
		BillcodeResponse response = sofortLibBillcode.sendBillcodeRequest(request);

		/* test the response */
		assertNotNull(response);
		assertEquals("1234567890", response.getBillcode());
		assertEquals("https://domain.com/billcode/" + response.getBillcode(), response.getBillcodeUrl());

		assertNotNull(response.getWarnings());
		FailureMessage warning = response.getWarnings().get(0);
		assertEquals("4711", warning.getCode());
		assertEquals("Field 123", warning.getField());
		assertEquals("0815 Message", warning.getMessage());
	}


	@SuppressWarnings("deprecation")
	@Test
	public void testTransactionDetailsBillcode() throws XmlRendererHelperException {

		/* build the transaction details request */
		BillcodeTransactionDetailsRequest request = new BillcodeTransactionDetailsRequest()
				.setTransIds(Arrays.asList("12345-67890-A1B2C3D4-E5F6"));

		/* check the request rendering */
		ResourceContentReader reader = new ResourceContentReader(XML_BILLCODE_DETAILS);
		XmlDocumentRenderable doc = new XmlDocumentRenderable("transaction_request", new Attribute("version", "2.0"));
		new BillcodeTransactionDetailsRequestRenderer().render(request, doc.getRoot());
		assertEquals(
				reader.getContent().trim(),
				doc.getXml().trim());

		/* send the transaction details response */
		ConnectionConfig config = new ConnectionConfigTest(connector);
		DataHandler dataHandler = new XmlDataHandler(new XmlConfigBillcode());
		SofortLibBillcode sofortLibBillcode = new SofortLibBillcode(config, dataHandler);
		BillcodeTransactionDetailsResponse response = sofortLibBillcode.sendBillcodeTransactionDetailsRequest(request);

		/* test the transaction details response */
		assertEquals(1, response.getTransactionDetailsList().size());
		BillcodeTransactionDetails t = response.getTransactionDetailsList().get(0);

		assertEquals(1234, t.getProjectId());
		assertEquals("12345-67890-A1B2C3D4-E5F6", t.getTransId());
		assertTrue(t.isTest());
		assertEquals("2013-03-20 11:23:21", getDateFormat().format(t.getTime()));

		assertEquals(BillcodeTransactionStatus.PENDING, t.getStatus());
		assertEquals(BillcodeTransactionStatusReason.NOT_CREDITED_YET, t.getStatusReason());
		assertEquals("2013-03-20 11:23:21", getDateFormat().format(t.getStatusModified()));

		assertEquals("billcode", t.getPaymentMethod());
		assertEquals("de", t.getLanguageCode());
		assertEquals(1.67, t.getAmount(), 0.001);
		assertEquals(0.00, t.getAmountRefunded(), 0.001);
		assertEquals("EUR", t.getCurrencyCode());

		assertNotNull(t.getReasons());
		assertEquals(2, t.getReasons().size());
		assertEquals("Sofort billcode Test", t.getReasons().get(0));
		assertEquals("Test billcode Sofort", t.getReasons().get(1));

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
		assertEquals(BillcodeTransactionStatus.PENDING, t.getStatusHistoryItems().get(0).getStatus());
		assertEquals(BillcodeTransactionStatusReason.NOT_CREDITED_YET, t.getStatusHistoryItems().get(0).getStatusReason());
		assertEquals("2013-03-20 11:23:21", getDateFormat().format(t.getStatusHistoryItems().get(0).getTime()));

		assertEquals("1234567890", t.getBillcode());
	}


	@Test
	public void testTransactionDetailsErrorBillcode() throws XmlRendererHelperException, ParseException {
		/* build the transaction details request */
		BillcodeTransactionDetailsRequest request = new BillcodeTransactionDetailsRequest()
				.setFromTime(getDateFormat().parse("2012-01-01 00:00:00"))
				.setToTime(getDateFormat().parse("2012-12-31 23:59:59"))
				.setNumber(1000)
				.setPage(1)
				.setStatus(BillcodeTransactionStatus.PENDING);

		/* check the request rendering */
		ResourceContentReader reader = new ResourceContentReader(XML_BILLCODE_DETAILS_ERROR);
		XmlDocumentRenderable doc = new XmlDocumentRenderable("transaction_request", new Attribute("version", "2.0"));
		new BillcodeTransactionDetailsRequestRenderer().render(request, doc.getRoot());
		assertEquals(
				reader.getContent().trim(),
				doc.getXml().trim());

		/* send the transaction details response */
		ConnectionConfig config = new ConnectionConfigTest(connector);
		DataHandler dataHandler = new XmlDataHandler(new XmlConfigBillcode());
		SofortLibBillcode sofortLibBillcode = new SofortLibBillcode(config, dataHandler);
		BillcodeTransactionDetailsResponse response = sofortLibBillcode.sendBillcodeTransactionDetailsRequest(request);

		/* test the transaction details response */
		assertEquals(0, response.getTransactionDetailsList().size());

		// assertEquals(3, response.getErrors().size());
	}


	@Test
	public void testTransactionDetailsTimeBillcode() throws XmlRendererHelperException, ParseException {

		/* build the transaction details request */
		BillcodeTransactionDetailsRequest request = new BillcodeTransactionDetailsRequest()
				.setFromTime(getDateFormat().parse("2013-08-29 12:00:00"))
				.setToTime(getDateFormat().parse("2013-08-29 15:59:59"))
				.setNumber(20)
				.setPage(1);

		/* check the request rendering */
		ResourceContentReader reader = new ResourceContentReader(XML_BILLCODE_DETAILS_TIME);
		XmlDocumentRenderable doc = new XmlDocumentRenderable("transaction_request", new Attribute("version", "2.0"));
		new BillcodeTransactionDetailsRequestRenderer().render(request, doc.getRoot());
		assertEquals(
				reader.getContent().trim(),
				doc.getXml().trim());

		/* send the transaction details response */
		ConnectionConfig config = new ConnectionConfigTest(connector);
		DataHandler dataHandler = new XmlDataHandler(new XmlConfigBillcode());
		SofortLibBillcode sofortLibBillcode = new SofortLibBillcode(config, dataHandler);
		BillcodeTransactionDetailsResponse response = sofortLibBillcode.sendBillcodeTransactionDetailsRequest(request);

		/* test the transaction details response */
		assertEquals(2, response.getTransactionDetailsList().size());

		assertEquals("billcode", response.getTransactionDetailsList().get(0).getPaymentMethod());
		assertEquals("billcode", response.getTransactionDetailsList().get(1).getPaymentMethod());
	}


	@SuppressWarnings("deprecation")
	@Test
	public void testSofortBillcodeStatus() throws XmlRendererHelperException {
		/* build the request */
		BillcodeStatusRequest request = new BillcodeStatusRequest("123456789");

		/* check the request rendering */
		ResourceContentReader reader = new ResourceContentReader(XML_BILLCODE_STATUS);
		XmlDocumentRenderable doc = new XmlDocumentRenderable("billcode_request");
		new BillcodeStatusRequestRenderer().render(request, doc.getRoot());
		assertEquals(
				reader.getContent().trim(),
				doc.getXml().trim());

		/* send the request */
		ConnectionConfig config = new ConnectionConfigTest(connector);
		DataHandler dataHandler = new XmlDataHandler(new XmlConfigBillcode());
		SofortLibBillcode sofortLibBillcode = new SofortLibBillcode(config, dataHandler);
		BillcodeStatusResponse response = sofortLibBillcode.sendBillcodeStatusRequest(request);

		/* test the response */
		assertNotNull(response);

		assertEquals(BillcodeStatus.OPEN, response.getStatus());
		assertEquals("1234567890", response.getBillcode());
		assertEquals(1234, response.getProjectId());
		assertEquals("00002-20000-A1B2C3D4-E5F6", response.getTransId());
		assertEquals(1.67, response.getAmount(), 0.001);
		assertNotNull(response.getReasons());
		assertEquals(2, response.getReasons().size());
		assertEquals("Sofort billcode Test", response.getReasons().get(0));
		assertEquals("Test billcode Sofort", response.getReasons().get(1));
		assertEquals("2013-03-20 11:23:21", getDateFormat().format(response.getTimeCreated()));
		assertEquals("2013-07-20 11:23:21", getDateFormat().format(response.getTimeUsed()));
		assertEquals("2013-11-20 11:23:21", getDateFormat().format(response.getEndDate()));
		assertEquals("EUR", response.getCurrencyCode());
		assertEquals("de", response.getLanguageCode());
		assertNotNull(response.getSender());
		assertEquals("00000", response.getSender().getBankCode());
		assertEquals("BIC1BIC2", response.getSender().getBic());
		assertEquals("DE", response.getSender().getCountryCode());
		assertNotNull(response.getUserVariables());
		assertEquals(0, response.getUserVariables().size());

		assertNull(response.getResponseErrors());
		assertNull(response.getResponseWarnings());
	}

}
