package examples.billcode;

import java.util.Arrays;

import com.sofort.lib.billcode.DefaultSofortLibBillcode;
import com.sofort.lib.billcode.SofortLibBillcode;
import com.sofort.lib.billcode.products.request.BillcodeRequest;
import com.sofort.lib.billcode.products.request.BillcodeStatusRequest;
import com.sofort.lib.billcode.products.request.BillcodeTransactionDetailsRequest;
import com.sofort.lib.billcode.products.response.BillcodeResponse;
import com.sofort.lib.billcode.products.response.BillcodeStatusResponse;
import com.sofort.lib.billcode.products.response.BillcodeTransactionDetailsResponse;
import com.sofort.lib.billcode.products.response.parts.BillcodeTransactionDetails;
import com.sofort.lib.core.internal.net.ConnectionException;
import com.sofort.lib.core.internal.net.http.HttpAuthorizationException;
import com.sofort.lib.core.internal.net.http.HttpConnectionException;
import com.sofort.lib.core.internal.transformer.RawResponse;
import com.sofort.lib.core.internal.transformer.RawResponse.Status;
import com.sofort.lib.core.products.request.parts.Notification;
import com.sofort.lib.core.products.response.SofortTransactionStatusNotification;


/**
 * An example of usage of the SofortLib Payment (SOFORT Überweisung).
 */
public class BillcodeExampleOfUsage {

	public static void main(String[] args) {

		/* initialize the default sofort lib billcode */
		final int customerId = 4711;
		final int projectId = 8150;
		final String apiKey = "API-KEY";

		final SofortLibBillcode sofortLibBillcode = new DefaultSofortLibBillcode(customerId, apiKey);

		/*
		 * 1st step -> start sofort billcode, check for errors and warnings and
		 * use the received billcode url for redirection of the buyer
		 */
		final String statusNotificationUrl = "https://my.shop/sofort/billcodeStatusNotification";

		BillcodeRequest billcodeRequest = new BillcodeRequest(projectId, 19.99, Arrays.asList("Customer 47110815", "Bill 08154711"));

		billcodeRequest.setNotificationUrls(Arrays.asList(new Notification(statusNotificationUrl)));

		BillcodeResponse billcodeResponse;
		try {
			billcodeResponse = sofortLibBillcode.sendBillcodeRequest(billcodeRequest);
		} catch (HttpConnectionException e) {

			if (e.getResponseCode() == 401) {
				System.err.println("The authorization with the given apiKey has been failed.");
			}

			throw e;
		}

		if (billcodeResponse.hasResponseErrors() || billcodeResponse.hasResponseWarnings()) {
			// check and handle the response errors and warnings
		}

		if (billcodeResponse.hasWarnings()) {
			// check the warnings
		}

		// start/resume/check listening on notificationUrls

		// Store or handle transId.
		System.out.println(billcodeResponse.getBillcode());
		// Redirect customer to billcode URL.
		System.out.println(billcodeResponse.getBillcodeUrl());

		/*
		 * 2nd step -> parse the received transaction changes notification
		 */
		String statusNotification = /* received status notification */"";
		SofortTransactionStatusNotification statusNotificationResponse = sofortLibBillcode.parseStatusNotificationResponse(new RawResponse(Status.OK, statusNotification));
		String statusNotificationTransId = statusNotificationResponse.getTransId();

		// handle notification responses
		System.out.println(statusNotificationTransId);

		/*
		 * 3rd step -> get last known billcode status
		 */
		BillcodeStatusRequest statusRequest = new BillcodeStatusRequest(billcodeResponse.getBillcode());
		BillcodeStatusResponse statusResponse;
		try {
			statusResponse = sofortLibBillcode.sendBillcodeStatusRequest(statusRequest);
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

		// handle current status
		System.out.println("Last known billcode status: " + statusResponse.getStatus() + ", Used on: " + statusResponse.getTimeUsed());

		/*
		 * 4th step -> get transaction details for notified transaction
		 */
		BillcodeTransactionDetailsRequest transactionRequest = new BillcodeTransactionDetailsRequest()
				.setTransIds(Arrays.asList(statusNotificationTransId));
		BillcodeTransactionDetailsResponse transactionDetailsResponse;
		try {
			transactionDetailsResponse = sofortLibBillcode.sendBillcodeTransactionDetailsRequest(transactionRequest);
		} catch (HttpConnectionException e) {

			if (e.getResponseCode() == 401) {
				System.err.println("The authorization with the given apiKey has been failed.");
			}

			throw e;
		}
		BillcodeTransactionDetails detailsBillcode = transactionDetailsResponse.getTransactionDetailsList().get(0);

		// handle current status
		System.out.println(detailsBillcode.getStatus() + " " + detailsBillcode.getStatusReason());
	}

}
