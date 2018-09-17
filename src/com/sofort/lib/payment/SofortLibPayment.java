package com.sofort.lib.payment;

import com.sofort.lib.core.SofortLibCommon;
import com.sofort.lib.core.internal.net.ConnectionConfig;
import com.sofort.lib.core.internal.transformer.DataHandler;
import com.sofort.lib.core.internal.transformer.RawResponse;
import com.sofort.lib.core.products.response.SofortTransactionStatusNotification;
import com.sofort.lib.payment.products.request.PaymentRequest;
import com.sofort.lib.payment.products.request.PaymentTransactionDetailsRequest;
import com.sofort.lib.payment.products.response.PaymentResponse;
import com.sofort.lib.payment.products.response.PaymentTransactionDetailsResponse;

import static com.sofort.lib.core.Logger.log;


/**
 * SofortLib implementation of SOFORT Payment (SOFORT Überweisung).
 * <p>
 * Implements the complete communication with the current SofortLib API for
 * SOFORT Payment and tools for parsing of manually received notification
 * responses:
 * <p>
 * - initiate a SOFORT Payment with
 * {@link SofortLibPayment#sendPaymentRequest(PaymentRequest)}
 * <p>
 * - parse the received status notification to
 * {@link SofortTransactionStatusNotification} with
 * {@link SofortLibPayment#parseStatusNotificationResponse(RawResponse)}
 * <p>
 * - get the payment transaction details with
 * {@link SofortLibPayment#sendTransactionDetailsRequest(PaymentTransactionDetailsRequest)}
 */
public class SofortLibPayment {


    /**
     * The sofort lib common implementation.
     */
    private final SofortLibCommon sofortLibCommon;


    /**
     * Instantiates a new sofort lib payment with the given communication and
     * handler data.
     *
     * @param connectionConfig the connection config
     * @param dataHandler      the data handler
     */
    public SofortLibPayment(ConnectionConfig connectionConfig, DataHandler dataHandler) {
        sofortLibCommon = new SofortLibCommon(connectionConfig, dataHandler);
    }


    /**
     * Initiates a SOFORT Payment for getting the transaction ID and redirection
     * payment URL for buyer.
     *
     * @param request the filled payment request
     * @return the sofort payment response with redirection URL and the
     * transaction ID
     */
    public PaymentResponse sendPaymentRequest(PaymentRequest request) {
        log.info("Send a SOFORT payment request.");
        return sofortLibCommon.sendLibRequest(request, PaymentResponse.class);
    }


    /**
     * Parses the received status notification response to a
     * {@link SofortTransactionStatusNotification}.
     *
     * @param rawResponse the raw response with the status notification as content
     * @return the sofort transaction status notification
     */
    public SofortTransactionStatusNotification parseStatusNotificationResponse(RawResponse rawResponse) {
        log.info("Parse the raw status notification response to the data response.");
        return sofortLibCommon.parseStatusNotificationResponse(rawResponse);
    }


    /**
     * Gets the transaction details for either the transaction IDs or the
     * transaction time/status search parameters.
     *
     * @param request the request with transaction search parameters
     * @return the transaction details payment response
     */
    public PaymentTransactionDetailsResponse sendTransactionDetailsRequest(PaymentTransactionDetailsRequest request) {
        log.info("Send a SOFORT transaction details request.");
        return sofortLibCommon.sendLibRequest(request, PaymentTransactionDetailsResponse.class);
    }

}
