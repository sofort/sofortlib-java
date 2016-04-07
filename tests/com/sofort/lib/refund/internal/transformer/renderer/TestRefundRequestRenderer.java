package com.sofort.lib.refund.internal.transformer.renderer;

import static org.testng.AssertJUnit.assertEquals;

import java.util.Arrays;

import org.testng.annotations.Test;

import com.sofort.lib.core.internal.ResourceContentReader;
import com.sofort.lib.core.internal.utils.XmlNormalizer;
import com.sofort.lib.core.internal.utils.xml.XmlDocumentRenderable;
import com.sofort.lib.core.internal.utils.xml.XmlRendererHelperException;
import com.sofort.lib.core.products.request.SofortLibRequest;
import com.sofort.lib.refund.products.RefundBankAccount;
import com.sofort.lib.refund.products.request.RefundRequest;
import com.sofort.lib.refund.products.request.parts.Refund;


public class TestRefundRequestRenderer {

	@Test
	public void testRenderer1() throws XmlRendererHelperException {

		SofortLibRequest request = new RefundRequest(
				Arrays.asList(
						new Refund("1-2-3-4-5-6", 47.11).setComment("niff-niff"),
						new Refund("7-8-9-a-b-c", 8.15).setComment("naff-naff"),
						new Refund("a-b-c-d-e-f", 50.00)))
								.setSender(new RefundBankAccount()
										.setHolder("Max Mix")
										.setIban("9999999999")
										.setBic("88888888"))
								.setTitle("Refund 4711");

		String xml = getXml(request);

		String master = new ResourceContentReader("/xml/refund/RefundRequest1.xml").getContent();

		assertEquals(
				XmlNormalizer.removeIndents(master.trim()),
				XmlNormalizer.removeIndents(xml.trim()));
	}


	@Test
	public void testRenderer2() throws XmlRendererHelperException {

		SofortLibRequest request = new RefundRequest(
				Arrays.asList(
						new Refund("00000-00000-00000000-0001", 0.50)
								.setComment("partial refund")
								.setPartialRefundId("xyz")
								.setReason1("1st Reason")
								.setReason2("2nd Reason")))
										.setTitle("Multipay API-Rückbuchung vom YYYY-MM-DD")
										.setSender(new RefundBankAccount()
												.setHolder("Max Mustermann")
												.setIban("9999999999")
												.setBic("88888888"));

		String xml = getXml(request);

		String master = new ResourceContentReader("/xml/refund/RefundRequest2.xml").getContent();

		assertEquals(
				XmlNormalizer.removeIndents(master.trim()),
				XmlNormalizer.removeIndents(xml.trim()));
	}


	@Test
	public void testRenderer3() throws XmlRendererHelperException {

		SofortLibRequest request = new RefundRequest(
				Arrays.asList(
						new Refund("00000-00000-00000000-0001", 0.50)
								.setComment("partial refund")
								.setPartialRefundId("xyz")
								.setReason1("1st Reason")
								.setReason2("2nd Reason")))
										.setTitle("Multipay API-Rückbuchung vom YYYY-MM-DD");

		String xml = getXml(request);

		String master = new ResourceContentReader("/xml/refund/RefundRequest3.xml").getContent();

		assertEquals(
				XmlNormalizer.removeIndents(master.trim()),
				XmlNormalizer.removeIndents(xml.trim()));
	}


	private static String getXml(SofortLibRequest request) throws XmlRendererHelperException {
		XmlDocumentRenderable doc = new XmlDocumentRenderable("refunds");
		new RefundRequestRenderer().render(request, doc.getRoot());

		return doc.getXml();
	}

}
