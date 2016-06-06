package examples.billcode;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import com.sofort.lib.billcode.DefaultSofortLibBillcode;
import com.sofort.lib.billcode.products.request.BillcodeRequest;
import com.sofort.lib.billcode.products.request.BillcodeStatusRequest;
import com.sofort.lib.billcode.products.request.BillcodeTransactionDetailsRequest;
import com.sofort.lib.billcode.products.response.BillcodeResponse;
import com.sofort.lib.billcode.products.response.BillcodeStatusResponse;
import com.sofort.lib.billcode.products.response.BillcodeTransactionDetailsResponse;
import com.sofort.lib.billcode.products.response.parts.BillcodeStatus;
import com.sofort.lib.billcode.products.response.parts.BillcodeTransactionDetails;
import com.sofort.lib.core.internal.net.ConnectionException;
import com.sofort.lib.core.internal.net.http.HttpAuthorizationException;
import com.sofort.lib.core.internal.net.http.HttpConnectionException;
import com.sofort.lib.core.internal.transformer.RawResponse;
import com.sofort.lib.core.internal.transformer.RawResponse.Status;
import com.sofort.lib.core.internal.utils.StringUtilities;
import com.sofort.lib.core.products.request.parts.Notification;
import com.sofort.lib.core.products.response.SofortTransactionStatusNotification;


/**
 * An example of usage of the SofortLib Billcode.
 * 
 * 1st - use BillcodeInitaliser to initialise a new billcode
 * 
 * 2nd - start/resume/check listening on notificationUrls (see the SOFORT API
 * documentation)
 * 
 * 3rd - use the NotificationParser to parse and handle received status
 * notifications
 * 
 * 4th - get the billcode status
 * 
 * 5th - use TransactionDetailsService to retrieve transaction details
 */
public class BillcodeExampleOfUsage {

	private static final ResourceBundle CORE_BUNDLE = ResourceBundle.getBundle("core");
	private static final int customerId = Integer.parseInt(CORE_BUNDLE.getString("customerId"));
	private static final int projectId = Integer.parseInt(CORE_BUNDLE.getString("projectId"));
	private static final String apiKey = CORE_BUNDLE.getString("apiKey");

	private static final ResourceBundle PAYCODE_BUNDLE = ResourceBundle.getBundle("billcode");
	private static final String notificationURL = PAYCODE_BUNDLE.getString("notificationURL");

	public static class BillcodeInitialiser {

		/**
		 * Initialize a sofort billcode, check for errors and warnings and use
		 * the received billcode url for redirection of the customer.
		 * 
		 * @return the billcode URL the customer have to be redirected to
		 */
		public String initialiseSofortBillcode(Date startDate, Date endDate, double amount, String currency, String... purposes) {

			BillcodeRequest billcodeRequest = new BillcodeRequest(projectId, amount, Arrays.asList(purposes))
					.setCurrencyCode(currency)
					.setStartDate(endDate)
					.setEndDate(endDate)
					.setNotificationUrls(Arrays.asList(new Notification(notificationURL)));

			// initialise a billcode.
			BillcodeResponse billcodeResponse = doRequest(billcodeRequest);

			// handle the transaction ID.
			handleBillcode(billcodeResponse);

			// the most important part of the successful initialisation is the
			// redirection URL.
			return billcodeResponse.getBillcodeUrl();
		}


		private BillcodeResponse doRequest(BillcodeRequest billcodeRequest) {

			BillcodeResponse billcodeResponse;

			try {
				billcodeResponse = new DefaultSofortLibBillcode(customerId, apiKey).sendBillcodeRequest(billcodeRequest);

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
			if (billcodeResponse.hasResponseErrors()) {
				throw new IllegalStateException("Initialization errors: " + new StringUtilities().glue(billcodeResponse.getResponseErrors(), " /// "));
			}
			if (billcodeResponse.hasResponseWarnings()) {
				System.err.println("Initialisation warnings: " + new StringUtilities().glue(billcodeResponse.getResponseWarnings(), " /// "));
			}

			// check and handle the response warnings
			if (billcodeResponse.hasWarnings()) {
				System.err.println("Initialisation of a new billcode warnings: " + new StringUtilities().glue(billcodeResponse.getWarnings(), " /// "));
			}

			return billcodeResponse;
		}


		private void handleBillcode(BillcodeResponse billcodeResponse) {
			// handle initialised billcode, i.e. store transId into DB
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

			SofortTransactionStatusNotification statusNotification = new DefaultSofortLibBillcode(customerId, apiKey).parseStatusNotificationResponse(new RawResponse(status, statusNotificationRaw));

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

	public static class BillcodeStatusService {

		/**
		 * Check the status of a billcode
		 * 
		 * @return the billcode status
		 */
		public BillcodeStatus checkBillcodeStatus(String billcode) {

			BillcodeStatusRequest billcodeStatusRequest = new BillcodeStatusRequest(billcode);

			// check the billcode status
			BillcodeStatusResponse billcodeStatusResponse = doRequest(billcodeStatusRequest);

			// handle the billcode status.
			handleBillcodeStatus(billcodeStatusResponse);

			// return the billcode status
			return billcodeStatusResponse.getStatus();
		}


		private BillcodeStatusResponse doRequest(BillcodeStatusRequest billcodeStatusRequest) {

			BillcodeStatusResponse billcodeStatusResponse;

			try {
				billcodeStatusResponse = new DefaultSofortLibBillcode(customerId, apiKey).sendBillcodeStatusRequest(billcodeStatusRequest);

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
			if (billcodeStatusResponse.hasResponseErrors()) {
				throw new IllegalStateException("Initialization errors: " + new StringUtilities().glue(billcodeStatusResponse.getResponseErrors(), " /// "));
			}
			if (billcodeStatusResponse.hasResponseWarnings()) {
				System.err.println("Initialisation warnings: " + new StringUtilities().glue(billcodeStatusResponse.getResponseWarnings(), " /// "));
			}

			return billcodeStatusResponse;
		}


		private void handleBillcodeStatus(BillcodeStatusResponse billcodeResponse) {
			// handle the billcode status, i.e. store the time it was used
		}

	}

	public static class TransactionDetailsService {

		/**
		 * Get transaction details for a billcode transaction.
		 * 
		 * @param transIds
		 *            - transaction ids
		 * @return list of billcode transaction details
		 */
		public List<BillcodeTransactionDetails> getDetails(String... transIds) {

			BillcodeTransactionDetailsRequest transactionRequest = new BillcodeTransactionDetailsRequest()
					.setTransIds(Arrays.asList(transIds));

			return doRequest(transactionRequest).getTransactionDetailsList();
		}


		/**
		 * Get billcode transaction details for a time span
		 * 
		 * @param from
		 *            start date
		 * @param to
		 *            end date
		 * 
		 * @return list of billcode transaction details
		 */
		public List<BillcodeTransactionDetails> getDetails(Date from, Date to) {

			BillcodeTransactionDetailsRequest transactionRequest = new BillcodeTransactionDetailsRequest()
					.setFromTime(from)
					.setToTime(to);

			return doRequest(transactionRequest).getTransactionDetailsList();
		}


		private BillcodeTransactionDetailsResponse doRequest(BillcodeTransactionDetailsRequest transactionRequest) {

			BillcodeTransactionDetailsResponse transactionDetailsResponse;

			try {
				transactionDetailsResponse = new DefaultSofortLibBillcode(customerId, apiKey).sendBillcodeTransactionDetailsRequest(transactionRequest);
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
