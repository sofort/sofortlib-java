package com.sofort.lib.core.internal.utils;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class TestNumberUtilities {

	private NumberUtilities util;


	@BeforeMethod
	public void before() {
		util = new NumberUtilities();
	}


	@Test
	public void testFormatAmount() {
		assertEquals("0.00", util.formatAmount(0));
		assertEquals("0.01", util.formatAmount(0.01));
		assertEquals("0.10", util.formatAmount(0.1));
		assertEquals("1.00", util.formatAmount(1));
		assertEquals("1.10", util.formatAmount(1.1));
		assertEquals("1.01", util.formatAmount(1.01));
		assertEquals("1.11", util.formatAmount(1.11));
		assertEquals("1111.11", util.formatAmount(1111.11));
		assertEquals("1111111.11", util.formatAmount(1111111.11));
	}


	@Test
	public void testParseAmount() {
		assertEquals(0d, util.parseAmount("0"));

		assertEquals(0.01, util.parseAmount(".01"));
		assertEquals(0.01, util.parseAmount("0.01"));

		assertEquals(0.1, util.parseAmount(".1"));
		assertEquals(0.1, util.parseAmount("0.1"));

		assertEquals(.11, util.parseAmount(".11"));
		assertEquals(.11, util.parseAmount("0.11"));

		assertEquals(1d, util.parseAmount("1."));
		assertEquals(1d, util.parseAmount("1"));
		assertEquals(1d, util.parseAmount("1.0"));
		assertEquals(1d, util.parseAmount("1.00"));

		assertEquals(1.1, util.parseAmount("1.1"));
		assertEquals(1.1, util.parseAmount("1.10"));

		assertEquals(1.01, util.parseAmount("1.01"));
		assertEquals(1.11, util.parseAmount("1.11"));

		assertEquals(1111.11, util.parseAmount("1111.11"));
		assertEquals(1111111.11, util.parseAmount("1111111.11"));

		assertEquals(0d, util.parseAmount("0,11"));
		assertEquals(1d, util.parseAmount("1,11"));
		assertEquals(1111111d, util.parseAmount("1111111,11"));

		assertTrue(Double.isNaN(util.parseAmount("x")));
		assertTrue(Double.isNaN(util.parseAmount("x,0")));
		assertTrue(Double.isNaN(util.parseAmount("x.0")));
	}

}
