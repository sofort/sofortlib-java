package examples.payment;

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
import com.sofort.lib.payment.DefaultSofortLibPayment;
import com.sofort.lib.payment.products.request.PaymentRequest;
import com.sofort.lib.payment.products.request.PaymentTransactionDetailsRequest;
import com.sofort.lib.payment.products.request.SofortPayment;
import com.sofort.lib.payment.products.response.PaymentResponse;
import com.sofort.lib.payment.products.response.PaymentTransactionDetailsResponse;
import com.sofort.lib.payment.products.response.parts.PaymentTransactionDetails;


/**
 * An example of usage of the SofortLib Payment (SOFORT Überweisung).
 * 
 * 1st - use PaymentInitaliser to initialise a new payment
 * 
 * 2nd - start/resume/check listening on notificationUrls (see the SOFORT API
 * documentation)
 * 
 * 3rd - use the NotificationParser to parse and handle received status
 * notifications
 * 
 * 4th - use TransactionDetailsService to retrieve transaction details
 */
public class PaymentExampleOfUsage {

	private static final ResourceBundle CORE_BUNDLE = ResourceBundle.getBundle("core");
	private static final int customerId = Integer.parseInt(CORE_BUNDLE.getString("customerId"));
	private static final int projectId = Integer.parseInt(CORE_BUNDLE.getString("projectId"));
	private static final String apiKey = CORE_BUNDLE.getString("apiKey");

	private static final ResourceBundle PAYMENT_BUNDLE = ResourceBundle.getBundle("payment");
	private static final String notificationURL = PAYMENT_BUNDLE.getString("notificationURL");
	private static final String successURL = PAYMENT_BUNDLE.getString("successURL");
	private static final String abortURL = PAYMENT_BUNDLE.getString("abortURL");
	private static final String timeoutURL = PAYMENT_BUNDLE.getString("timeoutURL");

	public static class PaymentInitialiser {

		/**
		 * Start a sofort payment, check for errors and warnings and use the
		 * received payment url for redirection of the customer.
		 * @param sofortPayment sofort payment special container
		 * 
		 * @return the payment URL the customer have to be redirected to
		 */
		public String initialiseSofortPayment(double amount, String currency, SofortPayment sofortPayment, String... purposes) {

			PaymentRequest paymentRequest = new PaymentRequest(projectId, amount, currency, Arrays.asList(purposes), sofortPayment)
					.setNotificationUrls(Arrays.asList(new Notification(notificationURL)))
					.setSuccessUrl(successURL)
					.setAbortUrl(abortURL)
					.setTimeoutUrl(timeoutURL);

			// initialise a payment.
			PaymentResponse paymentResponse = doRequest(paymentRequest);

			// handle the transaction ID.
			handlePayment(paymentResponse);

			// the most important part of the successful initialisation is the
			// redirection URL.
			return paymentResponse.getPaymentUrl();
		}


		private PaymentResponse doRequest(PaymentRequest paymentRequest) {

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

			// check and handle the response errors and warnings
			if (paymentResponse.hasResponseErrors()) {
				throw new IllegalStateException("Initialization errors: " + new StringUtilities().glue(paymentResponse.getResponseErrors(), " /// "));
			}
			if (paymentResponse.hasResponseWarnings()) {
				System.err.println("Initialisation warnings: " + new StringUtilities().glue(paymentResponse.getResponseWarnings(), " /// "));
			}

			// check and handle the payment errors and warnings
			if (paymentResponse.hasResponsePaymentErrors()) {
				throw new IllegalStateException("Could not initialize a sofort payment. Errors: " + new StringUtilities().glue(paymentResponse.getResponsePaymentErrors(), " /// "));
			}
			if (paymentResponse.hasNewPaymentWarnings()) {
				System.err.println("Initialisation of a new payment warnings: " + new StringUtilities().glue(paymentResponse.getNewPaymentWarnings(), " /// "));
			}

			return paymentResponse;
		}


		private void handlePayment(PaymentResponse paymentResponse) {
			// handle initialised payment, i.e. store transId into DB
		}

	}

	public static class NotificationParser {

		/**
		 * Parse the received transaction changes notification.
		 * 
		 * @param statusNotification
		 *            received status notification
		 * @param status
		 * @return
		 */
		public SofortTransactionStatusNotification parseManualReceivedStatusNotification(Status status, String statusNotificationRaw) {

			SofortTransactionStatusNotification statusNotification = new DefaultSofortLibPayment(customerId, apiKey).parseStatusNotificationResponse(new RawResponse(status, statusNotificationRaw));

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

	public static class TransactionDetailsService {

		/**
		 * Get transaction details for a transaction.
		 * 
		 * @param transIds
		 *            - transaction ids
		 * @return list of transaction details
		 */
		public List<PaymentTransactionDetails> getDetails(String... transIds) {

			PaymentTransactionDetailsRequest transactionRequest = new PaymentTransactionDetailsRequest()
					.setTransIds(Arrays.asList(transIds));

			return doRequest(transactionRequest).getTransactions();
		}


		/**
		 * Get transaction details for a time span.
		 * 
		 * @param from
		 *            start date
		 * @param to
		 *            end date
		 * 
		 * @return list of transaction details
		 */
		public List<PaymentTransactionDetails> getDetails(Date from, Date to) {

			PaymentTransactionDetailsRequest transactionRequest = new PaymentTransactionDetailsRequest()
					.setFromTime(from)
					.setToTime(to);

			return doRequest(transactionRequest).getTransactions();
		}


		private PaymentTransactionDetailsResponse doRequest(PaymentTransactionDetailsRequest transactionRequest) {

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
