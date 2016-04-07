package examples.paycode;

import java.util.Arrays;

import com.sofort.lib.core.internal.net.ConnectionException;
import com.sofort.lib.core.internal.net.http.HttpAuthorizationException;
import com.sofort.lib.core.internal.net.http.HttpConnectionException;
import com.sofort.lib.core.internal.transformer.RawResponse;
import com.sofort.lib.core.internal.transformer.RawResponse.Status;
import com.sofort.lib.core.products.request.parts.Notification;
import com.sofort.lib.core.products.response.SofortTransactionStatusNotification;
import com.sofort.lib.paycode.DefaultSofortLibPaycode;
import com.sofort.lib.paycode.SofortLibPaycode;
import com.sofort.lib.paycode.products.request.PaycodeRequest;
import com.sofort.lib.paycode.products.request.PaycodeStatusRequest;
import com.sofort.lib.paycode.products.request.PaycodeTransactionDetailsRequest;
import com.sofort.lib.paycode.products.response.PaycodeResponse;
import com.sofort.lib.paycode.products.response.PaycodeStatusResponse;
import com.sofort.lib.paycode.products.response.PaycodeTransactionDetailsResponse;
import com.sofort.lib.paycode.products.response.parts.PaycodeTransactionDetails;


/**
 * An example of usage of the SofortLib Payment (SOFORT Überweisung).
 */
public class PaycodeExampleOfUsage {

	public static void main(String[] args) {

		/* initialize the default sofort lib paycode */
		final int customerId = 4711;
		final int projectId = 8150;
		final String apiKey = "API-KEY";

		final SofortLibPaycode sofortLibPaycode = new DefaultSofortLibPaycode(customerId, apiKey);

		/*
		 * 1st step -> start sofort paycode, check for errors and warnings and
		 * use the received paycode url for redirection of the buyer
		 */
		final String statusNotificationUrl = "https://my.shop/sofort/paycodeStatusNotification";
		final String successUrl = "https://my.shop/sofort/successfulPaycode";

		PaycodeRequest paycodeRequest = new PaycodeRequest(projectId, 19.99, Arrays.asList("Customer 47110815", "Bill 08154711"));

		paycodeRequest.setNotificationUrls(Arrays.asList(new Notification(statusNotificationUrl)));
		paycodeRequest.setSuccessUrl(successUrl);

		PaycodeResponse paycodeResponse;
		try {
			paycodeResponse = sofortLibPaycode.sendPaycodeRequest(paycodeRequest);
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

		if (paycodeResponse.hasResponseErrors() || paycodeResponse.hasResponseWarnings()) {
			// check and handle the response errors and warnings
		}

		if (paycodeResponse.hasWarnings()) {
			// check the warnings
		}

		// start/resume/check listening on notificationUrls

		// Store or handle transId.
		System.out.println(paycodeResponse.getPaycode());
		// Redirect customer to paycode URL.
		System.out.println(paycodeResponse.getPaycodeUrl());

		/*
		 * 2nd step -> parse the received transaction changes notification
		 */
		String statusNotification = /* received status notification */"";
		SofortTransactionStatusNotification statusNotificationResponse = sofortLibPaycode.parseStatusNotificationResponse(new RawResponse(Status.OK, statusNotification));
		String statusNotificationTransId = statusNotificationResponse.getTransId();

		// handle notification responses
		System.out.println(statusNotificationTransId);

		/*
		 * 3rd step -> get paycode status
		 */
		PaycodeStatusRequest statusRequest = new PaycodeStatusRequest(paycodeResponse.getPaycode());
		PaycodeStatusResponse statusResponse;
		try {
			statusResponse = sofortLibPaycode.sendPaycodeStatusRequest(statusRequest);
		} catch (HttpConnectionException e) {

			if (e.getResponseCode() == 401) {
				System.err.println("The authorization with the given apiKey has been failed.");
			}

			throw e;
		}

		// handle current status
		System.out.println("Current paycode status: " + statusResponse.getStatus() + ", Used on: " + statusResponse.getTimeUsed());

		/*
		 * 4th step -> get transaction details for notified transaction
		 */
		PaycodeTransactionDetailsRequest transactionRequest = new PaycodeTransactionDetailsRequest()
				.setTransIds(Arrays.asList(statusNotificationTransId));
		PaycodeTransactionDetailsResponse transactionDetailsResponse;
		try {
			transactionDetailsResponse = sofortLibPaycode.sendPaycodeTransactionDetailsRequest(transactionRequest);
		} catch (HttpConnectionException e) {

			if (e.getResponseCode() == 401) {
				System.err.println("The authorization with the given apiKey has been failed.");
			}

			throw e;
		}
		PaycodeTransactionDetails detailsPaycode = transactionDetailsResponse.getTransactionDetailsList().get(0);

		// handle current transaction status
		System.out.println(detailsPaycode.getStatus() + ", " + detailsPaycode.getStatusReason());
	}

}
