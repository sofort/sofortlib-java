package examples.paycode;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import com.sofort.lib.core.internal.net.ConnectionException;
import com.sofort.lib.core.internal.net.http.HttpAuthorizationException;
import com.sofort.lib.core.internal.net.http.HttpConnectionException;
import com.sofort.lib.core.internal.transformer.RawResponse;
import com.sofort.lib.core.internal.transformer.RawResponse.Status;
import com.sofort.lib.core.internal.utils.StringUtilities;
import com.sofort.lib.core.products.request.parts.Notification;
import com.sofort.lib.core.products.response.SofortTransactionStatusNotification;
import com.sofort.lib.paycode.DefaultSofortLibPaycode;
import com.sofort.lib.paycode.products.request.PaycodeRequest;
import com.sofort.lib.paycode.products.request.PaycodeStatusRequest;
import com.sofort.lib.paycode.products.request.PaycodeTransactionDetailsRequest;
import com.sofort.lib.paycode.products.response.PaycodeResponse;
import com.sofort.lib.paycode.products.response.PaycodeStatusResponse;
import com.sofort.lib.paycode.products.response.PaycodeTransactionDetailsResponse;
import com.sofort.lib.paycode.products.response.parts.PaycodeStatus;
import com.sofort.lib.paycode.products.response.parts.PaycodeTransactionDetails;


/**
 * An example of usage of the SofortLib Paycode.
 * 
 * 1st - use PaycodeInitaliser to initialise a new paycode
 * 
 * 2nd - start/resume/check listening on notificationUrls (see the SOFORT API
 * documentation)
 * 
 * 3rd - use the NotificationParser to parse and handle received status
 * notifications
 * 
 * 4th - get the paycode status
 * 
 * 5th - use TransactionDetailsService to retrieve transaction details
 */
public class PaycodeExampleOfUsage {

	private static final ResourceBundle CORE_BUNDLE = ResourceBundle.getBundle("core");
	private static final int customerId = Integer.parseInt(CORE_BUNDLE.getString("customerId"));
	private static final int projectId = Integer.parseInt(CORE_BUNDLE.getString("projectId"));
	private static final String apiKey = CORE_BUNDLE.getString("apiKey");

	private static final ResourceBundle PAYCODE_BUNDLE = ResourceBundle.getBundle("paycode");
	private static final String notificationURL = PAYCODE_BUNDLE.getString("notificationURL");
	private static final String successURL = PAYCODE_BUNDLE.getString("successURL");
	private static final String abortURL = PAYCODE_BUNDLE.getString("abortURL");

	public static class PaycodeInitialiser {

		/**
		 * Start a sofort paycode, check for errors and warnings and use the
		 * received paycode url for redirection of the customer.
		 * 
		 * @return the paycode URL the customer have to be redirected to
		 */
		public String initialiseSofortPaycode(Date startDate, Date endDate, double amount, String currency, String... purposes) {

			PaycodeRequest paycodeRequest = new PaycodeRequest(projectId, amount, Arrays.asList(purposes))
					.setCurrencyCode(currency)
					.setStartDate(endDate)
					.setEndDate(endDate)
					.setNotificationUrls(Arrays.asList(new Notification(notificationURL)))
					.setSuccessUrl(successURL)
					.setAbortUrl(abortURL);

			// initialise a paycode.
			PaycodeResponse paycodeResponse = doRequest(paycodeRequest);

			// handle the transaction ID.
			handlePaycode(paycodeResponse);

			// the most important part of the successful initialisation is the
			// redirection URL.
			return paycodeResponse.getPaycodeUrl();
		}


		private PaycodeResponse doRequest(PaycodeRequest paycodeRequest) {

			PaycodeResponse paycodeResponse;

			try {
				paycodeResponse = new DefaultSofortLibPaycode(customerId, apiKey).sendPaycodeRequest(paycodeRequest);

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
			if (paycodeResponse.hasResponseErrors()) {
				throw new IllegalStateException("Initialization errors: " + new StringUtilities().glue(paycodeResponse.getResponseErrors(), " /// "));
			}
			if (paycodeResponse.hasResponseWarnings()) {
				System.err.println("Initialisation warnings: " + new StringUtilities().glue(paycodeResponse.getResponseWarnings(), " /// "));
			}

			// check and handle the paycode warnings
			if (paycodeResponse.hasWarnings()) {
				System.err.println("Initialisation of a new paycode warnings: " + new StringUtilities().glue(paycodeResponse.getWarnings(), " /// "));
			}

			return paycodeResponse;
		}


		private void handlePaycode(PaycodeResponse paycodeResponse) {
			// handle initialised paycode, i.e. store transId into DB
		}

	}

	public static class NotificationParser {

		/**
		 * Parse the received transaction changes notification
		 * 
		 * @param statusNotification
		 *            received status notification
		 * @param status
		 * @return
		 */
		public SofortTransactionStatusNotification parseManualReceivedStatusNotification(Status status, String statusNotificationRaw) {

			SofortTransactionStatusNotification statusNotification = new DefaultSofortLibPaycode(customerId, apiKey).parseStatusNotificationResponse(new RawResponse(status, statusNotificationRaw));

			// check and handle the response errors and warnings
			if (statusNotification.hasResponseErrors()) {
				throw new IllegalStateException("Parsed status notification contains errors. Errors: " + new StringUtilities().glue(statusNotification.getResponseErrors(), " /// "));
			}
			if (statusNotification.hasResponseWarnings()) {
				System.err.println("Parsed status notification contains warnings. Warnings: " + new StringUtilities().glue(statusNotification.getResponseWarnings(), " /// "));
			}

			return statusNotification;
		}

	}

	public static class PaycodeStatusService {

		/**
		 * Check the status of a paycode
		 * 
		 * @return the paycode status
		 */
		public PaycodeStatus checkPaycodeStatus(String paycode) {

			PaycodeStatusRequest paycodeStatusRequest = new PaycodeStatusRequest(paycode);

			// check the paycode status
			PaycodeStatusResponse paycodeStatusResponse = doRequest(paycodeStatusRequest);

			// handle the paycode status.
			handlePaycodeStatus(paycodeStatusResponse);

			// return the paycode status
			return paycodeStatusResponse.getStatus();
		}


		private PaycodeStatusResponse doRequest(PaycodeStatusRequest paycodeStatusRequest) {

			PaycodeStatusResponse paycodeStatusResponse;

			try {
				paycodeStatusResponse = new DefaultSofortLibPaycode(customerId, apiKey).sendPaycodeStatusRequest(paycodeStatusRequest);

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
			if (paycodeStatusResponse.hasResponseErrors()) {
				throw new IllegalStateException("Initialization errors: " + new StringUtilities().glue(paycodeStatusResponse.getResponseErrors(), " /// "));
			}
			if (paycodeStatusResponse.hasResponseWarnings()) {
				System.err.println("Initialisation warnings: " + new StringUtilities().glue(paycodeStatusResponse.getResponseWarnings(), " /// "));
			}

			return paycodeStatusResponse;
		}


		private void handlePaycodeStatus(PaycodeStatusResponse paycodeResponse) {
			// handle the paycode status, i.e. store the time it was used
		}

	}

	public static class TransactionDetailsService {

		/**
		 * Get transaction details for a paycode transaction.
		 * 
		 * @param transIds
		 *            - transaction ids
		 * @return list of paycode transaction details
		 */
		public List<PaycodeTransactionDetails> getDetails(String... transIds) {

			PaycodeTransactionDetailsRequest transactionRequest = new PaycodeTransactionDetailsRequest()
					.setTransIds(Arrays.asList(transIds));

			return doRequest(transactionRequest).getTransactionDetailsList();
		}


		/**
		 * Get paycode transaction details for a time span
		 * 
		 * @param from
		 *            start date
		 * @param to
		 *            end date
		 * 
		 * @return list of paycode transaction details
		 */
		public List<PaycodeTransactionDetails> getDetails(Date from, Date to) {

			PaycodeTransactionDetailsRequest transactionRequest = new PaycodeTransactionDetailsRequest()
					.setFromTime(from)
					.setToTime(to);

			return doRequest(transactionRequest).getTransactionDetailsList();
		}


		private PaycodeTransactionDetailsResponse doRequest(PaycodeTransactionDetailsRequest transactionRequest) {

			PaycodeTransactionDetailsResponse transactionDetailsResponse;

			try {
				transactionDetailsResponse = new DefaultSofortLibPaycode(customerId, apiKey).sendPaycodeTransactionDetailsRequest(transactionRequest);
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

			// check and handle the response errors
			if (transactionDetailsResponse.hasResponseErrors()) {
				throw new IllegalStateException("Initialization errors: " + new StringUtilities().glue(transactionDetailsResponse.getResponseErrors(), " /// "));
			}
			if (transactionDetailsResponse.hasResponseWarnings()) {
				System.err.println("Initialisation warnings: " + new StringUtilities().glue(transactionDetailsResponse.getResponseWarnings(), " /// "));
			}

			return transactionDetailsResponse;
		}

	}

}
