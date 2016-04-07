package examples.ideal;

import com.sofort.lib.core.internal.net.http.HttpConnectionException;
import com.sofort.lib.core.internal.utils.HashAlgorithm;
import com.sofort.lib.core.products.response.parts.FailureMessage;
import com.sofort.lib.ideal.DefaultSofortLibIDeal;
import com.sofort.lib.ideal.SofortLibIDeal;
import com.sofort.lib.ideal.products.request.IDealRequest;
import com.sofort.lib.ideal.products.response.IDealBanksResponse;
import com.sofort.lib.ideal.products.response.IDealNotificationResponse;
import com.sofort.lib.ideal.products.response.parts.IDealBank;


/**
 * An example of usage of the SofortLib iDEAL.
 */
public class IDealExampleOfUsage {

	public static void main(String[] args) {

		/* initialize the default sofort lib iDEAL */
		final int customerId = 4711;
		final String apiKey = "API-KEY-123";
		final int projectId = 815;
		final String projectPassword = "<< secret_password >>";
		final String notificationPassword = "<< secret_password >>";

		final SofortLibIDeal sofortLibIDeal = new DefaultSofortLibIDeal(customerId, apiKey, HashAlgorithm.SHA1);

		/*
		 * 1st step -> get the available bank list
		 */
		IDealBanksResponse banksResponse;
		try {
			banksResponse = sofortLibIDeal.sendIDealBanksRequest();
		} catch (HttpConnectionException e) {

			if (e.getResponseCode() == 401) {
				System.err.println("The authorization with the given apiKey has been failed.");
			}

			throw e;
		}
		if (banksResponse.hasErrors()) {
			// handle errors
			for (FailureMessage error : banksResponse.getErrors()) {
				// handle refund errors
				System.err.println(error.getCode() + " " + error.getMessage());
			}
		}

		for (IDealBank bank : banksResponse.getBanks()) {
			// build a bank selection
			System.out.println(bank.getName());
		}

		// use the selected bank for further steps
		IDealBank bank = banksResponse.getBanks().get(0);

		/*
		 * 2nd step, variant 'A' -> generate an iDEAL redirection URL (GET
		 * redirection method)
		 */
		IDealRequest request = new IDealRequest()
				.setUserId(customerId)
				.setProjectId(projectId)
				.setAmount(47.11)
				.setReason1("Reason1")
				.setReason2("Reason2")
				.setSenderHolder("Ms. Jones")
				.setSenderCountryId("NL")
				.setSenderBankCode(bank.getCode());

		// let the SofortLib generate the proper redirection URL. Hash value
		// will be set automatically!
		String redirectionUrl = sofortLibIDeal.getPaymentUrl(request, projectPassword);

		// redirect the user to the generated URL
		System.out.println(redirectionUrl);

		/*
		 * 2nd step, variant 'B' -> generate an iDEAL hash code (POST
		 * redirection method)
		 */
		String hash = sofortLibIDeal.calculateHash(request, projectPassword);

		// build a form in HTML with inputs, set all request values and the hash
		System.out.println("<input type='hidden' name='user_id' value='" + request.getUserId() + "'>");
		System.out.println("<input type='hidden' name='project_id' value='" + request.getProjectId() + "'>");
		System.out.println("<input type='hidden' name='...' value='...'>");
		System.out.println("<input type='hidden' name='hash' value='" + hash + "'>");

		/*
		 * 3rd step -> generate the hash code for manually notification response
		 */

		// read the received notification from storage and read the has value
		String receivedHash = "<< received 'hash' value >>";

		// fill the received field values into an IDealNotificationResponse
		// instance to calculate the hash
		IDealNotificationResponse notificationResponse = new IDealNotificationResponse()
				.setUserId(Integer.parseInt("<< received 'user_id' value >>"))
				.setProjectId(Integer.parseInt("<< received 'project_id' value >>"))
				.setAmount(Double.parseDouble("<< received 'amount' value >>"))
		// ///// set the rest of all received notification fields
		;

		// generate the hash value with your notification password
		String notificationHash = sofortLibIDeal.calculateHash(notificationResponse, notificationPassword);

		if (notificationHash.equals(receivedHash)) {
			// handle the received notification response
			System.out.println("received notification response is correct");
		} else {
			// something went wrong. check the project password and if all
			// received field values were set in IDealNotificationResponse
			System.err.println("received notification response is NOT correct");
		}

	}
}
