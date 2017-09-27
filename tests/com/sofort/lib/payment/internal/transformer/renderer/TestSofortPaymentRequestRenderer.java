package com.sofort.lib.payment.internal.transformer.renderer;

import static org.testng.AssertJUnit.assertEquals;

import java.util.Arrays;

import org.testng.annotations.Test;

import com.sofort.lib.core.internal.ResourceContentReader;
import com.sofort.lib.core.internal.utils.XmlNormalizer;
import com.sofort.lib.core.internal.utils.xml.XmlDocumentRenderable;
import com.sofort.lib.core.internal.utils.xml.XmlRendererHelperException;
import com.sofort.lib.core.products.common.BankAccount;
import com.sofort.lib.core.products.request.parts.Notification;
import com.sofort.lib.payment.products.request.PaymentRequest;


public class TestSofortPaymentRequestRenderer {

	@SuppressWarnings("deprecation")
	@Test
	public void testRenderer() throws XmlRendererHelperException {

		PaymentRequest spr = new PaymentRequest(1383, 1.66666666, "EUR", Arrays.asList("a b c d e f g h", "z z z z z z z"), true)
				.setInterfaceVersion("pn_test_1")
				.setLanguageCode("DE")
				.setTimeout(0)
				.setEmailCustomer("ach@och.uch")
				.setPhoneCustomer("4711 0815")
				.setUserVariables(Arrays.asList("1234", "absd"))
				.setSender(new BankAccount()
						.setHolder("Max Mustermann")
						.setAccountNumber("A1B2C3D4")
						.setBankCode("XYZA1234")
						.setCountryCode("ZZ")
						.setIban("ZZ9912345678901234567890")
						.setBic("ZBZBZBZB"))
				.setSuccessUrl("https://success.url")
				.setAbortUrl("https://abort.url")
				.setTimeoutUrl("https://timeout.url")
				.setNotificationUrls(null)
				.setNotificationEmails(Arrays.asList(
						new Notification("a@b.c"),
						new Notification("d@e.f"),
						new Notification("g@h.j")));

		XmlDocumentRenderable doc = new XmlDocumentRenderable("multipay");
		new PaymentRequestRenderer().render(spr, doc.getRoot());
		String xml = doc.getXml();

		String master = new ResourceContentReader("/xml/payment/SofortPayment.xml").getContent();

		assertEquals(
				XmlNormalizer.removeIndents(master.trim()),
				XmlNormalizer.removeIndents(xml.trim()));
	}
}
