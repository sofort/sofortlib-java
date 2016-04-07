package com.sofort.lib.payment.products.response.parts;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNull;

import org.testng.annotations.Test;


public class TestPaymentStatusReason {

	@Test
	public void testStatus() {
		assertNull(PaymentStatusReason.get(null));
		assertEquals(PaymentStatusReason.NOT_CREDITED, PaymentStatusReason.get("NOT_CREDITED"));
		assertEquals(PaymentStatusReason.NOT_CREDITED_YET, PaymentStatusReason.get("NOT_CREDITED_YET"));
		assertEquals(PaymentStatusReason.CREDITED, PaymentStatusReason.get("CREDITED"));
		assertEquals(PaymentStatusReason.PARTIALLY_CREDITED, PaymentStatusReason.get("PARTIALLY_CREDITED"));
		assertEquals(PaymentStatusReason.OVERPAYMENT, PaymentStatusReason.get("OVERPAYMENT"));
		assertEquals(PaymentStatusReason.COMPENSATION, PaymentStatusReason.get("compensation"));
		assertEquals(PaymentStatusReason.REFUNDED, PaymentStatusReason.get("REFUNDED"));
		assertEquals(PaymentStatusReason.SOFORT_BANK_ACCOUNT_NEEDED, PaymentStatusReason.get("SOFORT_BANK_aCcOuNt_NEEded"));
	}


	@Test
	public void testNull() {
		assertNull(PaymentStatusReason.get(null));
	}


	@Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Unknown status reason: xyz")
	public void testStatusREasonFail() {
		PaymentStatusReason.get("xyz");
	}

}
