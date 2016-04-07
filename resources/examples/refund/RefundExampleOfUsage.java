package examples.refund;

import java.util.Arrays;

import com.sofort.lib.core.internal.net.ConnectionException;
import com.sofort.lib.core.internal.net.http.HttpAuthorizationException;
import com.sofort.lib.core.internal.net.http.HttpConnectionException;
import com.sofort.lib.core.products.response.parts.FailureMessage;
import com.sofort.lib.refund.DefaultSofortLibRefund;
import com.sofort.lib.refund.SofortLibRefund;
import com.sofort.lib.refund.products.RefundBankAccount;
import com.sofort.lib.refund.products.request.RefundRequest;
import com.sofort.lib.refund.products.response.RefundResponse;


/**
 * An example of usage of the SofortLib REfund (SOFORT Rückbuchnugen).
 */
public class RefundExampleOfUsage {

	public static void main(String[] args) {

		/* initialize the default sofort lib refund */
		final int customerId = 4711;
		final String apiKey = "API-KEY-123";

		final SofortLibRefund sofortLibRefund = new DefaultSofortLibRefund(customerId, apiKey);

		/* refund a sofort payment */
		final String transIdToRefund = "1111-22222-33333-4444";

		RefundRequest request = new RefundRequest(
				Arrays.asList(
						new com.sofort.lib.refund.products.request.parts.Refund(transIdToRefund, 47.11)
								.setComment("Retoure 24.12.2012")))
				.setSender(new RefundBankAccount()
						.setHolder("Mr. Jones")
						.setBankName("ZZ Bank")
						.setIban("ZZ771212121212121212")
						.setBic("ZZZ21341234"));

		RefundResponse response;
		try {
			response = sofortLibRefund.sendRefundRequest(request);
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

		// check for response error first
		if (response.hasErrors()) {
			for (FailureMessage error : response.getErrors()) {
				// handle response errors
				System.err.println(error.getCode() + " " + error.getMessage());
			}
		}

		// handle response
		System.out.println(response.getPain());

		for (com.sofort.lib.refund.products.response.parts.Refund refund : response.getRefunds()) {

			// check for refund error first
			if (refund.hasErrors()) {
				for (FailureMessage error : refund.getErrors()) {
					// handle refund errors
					System.err.println(error.getCode() + " " + error.getMessage());
				}
			}

			// handle refund
			System.out.println(refund.getStatus());
			System.out.println(refund.getComment());
		}
	}
}
