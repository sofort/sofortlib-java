package com.sofort.lib.payment.products.response.parts;

import com.sofort.lib.core.products.response.parts.TransactionDetails;

import java.util.Date;
import java.util.List;


/**
 * The API SOFORT Payment (SOFORT Überweisung) transaction details container
 * based on the {@link TransactionDetails}.
 */
public class PaymentTransactionDetails extends TransactionDetails {

    /**
     * the product type is constant
     */
    public static final String product = "payment";

    /**
     * The status.
     */
    private PaymentStatus status;

    /**
     * The status reason.
     */
    private PaymentStatusReason statusReason;

    /**
     * The status history items.
     */
    private List<PaymentStatusHistoryItem> statusHistoryItems;

    /**
     * The status modified.
     */
    private Date statusModified;

    /**
     * The email customer.
     */
    private String emailCustomer;

    /**
     * The phone customer.
     */
    private String phoneCustomer;

    /**
     * The consumer protection.
     */
    private boolean consumerProtection;


    /**
     * Gets the status.
     *
     * @return the status
     */
    public PaymentStatus getStatus() {
        return status;
    }


    /**
     * Sets the status (read the API doc to find out the available statuses).
     *
     * @param status the new status
     */
    public void setStatus(PaymentStatus status) {
        this.status = status;
    }


    /**
     * Gets the status reason (read the API doc to find out the available status
     * reasons).
     *
     * @return the status reason
     */
    public PaymentStatusReason getStatusReason() {
        return statusReason;
    }


    /**
     * Sets the status reason.
     *
     * @param statusReason the new status reason
     */
    public void setStatusReason(PaymentStatusReason statusReason) {
        this.statusReason = statusReason;
    }


    /**
     * Gets the status history items.
     *
     * @return the status history items
     */
    public List<PaymentStatusHistoryItem> getStatusHistoryItems() {
        return statusHistoryItems;
    }


    /**
     * Sets the status history items.
     *
     * @param statusHistoryItems the new status history items
     */
    public void setStatusHistoryItems(List<PaymentStatusHistoryItem> statusHistoryItems) {
        this.statusHistoryItems = statusHistoryItems;
    }


    /**
     * Gets the status modified.
     *
     * @return the status modified
     */
    public Date getStatusModified() {
        return statusModified;
    }


    /**
     * Sets the status modified.
     *
     * @param statusModified the new status modified
     */
    public void setStatusModified(Date statusModified) {
        this.statusModified = statusModified;
    }


    /**
     * Gets the email customer.
     *
     * @return the email customer
     */
    public String getEmailCustomer() {
        return emailCustomer;
    }


    /**
     * Sets the email customer.
     *
     * @param emailCustomer the new email customer
     */
    public void setEmailCustomer(String emailCustomer) {
        this.emailCustomer = emailCustomer;
    }


    /**
     * Gets the phone customer.
     *
     * @return the phone customer
     */
    public String getPhoneCustomer() {
        return phoneCustomer;
    }


    /**
     * Sets the phone customer.
     *
     * @param phoneCustomer the new phone customer
     */
    public void setPhoneCustomer(String phoneCustomer) {
        this.phoneCustomer = phoneCustomer;
    }


    /**
     * Checks if is consumer protection.
     *
     * @return true, if is consumer protection
     */
    public boolean isConsumerProtection() {
        return consumerProtection;
    }


    /**
     * Sets the consumer protection.
     *
     * @param consumerProtection the new consumer protection
     */
    public void setConsumerProtection(boolean consumerProtection) {
        this.consumerProtection = consumerProtection;
    }

}
