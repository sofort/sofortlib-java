package com.sofort.lib.ideal;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

import java.util.List;

import org.testng.annotations.Test;

import com.sofort.lib.core.ConnectionConfigTest;
import com.sofort.lib.core.ResourceConnector;
import com.sofort.lib.core.internal.net.ConnectionConfig;
import com.sofort.lib.core.internal.net.http.HttpConnectionData;
import com.sofort.lib.core.internal.transformer.DataHandler;
import com.sofort.lib.core.internal.transformer.xml.XmlDataHandler;
import com.sofort.lib.core.internal.transformer.xml.XmlRootEntry;
import com.sofort.lib.core.internal.utils.HashAlgorithm;
import com.sofort.lib.ideal.SofortLibIDeal;
import com.sofort.lib.ideal.internal.transformer.xml.XmlConfigIDeal;
import com.sofort.lib.ideal.products.request.IDealBanksRequest;
import com.sofort.lib.ideal.products.request.IDealRequest;
import com.sofort.lib.ideal.products.response.IDealBanksResponse;
import com.sofort.lib.ideal.products.response.IDealNotificationResponse;
import com.sofort.lib.ideal.products.response.parts.IDealBank;


public class TestSofortLibIDeal {

	private static final ResourceConnector connector = new ResourceConnector(new String[] {
			"/xml/ideal/IDealBanks2Request.xml",
			"/xml/ideal/IDealBanksRequest.xml"
	});


	@Test
	public void testIDealBankCodes() {
		ConnectionConfig config = new ConnectionConfigTest(connector);
		DataHandler dataHandler = new XmlDataHandler(new XmlConfigIDeal());
		SofortLibIDeal sofortLib = new SofortLibIDeal(config, dataHandler, HashAlgorithm.SHA1);

		/* send the request */
		IDealBanksResponse response = sofortLib.sendIDealBanksRequest();

		/* test the response */
		assertNotNull(response);
		List<IDealBank> banks = response.getBanks();
		assertEquals(10, banks.size());

		IDealBank bank = banks.get(0);
		assertEquals("0031", bank.getCode());
		assertEquals("ABN Amro", bank.getName());

		bank = banks.get(1);
		assertEquals("0091", bank.getCode());
		assertEquals("Friesland Bank", bank.getName());

		bank = banks.get(2);
		assertEquals("0721", bank.getCode());
		assertEquals("ING", bank.getName());

		bank = banks.get(3);
		assertEquals("0021", bank.getCode());
		assertEquals("Rabobank", bank.getName());

		bank = banks.get(4);
		assertEquals("0751", bank.getCode());
		assertEquals("SNS Bank", bank.getName());

		bank = banks.get(5);
		assertEquals("0761", bank.getCode());
		assertEquals("ASN Bank", bank.getName());

		bank = banks.get(6);
		assertEquals("0801", bank.getCode());
		assertEquals("Knab", bank.getName());

		bank = banks.get(7);
		assertEquals("0771", bank.getCode());
		assertEquals("RegioBank", bank.getName());

		bank = banks.get(8);
		assertEquals("0511", bank.getCode());
		assertEquals("Triodos Bank", bank.getName());

		bank = banks.get(9);
		assertEquals("0161", bank.getCode());
		assertEquals("Van Lanschot Bankiers", bank.getName());

	}


	@Test
	public void testIDealBankBics() {
		ConnectionConfig config = new ConnectionConfigTest(connector);
		DataHandler dataHandler = new XmlDataHandler(new XmlConfigIDeal() {

			@Override
			protected void initRootEntryMapping() {
				rootEntryMapping.put(IDealBanksRequest.class, new XmlRootEntry("bic_request"));
			}
		});
		SofortLibIDeal sofortLib = new SofortLibIDeal(config, dataHandler, HashAlgorithm.SHA1);

		/* send the request */
		IDealBanksResponse response = sofortLib.sendIDealBanksRequest();

		/* test the response */
		assertNotNull(response);
		List<IDealBank> banks = response.getBanks();
		assertEquals(10, banks.size());

		IDealBank bank = banks.get(0);
		assertEquals("ABNANL2A", bank.getCode());
		assertEquals("ABN Amro", bank.getName());

		bank = banks.get(1);
		assertEquals("FRBKNL2L", bank.getCode());
		assertEquals("Friesland Bank", bank.getName());

		bank = banks.get(2);
		assertEquals("INGBNL2A", bank.getCode());
		assertEquals("ING", bank.getName());

		bank = banks.get(3);
		assertEquals("RABONL2U", bank.getCode());
		assertEquals("Rabobank", bank.getName());

		bank = banks.get(4);
		assertEquals("SNSBNL2A", bank.getCode());
		assertEquals("SNS Bank", bank.getName());

		bank = banks.get(5);
		assertEquals("ASNBNL21", bank.getCode());
		assertEquals("ASN Bank", bank.getName());

		bank = banks.get(6);
		assertEquals("KNABNL2H", bank.getCode());
		assertEquals("Knab", bank.getName());

		bank = banks.get(7);
		assertEquals("RBRBNL21", bank.getCode());
		assertEquals("RegioBank", bank.getName());

		bank = banks.get(8);
		assertEquals("TRIONL2U", bank.getCode());
		assertEquals("Triodos Bank", bank.getName());

		bank = banks.get(9);
		assertEquals("FVLBNL22", bank.getCode());
		assertEquals("Van Lanschot Bankiers", bank.getName());

	}


	@Test
	public void testIDealPaymentUrl() {
		ConnectionConfig config = new ConnectionConfigTest(connector, new HttpConnectionData("https://s0f0rt/payment/ideal/", null));
		DataHandler dataHandler = new XmlDataHandler(new XmlConfigIDeal());
		SofortLibIDeal sofortLib = new SofortLibIDeal(config, dataHandler, HashAlgorithm.SHA1);

		IDealRequest request = new IDealRequest()
				.setUserId(1010)
				.setProjectId(4808)
				.setAmount(1.23)
				.setReason1("äÄöÖüÜß ÀàÂâÆæ")
				.setReason2("ÇçÉéÈèÊêËëÏïÎîÔôŒœÙùÛûŸÿ")
				.setUserVariable0("0th user variable")
				.setUserVariable1("1st user variable")
				.setUserVariable2("2nd user variable")
				.setUserVariable3("3rd user variable")
				.setUserVariable4("4th user variable")
				.setUserVariable5("5th user variable")
				.setSenderBankCode("0021")
				.setSenderAccountNumber("123456789")
				.setSenderHolder("Kaiser van der Haalz")
				.setSenderCountryId("NL")
				.setLanguageId("NL")
				.setInterfaceTimeout(600)
				.setInterfaceVersion("TEST iDeal Interface");

		String hash = sofortLib.calculateHash(request, "53(UR3");
		assertEquals("d61b20487f7f49f1c7a99e413b21e342d3015145", hash);

		String paymentUrl = sofortLib.getPaymentUrl(request, "53(UR3");
		assertEquals("https://s0f0rt/payment/ideal/?user_id=1010&project_id=4808&amount=1.23&reason_1=%C3%A4%C3%84%C3%B6%C3%96%C3%BC%C3%9C%C3%9F+%C3%80%C3%A0%C3%82%C3%A2%C3%86%C3%A6&reason_2=%C3%87%C3%A7%C3%89%C3%A9%C3%88%C3%A8%C3%8A%C3%AA%C3%8B%C3%AB%C3%8F%C3%AF%C3%8E%C3%AE%C3%94%C3%B4%C5%92%C5%93%C3%99%C3%B9%C3%9B%C3%BB%C5%B8%C3%BF&user_variable_0=0th+user+variable&user_variable_1=1st+user+variable&user_variable_2=2nd+user+variable&user_variable_3=3rd+user+variable&user_variable_4=4th+user+variable&user_variable_5=5th+user+variable&sender_bank_code=0021&sender_account_number=123456789&sender_holder=Kaiser+van+der+Haalz&sender_country_id=NL&hash=d61b20487f7f49f1c7a99e413b21e342d3015145&language_id=NL&interface_timeout=600&interface_version=TEST+iDeal+Interface", paymentUrl);
	}


	@Test
	public void testIDealNotification() {
		ConnectionConfig config = new ConnectionConfigTest(connector, new HttpConnectionData("https://s0f0rt/payment/ideal/", null));
		DataHandler dataHandler = new XmlDataHandler(new XmlConfigIDeal());
		SofortLibIDeal sofortLib = new SofortLibIDeal(config, dataHandler, HashAlgorithm.SHA1);

		String dateTime = "2013-12-11T10:09:08+0700";

		IDealNotificationResponse response = new IDealNotificationResponse()
				.setUserId(1010)
				.setProjectId(4808)
				.setSenderHolder("Kaser van der Haalz")
				.setSenderAccountNumber("123456789")
				.setSenderBankName("Rabobank")
				.setSenderBankBic("RABONL2U")
				.setSenderIban("NL44RABO0123456789")
				.setSenderCountryId("NL")
				.setRecepientHolder("Max Min")
				.setRecepientAccountNumber("00000")
				.setRecepientBankCode("88888888")
				.setRecepientBankName("Demo Bank")
				.setRecepientBankBic("")
				.setRecepientIban("")
				.setRecepientCountryId("DE")
				.setAmount(1.23)
				.setCurrencyId("EUR")
				.setReason1("äÄöÖüÜß ÀàÂâÆæ")
				.setReason2("ÇçÉéÈèÊêËëÏïÎîÔôŒœÙùÛûŸÿ")
				.setUserVariable0("0th user variable")
				.setUserVariable1("1st user variable")
				.setUserVariable2("2nd user variable")
				.setUserVariable3("3rd user variable")
				.setUserVariable4("4th user variable")
				.setUserVariable5("5th user variable")
				.setCreated(dateTime)
				.setHash("7ae2adddae37a19f4d7cd57a5b607020384ea713")
				.setStatus("pending")
				.setStatusModified(dateTime);

		String hash = sofortLib.calculateHash(response, "53(UR3");
		assertEquals(response.getHash(), hash);
	}
}
