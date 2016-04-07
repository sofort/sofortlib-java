package com.sofort.lib.ideal.products.ideal;

import static org.testng.AssertJUnit.assertEquals;

import org.testng.annotations.Test;

import com.sofort.lib.core.internal.utils.HashAlgorithm;
import com.sofort.lib.ideal.products.ideal.IDealNotificationResponseHashCalculator;
import com.sofort.lib.ideal.products.response.IDealNotificationResponse;


public class TestIDealNotificationHashCalculator {

	private static IDealNotificationResponse getRequest() {

		return new IDealNotificationResponse()
				.setTransId("a-b-c-d-e-f")
				.setUserId(123)
				.setProjectId(4567)
				.setSenderHolder("Paul Panzer")
				.setSenderAccountNumber("12345678")
				.setSenderBankName("BA-6AHK'b")
				.setSenderBankBic("A12B34C56")
				.setSenderIban("AB0012345678")
				.setSenderCountryId("ZZ")
				.setRecepientHolder("Nagari")
				.setRecepientAccountNumber("222233333")
				.setRecepientBankCode("0031")
				.setRecepientBankName("Amro")
				.setRecepientBankBic("NL11OO34")
				.setRecepientIban("NL001234123412341234")
				.setRecepientCountryId("NL")
				.setAmount(12.34)
				.setCurrencyId("ZZZ")
				.setReason1("ver 1")
				.setReason2("ver 2")
				.setUserVariable0("uv 0")
				.setUserVariable1("uv 1")
				.setUserVariable2("")
				.setUserVariable3("uv 3")
				.setUserVariable4(null)
				.setUserVariable5("uv 5")
				.setCreated("2012-11-10T07:08:09.111+02:00")
				.setStatus("ok")
				.setStatusModified("2012-11-10T07:08:09.111+02:00");
	}


	@Test
	public void testHashes() {
		IDealNotificationResponseHashCalculator hashCalculator = new IDealNotificationResponseHashCalculator(getRequest());

		String sha1 = hashCalculator.getHash("[{-=!S3CUR3D!=-}]", HashAlgorithm.SHA1);
		assertEquals("438c01cb4aeef841075f4dcd9f8ef86f35fb79de", sha1);

		String sha256 = hashCalculator.getHash("[{-=!S3CUR3D!=-}]", HashAlgorithm.SHA256);
		assertEquals("1c9128545f26b03875ee28da47335fa9626f30712965f0d99cd044458ffc7883", sha256);

		String sha512 = hashCalculator.getHash("[{-=!S3CUR3D!=-}]", HashAlgorithm.SHA512);
		assertEquals("d91ae6636d9435dc4cc3c5b65663222896774667f1b74be70900a448a1d06d4683af054d2441c58d711a93a1f77b612b6ca4c5a681c9366837ec0058022cccf2", sha512);
	}

}
