package com.sofort.lib.paycode.products.common;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNull;

import org.testng.annotations.Test;


public class TestPaycodeTransactionStatus {

	@Test
	public void testStatus() {
		assertEquals(PaycodeTransactionStatus.LOSS, PaycodeTransactionStatus.get("loss"));
		assertEquals(PaycodeTransactionStatus.PENDING, PaycodeTransactionStatus.get("PENDING"));
		assertEquals(PaycodeTransactionStatus.RECEIVED, PaycodeTransactionStatus.get("Received"));
		assertEquals(PaycodeTransactionStatus.REFUNDED, PaycodeTransactionStatus.get("ReFuNdEd"));
		assertEquals(PaycodeTransactionStatus.UNTRACEABLE, PaycodeTransactionStatus.get("unTRACEable"));

		assertEquals(5, PaycodeTransactionStatus.values().length);
	}


	@Test
	public void testNull() {
		assertNull(PaycodeTransactionStatus.get(null));
	}


	@Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Unknown paycode transaction status: xyz")
	public void testFail() {
		PaycodeTransactionStatus.get("xyz");
	}

}
