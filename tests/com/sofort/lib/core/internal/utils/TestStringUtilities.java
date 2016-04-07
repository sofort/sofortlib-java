package com.sofort.lib.core.internal.utils;

import static org.testng.AssertJUnit.assertEquals;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class TestStringUtilities {

	private StringUtilities util;


	@BeforeMethod
	public void before() {
		util = new StringUtilities();
	}


	@Test
	public void testToUrl() {
		assertEquals("http://abc.de/fg", util.toUrl("http://abc.de/fg").toString());
	}


	@Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Not proper url abc:def. unknown protocol: abc")
	public void testToUrlFail() {
		util.toUrl("abc:def");
	}


	@Test
	public void testUrlEncode() {
		assertEquals("a+b", util.urlEncode("a b"));
		assertEquals("a%2Bb", util.urlEncode("a+b"));
	}


	@Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Not proper encoding XYZ123.")
	public void testUrlEncodeFail() {
		util.urlEncode("a b", "XYZ123");
	}

}
