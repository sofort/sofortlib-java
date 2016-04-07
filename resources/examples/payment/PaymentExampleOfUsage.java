package examples.payment;

import java.util.Arrays;
import java.util.Date;
import java.util.ResourceBundle;

import com.sofort.lib.core.internal.net.ConnectionException;
import com.sofort.lib.core.internal.net.http.HttpAuthorizationException;
import com.sofort.lib.core.internal.net.http.HttpConnectionException;
import com.sofort.lib.core.internal.transformer.RawResponse;
import com.sofort.lib.core.internal.transformer.RawResponse.Status;
import com.sofort.lib.core.products.request.parts.Notification;
import com.sofort.lib.core.products.response.SofortTransactionStatusNotification;
import com.sofort.lib.payment.DefaultSofortLibPayment;
import com.sofort.lib.payment.products.request.PaymentRequest;
import com.sofort.lib.payment.products.request.PaymentTransactionDetailsRequest;
import com.sofort.lib.payment.products.response.PaymentResponse;
import com.sofort.lib.payment.products.response.PaymentTransactionDetailsResponse;


/**
 * An example of usage of the SofortLib Payment (SOFORT Überweisung).
 */
public class PaymentExampleOfUsage {

	// change the config.properties
	private static final ResourceBundle CONFIG_BUNDLE = ResourceBundle.getBundle("config");

	private static final int customerId = Integer.parseInt(CONFIG_BUNDLE.getString("customerId"));
	private static final int projectId = Integer.parseInt(CONFIG_BUNDLE.getString("projectId"));
	private static final String apiKey = CONFIG_BUNDLE.getString("apiKey");
	private static final String notificationURL = CONFIG_BUNDLE.getString("notificationURL");
	private static final String successURL = CONFIG_BUNDLE.getString("successURL");
	private static final String abortURL = CONFIG_BUNDLE.getString("abortURL");
	private static final String timeoutURL = CONFIG_BUNDLE.getString("timeoutURL");

	public static class PaymentInitaliser {

		/**
		 * Start a sofort payment, check for errors and warnings and use the
		 * received payment url for redirection of the customer.
		 * 
		 * @return the payment URL the customer have to be redirected to
		 */
		public String initializeSofortPayment() {
			PaymentRequest paymentRequest = new PaymentRequest(projectId, 19.99, "EUR", Arrays.asList("Customer 47110815", "Bill 08154711"), false)
					.setNotificationUrls(Arrays.asList(new Notification(notificationURL)))
					.setSuccessUrl(successURL)
					.setAbortUrl(abortURL)
					.setTimeoutUrl(timeoutURL);

			PaymentResponse paymentResponse;
			try {
				paymentResponse = new DefaultSofortLibPayment(customerId, apiKey).sendPaymentRequest(paymentRequest);
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

			if (paymentResponse.hasResponsePaymentErrors()) {
				// check and handle the response errors and warnings
				throw new IllegalStateException("Could not initialize a sofort payment. Errors: " + paymentResponse.getResponseErrors());
			}

			if (paymentResponse.hasResponseWarnings()) {
				System.err.println("Initialisation warnings: " + paymentResponse.getResponseWarnings());
				// check and handle the response errors and warnings
			}

			if (paymentResponse.hasResponseErrors()) {
				// check and handle the response errors and warnings
				throw new IllegalStateException("Initialization errors: " + paymentResponse.getResponseErrors());
			}

			if (paymentResponse.hasNewPaymentWarnings()) {
				System.err.println("Initialisation of a new payment warnings: " + paymentResponse.getNewPaymentWarnings());
				// check the new payment warnings
			}

			// Store and handle the transaction ID.
			handleTransId(paymentResponse);

			// the most important part of the successful initialisation is the
			// redirection URL.
			return paymentResponse.getPaymentUrl();
		}


		private static void handleTransId(PaymentResponse paymentResponse) {
			// your implementation here
			System.out.println(paymentResponse.getTransId());
		}

	}

	public static class NotificationParser {

		/**
		 * 2nd step -> parse the received transaction changes notification
		 * 
		 * @param statusNotification
		 *            received status notification
		 * @param status
		 * @return
		 */
		public SofortTransactionStatusNotification parseManualReceivedStatusNotification(Status status, String statusNotification) {
			SofortTransactionStatusNotification statusNotificationResponse = new DefaultSofortLibPayment(customerId, apiKey).parseStatusNotificationResponse(new RawResponse(status, statusNotification));
			String statusNotificationTransId = statusNotificationResponse.getTransId();

			if (statusNotificationResponse.hasResponseWarnings()) {
				// check and handle the response errors and warnings
			}

			if (statusNotificationResponse.hasResponseErrors()) {
				// check and handle the response errors and warnings
				throw new IllegalStateException("Parsed status notification contains errors. Errors: " + statusNotificationResponse.getResponseErrors());
			}

			// handle notification responses
			handleStatusNotification(statusNotificationTransId);

			return statusNotificationResponse;
		}


		private static void handleStatusNotification(String statusNotificationTransId) {
			// your implementation here
			System.out.println(statusNotificationTransId);
		}
	}

	public static class TransactionDetailsService {

		/**
		 * Get transaction details for a transaction.
		 */
		public void getDetails(String transId) {
			PaymentTransactionDetailsRequest transactionRequest = new PaymentTransactionDetailsRequest()
					.setTransIds(Arrays.asList(transId));

			PaymentTransactionDetailsResponse transactionDetailsResponse = getDetails(transactionRequest);
			handleTransactionDetails(transactionDetailsResponse);
		}


		/**
		 * Get transaction details for a time span
		 * 
		 * @param from
		 * @param to
		 */
		public void getDetails(Date from, Date to) {
			PaymentTransactionDetailsRequest transactionRequest = new PaymentTransactionDetailsRequest()
					.setFromTime(from)
					.setToTime(to);

			PaymentTransactionDetailsResponse transactionDetailsResponse = getDetails(transactionRequest);
			handleTransactionDetails(transactionDetailsResponse);
		}


		private static PaymentTransactionDetailsResponse getDetails(PaymentTransactionDetailsRequest transactionRequest) {
			PaymentTransactionDetailsResponse transactionDetailsResponse;
			try {
				transactionDetailsResponse = new DefaultSofortLibPayment(customerId, apiKey).sendTransactionDetailsRequest(transactionRequest);
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
			return transactionDetailsResponse;
		}


		private static void handleTransactionDetails(PaymentTransactionDetailsResponse transactionDetailsResponse) {
			// your implementation here
			System.out.println(transactionDetailsResponse.getTransactions().get(0).getStatus() + " " +
					transactionDetailsResponse.getTransactions().get(0).getStatusReason());
		}

	}


	public static void main(String[] args) {

		// use PaymentInitalizationService to initialise a new payment

		// start/resume/check listening on notificationUrls (see API
		// documentation)

		// Use the NotificationParser to parse and handle received status
		// notifications

		// use TransactionDetailsService to receive the transaction details
	}

}
