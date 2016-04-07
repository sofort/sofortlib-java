package com.sofort.lib.payment.products.response.parts;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNull;

import org.testng.annotations.Test;


public class TestPaymentStatus {

	@Test
	public void testStatus() {
		assertEquals(PaymentStatus.LOSS, PaymentStatus.get("loss"));
		assertEquals(PaymentStatus.PENDING, PaymentStatus.get("pending"));
		assertEquals(PaymentStatus.RECEIVED, PaymentStatus.get("received"));
		assertEquals(PaymentStatus.REFUNDED, PaymentStatus.get("ReFuNdEd"));
		assertEquals(PaymentStatus.UNTRACEABLE, PaymentStatus.get("UNTRaceable"));
	}


	@Test
	public void testNull() {
		assertNull(PaymentStatus.get(null));
	}


	@Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Unknown status: xyz")
	public void testFail() {
		PaymentStatus.get("xyz");
	}

}
