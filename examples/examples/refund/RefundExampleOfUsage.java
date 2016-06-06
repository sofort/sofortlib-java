package examples.refund;

import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import com.sofort.lib.core.internal.net.ConnectionException;
import com.sofort.lib.core.internal.net.http.HttpAuthorizationException;
import com.sofort.lib.core.internal.net.http.HttpConnectionException;
import com.sofort.lib.core.internal.utils.StringUtilities;
import com.sofort.lib.refund.DefaultSofortLibRefund;
import com.sofort.lib.refund.products.RefundBankAccount;
import com.sofort.lib.refund.products.request.RefundRequest;
import com.sofort.lib.refund.products.request.parts.Refund;
import com.sofort.lib.refund.products.response.RefundResponse;


/**
 * An example of usage of the SofortLib Refund.
 */
public class RefundExampleOfUsage {

	private static final ResourceBundle CORE_BUNDLE = ResourceBundle.getBundle("core");
	private static final int customerId = Integer.parseInt(CORE_BUNDLE.getString("customerId"));
	private static final String apiKey = CORE_BUNDLE.getString("apiKey");

	public static class RefundInitialiser {

		/**
		 * Send sofort refund(s), check for errors and warnings and use the
		 * received refund url for redirection of the customer.
		 * 
		 * @return Refunds with execution statuses
		 */
		public List<com.sofort.lib.refund.products.response.parts.Refund> sendRefunds(String holder, String bankName, String iban, String bic, Refund... refunds) {

			RefundRequest refundRequest = new RefundRequest(Arrays.asList(refunds))
					.setSender(new RefundBankAccount()
							.setHolder(holder)
							.setBankName(bankName)
							.setIban(iban)
							.setBic(bic));

			// initialise a refund.
			RefundResponse refundResponse = doRequest(refundRequest);

			// handle the refund.
			handleRefunds(refundResponse);

			// the most important part of the successful initialisation is the
			// redirection URL.
			return refundResponse.getRefunds();
		}


		private RefundResponse doRequest(RefundRequest refundRequest) {

			RefundResponse refundResponse;

			try {
				refundResponse = new DefaultSofortLibRefund(customerId, apiKey).sendRefundRequest(refundRequest);

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
			if (refundResponse.hasResponseErrors()) {
				throw new IllegalStateException("Initialization errors: " + new StringUtilities().glue(refundResponse.getResponseErrors(), " /// "));
			}
			if (refundResponse.hasResponseWarnings()) {
				System.err.println("Initialisation warnings: " + new StringUtilities().glue(refundResponse.getResponseWarnings(), " /// "));
			}

			// check and handle the refund errors
			if (refundResponse.hasErrors()) {
				throw new IllegalStateException("Could not initialize a sofort refund. Errors: " + new StringUtilities().glue(refundResponse.getErrors(), " /// "));
			}

			return refundResponse;
		}


		private void handleRefunds(RefundResponse refundResponse) {
			// handle refund
		}
	}

}
