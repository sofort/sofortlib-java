package com.sofort.lib.core.internal.utils;

import static org.testng.AssertJUnit.assertEquals;

import org.testng.annotations.Test;


public class TestHashAlgorithm {

	@Test
	public void testHashAlgorithm() {
		assertEquals(HashAlgorithm.MD5, HashAlgorithm.get("MD5"));
		assertEquals(HashAlgorithm.SHA1, HashAlgorithm.get("SHA1"));
		assertEquals(HashAlgorithm.SHA256, HashAlgorithm.get("SHA256"));
		assertEquals(HashAlgorithm.SHA512, HashAlgorithm.get("SHA512"));

		assertEquals(HashAlgorithm.SHA512, HashAlgorithm.get("sha512"));
		assertEquals(HashAlgorithm.SHA512, HashAlgorithm.get("SHA 512"));
		assertEquals(HashAlgorithm.SHA512, HashAlgorithm.get("SHA-512"));
		assertEquals(HashAlgorithm.SHA512, HashAlgorithm.get("SHA_512"));
	}


	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testHashBuilderFail() {
		HashAlgorithm.get("XYZ");
	}
}
