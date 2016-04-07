package com.sofort.lib.core.internal.utils;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNull;

import org.testng.annotations.Test;


public class TestHashBuilder {

	@Test
	public void testHashBuilder() {

		assertNull(new HashBuilder(null).getMd5());

		assertEquals("d41d8cd98f00b204e9800998ecf8427e", new HashBuilder("").getMd5());

		HashBuilder hash = new HashBuilder("a");
		assertEquals("0cc175b9c0f1b6a831c399e269772661", hash.getMd5());
		assertEquals("86f7e437faa5a7fce15d1ddcb9eaeaea377667b8", hash.getSha1());
		assertEquals("ca978112ca1bbdcafac231b39a23dc4da786eff8147c4e72b9807785afee48bb", hash.getSha256());

	}


	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testHashBuilderFail() {
		HashBuilder.getMessageDigest("ABC123");
	}
}
