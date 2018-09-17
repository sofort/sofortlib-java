package com.sofort.lib.paycode.products.response;

import com.sofort.lib.core.products.response.SofortLibResponse;
import com.sofort.lib.paycode.products.response.parts.PaycodeTransactionDetails;

import java.util.List;


/**
 * The paycode transaction details container.
 */
public class PaycodeTransactionDetailsResponse extends SofortLibResponse {

    /**
     * The transaction details list.
     */
    private List<PaycodeTransactionDetails> transactionDetailsList;


    /**
     * Sets the transaction details paycode list.
     *
     * @param transactionDetailsList the transaction details list
     */
    public void setTransactionDetailsList(List<PaycodeTransactionDetails> transactionDetailsList) {
        this.transactionDetailsList = transactionDetailsList;
    }


    /**
     * Gets the transaction details paycode list.
     *
     * @return the transaction details paycode lists
     */
    public List<PaycodeTransactionDetails> getTransactionDetailsList() {
        return transactionDetailsList;
    }

}
