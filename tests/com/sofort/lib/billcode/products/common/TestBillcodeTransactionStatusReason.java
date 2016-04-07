package com.sofort.lib.billcode.products.common;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNull;

import org.testng.annotations.Test;


public class TestBillcodeTransactionStatusReason {

	@Test
	public void testStatus() {
		assertEquals(BillcodeTransactionStatusReason.NOT_CREDITED, BillcodeTransactionStatusReason.get("NOT_CREDITED"));
		assertEquals(BillcodeTransactionStatusReason.NOT_CREDITED_YET, BillcodeTransactionStatusReason.get("NOT_CREDITED_YET"));
		assertEquals(BillcodeTransactionStatusReason.CREDITED, BillcodeTransactionStatusReason.get("CREDITED"));
		assertEquals(BillcodeTransactionStatusReason.PARTIALLY_CREDITED, BillcodeTransactionStatusReason.get("PARTIALLY_credited"));
		assertEquals(BillcodeTransactionStatusReason.OVERPAYMENT, BillcodeTransactionStatusReason.get("Overpayment"));
		assertEquals(BillcodeTransactionStatusReason.COMPENSATION, BillcodeTransactionStatusReason.get("compensation"));
		assertEquals(BillcodeTransactionStatusReason.REFUNDED, BillcodeTransactionStatusReason.get("rEFundEd"));
		assertEquals(BillcodeTransactionStatusReason.SOFORT_BANK_ACCOUNT_NEEDED, BillcodeTransactionStatusReason.get("SOFORT_BANK_ACCOUNT_NEEDED"));

		assertEquals(8, BillcodeTransactionStatusReason.values().length);
	}


	@Test
	public void testNull() {
		assertNull(BillcodeTransactionStatusReason.get(null));
	}


	@Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Unknown billcode transaction status reason: xyz")
	public void testStatusReasonFail() {
		BillcodeTransactionStatusReason.get("xyz");
	}

}
