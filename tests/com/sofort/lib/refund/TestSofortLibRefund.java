package com.sofort.lib.refund;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;
import static org.testng.AssertJUnit.assertNull;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.testng.annotations.Test;

import com.sofort.lib.core.ConnectionConfigTest;
import com.sofort.lib.core.ResourceConnector;
import com.sofort.lib.core.internal.net.ConnectionConfig;
import com.sofort.lib.core.internal.transformer.DataHandler;
import com.sofort.lib.core.internal.transformer.xml.XmlDataHandler;
import com.sofort.lib.refund.SofortLibRefund;
import com.sofort.lib.refund.internal.transformer.xml.XmlConfigRefund;
import com.sofort.lib.refund.products.RefundBankAccount;
import com.sofort.lib.refund.products.request.RefundRequest;
import com.sofort.lib.refund.products.request.parts.Refund;
import com.sofort.lib.refund.products.response.RefundResponse;


public class TestSofortLibRefund {

	private static final ResourceConnector connector = new ResourceConnector(new String[] {
			"/xml/refund/RefundRequest.xml"
	});


	@Test
	public void testRefundResponse() {
		/* build the refund request */
		RefundRequest request = new RefundRequest(
				Arrays.asList(
						new Refund("00002-20000-A1B2C3D4-E5F6", 0.50)
								.setComment("partial refund 2")
								.setReason1("1st Reason")
								.setReason2("2nd Reason")
								.setPartialRefundId("xyz")))
				.setTitle("Multipay API-Rückbuchung vom YYYY-MM-DD")
				.setSender(new RefundBankAccount()
						.setHolder("Max Mustermann")
						.setIban("9999999999")
						.setBic("88888888"));

		/* send the refund request */
		ConnectionConfig config = new ConnectionConfigTest(connector);
		DataHandler dataHandler = new XmlDataHandler(new XmlConfigRefund());
		SofortLibRefund sofortLib = new SofortLibRefund(config, dataHandler);
		RefundResponse response = sofortLib.sendRefundRequest(request);

		/* test the refund response */
		assertNotNull(response);
		assertNotNull(response.getSender());
		assertEquals("Max Mustermann", response.getSender().getHolder());
		assertEquals("Test Bank", response.getSender().getBankName());
		assertEquals("9999999999", response.getSender().getIban());
		assertEquals("88888888", response.getSender().getBic());

		assertEquals("Multipay API-Rückbuchung vom YYYY-MM-DD", response.getTitle());

		assertEquals(1, response.getRefunds().size());

		com.sofort.lib.refund.products.response.parts.Refund refund = response.getRefunds().get(0);
		assertEquals("00002-20000-A1B2C3D4-E5F6", refund.getTransId());
		assertEquals(0.50, refund.getAmount(), 0.001);
		assertEquals("partial refund 2", refund.getComment());
		assertEquals("1st Reason", refund.getReason1());
		assertEquals("2nd Reason", refund.getReason2());
		assertEquals("xyz", refund.getPartialRefundId());
		assertEquals("error", refund.getStatus());

		assertNotNull(refund.getRecipient());
		assertEquals("Mix Max", refund.getRecipient().getHolder());
		assertEquals("Test Bank", refund.getRecipient().getBankName());
		assertEquals("99999999992", refund.getRecipient().getIban());
		assertEquals("888888882", refund.getRecipient().getBic());

		assertNotNull(refund.getErrors());
		assertEquals("2013-03-20T11:23:21+0100", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").format(refund.getTime()));
		assertEquals(1, refund.getErrors().size());
		assertEquals("5004", refund.getErrors().get(0).getCode());
		assertNull(refund.getErrors().get(0).getField());
		assertEquals("Transaction has not been received yet.", refund.getErrors().get(0).getMessage());

		assertEquals("No pain, no gain!", response.getPain());
	}

}
