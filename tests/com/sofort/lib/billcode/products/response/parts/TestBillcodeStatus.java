package com.sofort.lib.billcode.products.response.parts;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNull;

import org.testng.annotations.Test;

import com.sofort.lib.billcode.products.common.BillcodeTransactionStatus;


public class TestBillcodeStatus {

	@Test
	public void testStatus() {
		assertEquals(BillcodeStatus.OPEN, BillcodeStatus.get("open"));
		assertEquals(BillcodeStatus.USED, BillcodeStatus.get("USED"));
		assertEquals(BillcodeStatus.EXPIRED, BillcodeStatus.get("eXpIrEd"));

		assertEquals(3, BillcodeStatus.values().length);
	}


	@Test
	public void testNull() {
		assertNull(BillcodeTransactionStatus.get(null));
	}


	@Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Unknown billcode status: xyz")
	public void testFail() {
		BillcodeStatus.get("xyz");
	}

}
