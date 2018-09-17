package com.sofort.lib.payment.products.response;

import com.sofort.lib.core.products.response.SofortLibResponse;
import com.sofort.lib.payment.products.response.parts.PaymentTransactionDetails;

import java.util.List;


/**
 * The renderer for {@link PaymentTransactionDetails}.
 */
public class PaymentTransactionDetailsResponse extends SofortLibResponse {

    /**
     * The transaction details list.
     */
    private final List<PaymentTransactionDetails> transactionDetailsList;


    /**
     * Instantiates a new transaction details payment response.
     *
     * @param transactionDetailsList the transaction details list
     */
    public PaymentTransactionDetailsResponse(List<PaymentTransactionDetails> transactionDetailsList) {
        this.transactionDetailsList = transactionDetailsList;
    }


    /**
     * Gets the transactions.
     *
     * @return the transactions
     */
    public List<PaymentTransactionDetails> getTransactions() {
        return transactionDetailsList;
    }

}
