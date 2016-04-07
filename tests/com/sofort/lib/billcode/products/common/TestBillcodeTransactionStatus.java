package com.sofort.lib.billcode.products.common;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNull;

import org.testng.annotations.Test;


public class TestBillcodeTransactionStatus {

	@Test
	public void testStatus() {
		assertEquals(BillcodeTransactionStatus.LOSS, BillcodeTransactionStatus.get("loss"));
		assertEquals(BillcodeTransactionStatus.PENDING, BillcodeTransactionStatus.get("PENDING"));
		assertEquals(BillcodeTransactionStatus.RECEIVED, BillcodeTransactionStatus.get("Received"));
		assertEquals(BillcodeTransactionStatus.REFUNDED, BillcodeTransactionStatus.get("ReFuNdEd"));

		assertEquals(5, BillcodeTransactionStatus.values().length);
	}


	@Test
	public void testNull() {
		assertNull(BillcodeTransactionStatus.get(null));
	}


	@Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Unknown billcode transaction status: xyz")
	public void testFail() {
		BillcodeTransactionStatus.get("xyz");
	}

}
