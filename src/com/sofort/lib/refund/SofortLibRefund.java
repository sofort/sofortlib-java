package com.sofort.lib.refund;

import com.sofort.lib.core.SofortLibCommon;
import com.sofort.lib.core.internal.net.ConnectionConfig;
import com.sofort.lib.core.internal.transformer.DataHandler;
import com.sofort.lib.refund.products.request.RefundRequest;
import com.sofort.lib.refund.products.response.RefundResponse;

import static com.sofort.lib.core.Logger.log;


/**
 * SofortLib implementation of SOFORT Refund.
 * <p>
 * Implements the complete communication with the current SofortLib API for
 * SOFORT Refund:
 * <p>
 * - send the refund request for all products with
 * {@link SofortLibRefund#sendRefundRequest(RefundRequest)}
 */
public class SofortLibRefund {

    /**
     * The sofort lib common implementation.
     */
    private final SofortLibCommon sofortLibCommon;


    /**
     * Instantiates a new sofort lib refund with given communication and handler
     * data.
     *
     * @param connectionConfig the connection config
     * @param dataHandler      the data handler
     */
    public SofortLibRefund(ConnectionConfig connectionConfig, DataHandler dataHandler) {
        sofortLibCommon = new SofortLibCommon(connectionConfig, dataHandler);
    }


    /**
     * Send the refund request defined for a transaction.
     *
     * @param request the request with transaction data
     * @return the refund response with all refunds
     */
    public RefundResponse sendRefundRequest(RefundRequest request) {
        log.info("Send a refund request.");
        return sofortLibCommon.sendLibRequest(request, RefundResponse.class);
    }

}
