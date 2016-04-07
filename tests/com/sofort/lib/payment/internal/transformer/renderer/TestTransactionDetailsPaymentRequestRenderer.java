package com.sofort.lib.payment.internal.transformer.renderer;

import static org.testng.AssertJUnit.assertEquals;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;

import org.testng.annotations.Test;

import com.sofort.lib.core.internal.ResourceContentReader;
import com.sofort.lib.core.internal.utils.Attribute;
import com.sofort.lib.core.internal.utils.XmlNormalizer;
import com.sofort.lib.core.internal.utils.xml.XmlDocumentRenderable;
import com.sofort.lib.core.internal.utils.xml.XmlFormatter;
import com.sofort.lib.core.internal.utils.xml.XmlRendererHelperException;
import com.sofort.lib.payment.internal.transformer.renderer.PaymentTransactionDetailsRequestRenderer;
import com.sofort.lib.payment.products.request.PaymentTransactionDetailsRequest;


public class TestTransactionDetailsPaymentRequestRenderer {

	@Test
	public void testRenderer1() throws XmlRendererHelperException {
		Calendar c = Calendar.getInstance();
		String xmlDate = new SimpleDateFormat(XmlFormatter.DATE_FORMAT).format(c.getTime()).replaceFirst("(\\d{2})$", ":$1");

		PaymentTransactionDetailsRequest tr = new PaymentTransactionDetailsRequest()
				.setFromTime(c.getTime())
				.setToTime(c.getTime())
				.setNumber(35);

		XmlDocumentRenderable doc = new XmlDocumentRenderable("transaction_request", new Attribute("version", "2"));
		new PaymentTransactionDetailsRequestRenderer().render(tr, doc.getRoot());
		String xml = doc.getXml();

		String master = String.format(new ResourceContentReader("/xml/payment/TransactionDetailsDate.xml").getContent(), xmlDate, xmlDate);
		assertEquals(
				XmlNormalizer.removeIndents(master.trim()),
				XmlNormalizer.removeIndents(xml.trim()));
	}


	@Test
	public void testRenderer2() throws XmlRendererHelperException {

		PaymentTransactionDetailsRequest tr = new PaymentTransactionDetailsRequest()
				.setTransIds(Arrays.asList("00001-00015-5130B6EA-8DF8", "10001-00015-5130B6EA-8DF8"))
				.setNumber(35)
				.setPage(2);

		XmlDocumentRenderable doc = new XmlDocumentRenderable("transaction_request", new Attribute("version", "2"));
		new PaymentTransactionDetailsRequestRenderer().render(tr, doc.getRoot());
		String xml = doc.getXml();

		String master = new ResourceContentReader("/xml/payment/TransactionDetailsTransIds.xml").getContent();
		assertEquals(
				XmlNormalizer.removeIndents(master.trim()),
				XmlNormalizer.removeIndents(xml.trim()));
	}
}
