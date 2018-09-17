package com.sofort.lib.payment.products.response;

import com.sofort.lib.core.products.response.SofortLibResponse;
import com.sofort.lib.core.products.response.parts.FailureMessage;

import java.util.List;


/**
 * The API SOFORT Payment (SOFORT Überweisung) response container.
 */
public class PaymentResponse extends SofortLibResponse {

    /**
     * The trans id.
     */
    private String transId;

    /**
     * The payment url.
     */
    private String paymentUrl;

    /**
     * The new payment warnings.
     */
    private List<FailureMessage> newPaymentWarnings;

    /**
     * The response payment specific errors.
     */
    private List<FailureMessage> responsePaymentErrors;


    /**
     * Gets the trans id.
     *
     * @return the trans id
     */
    public String getTransId() {
        return transId;
    }


    /**
     * Sets the trans id.
     *
     * @param transId the new trans id
     */
    public void setTransId(String transId) {
        this.transId = transId;
    }


    /**
     * Gets the payment url.
     *
     * @return the payment url
     */
    public String getPaymentUrl() {
        return paymentUrl;
    }


    /**
     * Sets the payment url.
     *
     * @param paymentUrl the new payment url
     */
    public void setPaymentUrl(String paymentUrl) {
        this.paymentUrl = paymentUrl;
    }


    /**
     * Gets the new payment warnings.
     *
     * @return the new payment warnings
     */
    public List<FailureMessage> getNewPaymentWarnings() {
        return newPaymentWarnings;
    }


    /**
     * Sets the new payment warnings.
     *
     * @param warnings the new payment warnings
     */
    public void setNewPaymentWarnings(List<FailureMessage> warnings) {
        this.newPaymentWarnings = warnings;
    }


    /**
     * Checks for the new payment warnings.
     *
     * @return true, if the new payment warnings are present
     */
    public boolean hasNewPaymentWarnings() {
        return newPaymentWarnings != null && !newPaymentWarnings.isEmpty();
    }


    /**
     * Gets the response payment errors.
     *
     * @return the response payment errors
     */
    public final List<FailureMessage> getResponsePaymentErrors() {
        return responsePaymentErrors;
    }


    /**
     * Sets the response payment errors.
     *
     * @param responsePaymentErrors the new response payment errors
     */
    public void setResponsePaymentErrors(List<FailureMessage> responsePaymentErrors) {
        this.responsePaymentErrors = responsePaymentErrors;
    }


    /**
     * Checks for the response payment errors.
     *
     * @return true, if the response payment errors are present
     */
    public final boolean hasResponsePaymentErrors() {
        return responsePaymentErrors != null && !responsePaymentErrors.isEmpty();
    }

}
