package examples.ideal;

import java.util.List;
import java.util.ResourceBundle;

import com.sofort.lib.core.internal.net.ConnectionException;
import com.sofort.lib.core.internal.net.http.HttpAuthorizationException;
import com.sofort.lib.core.internal.net.http.HttpConnectionException;
import com.sofort.lib.core.internal.utils.HashAlgorithm;
import com.sofort.lib.core.internal.utils.StringUtilities;
import com.sofort.lib.ideal.DefaultSofortLibIDeal;
import com.sofort.lib.ideal.products.request.IDealRequest;
import com.sofort.lib.ideal.products.response.IDealBanksResponse;
import com.sofort.lib.ideal.products.response.IDealNotificationResponse;
import com.sofort.lib.ideal.products.response.parts.IDealBank;


/**
 * An example of usage of the SofortLib iDEAL.
 * 
 * Get the banks, to set proper the SENDER BANK CODE.
 * 
 * Calculate the hash for an iDEAL Payment.
 * 
 * Calculate the hash from notification.
 */
public class IDealExampleOfUsage {

	private static final ResourceBundle CORE_BUNDLE = ResourceBundle.getBundle("core");
	private static final int customerId = Integer.parseInt(CORE_BUNDLE.getString("customerId"));
	private static final int projectId = Integer.parseInt(CORE_BUNDLE.getString("projectId"));
	private static final String apiKey = CORE_BUNDLE.getString("apiKey");

	private static final ResourceBundle IDEAL_BUNDLE = ResourceBundle.getBundle("ideal");
	private static final HashAlgorithm hashAlgorithm = HashAlgorithm.valueOf(IDEAL_BUNDLE.getString("hashAlgorithm"));
	private static final String projectPassword = IDEAL_BUNDLE.getString("projectPassword");
	private static final String notificationPassword = IDEAL_BUNDLE.getString("notificationPassword");

	public static class IDealBankService {

		/**
		 * Receive a list of banks.
		 * 
		 * @return the list of banks.
		 */
		public List<IDealBank> getIDealBanks() {

			// get the banks.
			IDealBanksResponse banksResponse = doRequest();

			// handle banks.
			handleBanks(banksResponse);

			// return the banks list.
			return banksResponse.getBanks();
		}


		private IDealBanksResponse doRequest() {

			IDealBanksResponse banksResponse;
			try {
				banksResponse = new DefaultSofortLibIDeal(customerId, apiKey, hashAlgorithm).sendIDealBanksRequest();

			} catch (HttpAuthorizationException e) {
				System.err.println("The authorization with the given apiKey has been failed.");
				throw e;

			} catch (HttpConnectionException e) {
				System.err.println("The HTTP communication has been failed. Response/status code: " + e.getResponseCode());
				throw e;

			} catch (ConnectionException e) {
				System.err.println("The communication has been failed.");
				throw e;
			}

			// check and handle the response errors and warnings
			if (banksResponse.hasResponseErrors()) {
				throw new IllegalStateException("Response errors: " + new StringUtilities().glue(banksResponse.getResponseErrors(), " /// "));
			}
			if (banksResponse.hasResponseWarnings()) {
				System.err.println("Responsewarnings: " + new StringUtilities().glue(banksResponse.getResponseWarnings(), " /// "));
			}

			// check and handle the iDEAL errors
			if (banksResponse.hasErrors()) {
				System.err.println("IDeal Banks errors: " + new StringUtilities().glue(banksResponse.getErrors(), " /// "));
			}

			return banksResponse;
		}


		private void handleBanks(IDealBanksResponse banksResponse) {
			// handle received bank list, i.e. cache it
		}

	}

	public static class IDealURLService {

		public String generateUrl(double amount, String senderHolder, String senderCountryId, String senderBankCode, String reason1, String reason2) {

			IDealRequest request = new IDealRequest()
					.setUserId(customerId)
					.setProjectId(projectId)
					.setAmount(amount)
					.setReason1(reason1)
					.setReason2(reason2)
					.setSenderHolder(senderHolder)
					.setSenderCountryId(senderCountryId)
					.setSenderBankCode(senderBankCode);

			return generateUrl(request);

		}


		private String generateUrl(IDealRequest request) {
			return new DefaultSofortLibIDeal(customerId, apiKey, hashAlgorithm).getPaymentUrl(request, projectPassword);
		}

	}

	public static class IDealHashService {

		public String calculateProjectHash(double amount, String senderHolder, String senderCountryId, String senderBankCode, String reason1, String reason2) {

			IDealRequest request = new IDealRequest()
					.setUserId(customerId)
					.setProjectId(projectId)
					.setAmount(amount)
					.setReason1(reason1)
					.setReason2(reason2)
					.setSenderHolder(senderHolder)
					.setSenderCountryId(senderCountryId)
					.setSenderBankCode(senderBankCode);

			return calculateProjectHash(request);

		}


		private String calculateProjectHash(IDealRequest request) {
			return new DefaultSofortLibIDeal(customerId, apiKey, hashAlgorithm).calculateHash(request, projectPassword);
		}


		public String calculateNotificationHash(double amount, String senderHolder, String senderCountryId, String senderBankCode, String reason1, String reason2) {

			IDealNotificationResponse request = new IDealNotificationResponse()
					.setUserId(customerId)
					.setProjectId(projectId)
					.setAmount(amount)
					.setReason1(reason1)
					.setReason2(reason2)
					.setSenderHolder(senderHolder)
					.setSenderCountryId(senderCountryId)
					.setSenderBankBic(senderBankCode);

			return calculateNotificationHash(request);

		}


		private String calculateNotificationHash(IDealNotificationResponse request) {
			return new DefaultSofortLibIDeal(customerId, apiKey, hashAlgorithm).calculateHash(request, notificationPassword);
		}

	}

}
