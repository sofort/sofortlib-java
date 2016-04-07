package com.sofort.lib.ideal.products.ideal;

import static org.testng.AssertJUnit.assertEquals;

import org.testng.annotations.Test;

import com.sofort.lib.core.internal.utils.HashAlgorithm;
import com.sofort.lib.ideal.products.ideal.IDealRequestHashCalculator;
import com.sofort.lib.ideal.products.request.IDealRequest;


public class TestIDealRequestHashCalculator {

	private static IDealRequest getRequest() {

		return new IDealRequest()
				.setUserId(123)
				.setProjectId(4567)
				.setAmount(12.34)
				.setSenderCountryId("NL")
				.setSenderBankCode("0031")
				.setSenderHolder("Prep Prop")
				.setSenderAccountNumber("12345678")
				.setReason1("ver 1")
				.setReason2("ver 2")
				.setUserVariable0("uv 0")
				.setUserVariable1("uv 1")
				.setUserVariable2("")
				.setUserVariable3("uv 3")
				.setUserVariable4(null)
				.setUserVariable5("uv 5");
	}


	@Test
	public void testHashes() {
		IDealRequestHashCalculator hashCalculator = new IDealRequestHashCalculator(getRequest());

		String sha1 = hashCalculator.getHash("[{-=!S3CUR3!=-}]", HashAlgorithm.SHA1);
		assertEquals("bff6646502996f48c25b2bdb6f8f190f747e5ed3", sha1);

		String sha256 = hashCalculator.getHash("[{-=!S3CUR3!=-}]", HashAlgorithm.SHA256);
		assertEquals("f97d6e9159ca058975802613f9e084c75797ac6579b0bdaa4e17176e1b1f68a9", sha256);

		String sha512 = hashCalculator.getHash("[{-=!S3CUR3!=-}]", HashAlgorithm.SHA512);
		assertEquals("b22d3e578e531bfb8e429cebad23fe7992dd72853ae426888b2c320f02292e2072c36e3986a6f499613f2cd47dbc6a48dad61e5673ba7b62c5d9a708d0198311", sha512);
	}
}
