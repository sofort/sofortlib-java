package com.sofort.lib.paycode.products.common;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNull;

import org.testng.annotations.Test;


public class TestPaycodeTransactionStatusReason {

	@Test
	public void testStatus() {
		assertEquals(PaycodeTransactionStatusReason.NOT_CREDITED, PaycodeTransactionStatusReason.get("NOT_CREDITED"));
		assertEquals(PaycodeTransactionStatusReason.NOT_CREDITED_YET, PaycodeTransactionStatusReason.get("NOT_CREDITED_YET"));
		assertEquals(PaycodeTransactionStatusReason.CREDITED, PaycodeTransactionStatusReason.get("CREDITED"));
		assertEquals(PaycodeTransactionStatusReason.PARTIALLY_CREDITED, PaycodeTransactionStatusReason.get("PARTIALLY_credited"));
		assertEquals(PaycodeTransactionStatusReason.REFUNDED, PaycodeTransactionStatusReason.get("rEFundEd"));
		assertEquals(PaycodeTransactionStatusReason.SOFORT_BANK_ACCOUNT_NEEDED, PaycodeTransactionStatusReason.get("SOFORT_BANK_ACCOUNT_nEEdEd"));

		assertEquals(8, PaycodeTransactionStatusReason.values().length);
	}


	@Test
	public void testNull() {
		assertNull(PaycodeTransactionStatusReason.get(null));
	}


	@Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Unknown paycode transaction status reason: xyz")
	public void testStatusReasonFail() {
		PaycodeTransactionStatusReason.get("xyz");
	}

}
