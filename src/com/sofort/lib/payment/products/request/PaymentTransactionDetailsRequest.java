package com.sofort.lib.payment.products.request;

import com.sofort.lib.core.products.request.SofortLibRequest;
import com.sofort.lib.payment.products.response.parts.PaymentStatus;
import com.sofort.lib.payment.products.response.parts.PaymentStatusReason;

import java.util.Date;
import java.util.List;


/**
 * The API transaction details request container.
 * <p>
 * If transaction IDs are set the other parameters will be ignored on request!
 */
public class PaymentTransactionDetailsRequest extends SofortLibRequest {

    /**
     * the product type is constant
     */
    public static final String product = "payment";

    /**
     * The transaction IDs.
     */
    private List<String> transIds = null;

    /**
     * The from time.
     */
    private Date fromTime;

    /**
     * The to time.
     */
    private Date toTime;

    /**
     * The from status modified time.
     */
    private Date fromStatusModifiedTime;

    /**
     * The to status modified time.
     */
    private Date toStatusModifiedTime;

    /**
     * The status.
     */
    private PaymentStatus status;

    /**
     * The status reason.
     */
    private PaymentStatusReason statusReason;

    /**
     * The number.
     */
    private Integer number;

    /**
     * The page.
     */
    private Integer page;


    /**
     * Gets the transaction IDs.
     *
     * @return the transaction IDs
     */
    public List<String> getTransIds() {
        return transIds;
    }


    /**
     * Sets the transaction IDs.
     *
     * @param transIds the transaction IDs
     * @return the transaction details payment request
     */
    public PaymentTransactionDetailsRequest setTransIds(List<String> transIds) {
        this.transIds = transIds;
        return this;
    }


    /**
     * Gets the from time.
     *
     * @return the from time
     */
    public Date getFromTime() {
        return fromTime;
    }


    /**
     * Sets the from time.
     *
     * @param fromTime the from time
     * @return the transaction details payment request
     */
    public PaymentTransactionDetailsRequest setFromTime(Date fromTime) {
        this.fromTime = fromTime;
        return this;
    }


    /**
     * Gets the to time.
     *
     * @return the to time
     */
    public Date getToTime() {
        return toTime;
    }


    /**
     * Sets the to time.
     *
     * @param toTime the to time
     * @return the transaction details payment request
     */
    public PaymentTransactionDetailsRequest setToTime(Date toTime) {
        this.toTime = toTime;
        return this;
    }


    /**
     * Gets the from status modified time.
     *
     * @return the from status modified time
     */
    public Date getFromStatusModifiedTime() {
        return fromStatusModifiedTime;
    }


    /**
     * Sets the from status modified time.
     *
     * @param fromStatusModifiedTime the from status modified time
     * @return the transaction details payment request
     */
    public PaymentTransactionDetailsRequest setFromStatusModifiedTime(Date fromStatusModifiedTime) {
        this.fromStatusModifiedTime = fromStatusModifiedTime;
        return this;
    }


    /**
     * Gets the to status modified time.
     *
     * @return the to status modified time
     */
    public Date getToStatusModifiedTime() {
        return toStatusModifiedTime;
    }


    /**
     * Sets the to status modified time.
     *
     * @param toStatusModifiedTime the to status modified time
     * @return the transaction details payment request
     */
    public PaymentTransactionDetailsRequest setToStatusModifiedTime(Date toStatusModifiedTime) {
        this.toStatusModifiedTime = toStatusModifiedTime;
        return this;
    }


    /**
     * Gets the status.
     *
     * @return the status
     */
    public PaymentStatus getStatus() {
        return status;
    }


    /**
     * Sets the status.
     *
     * @param status the status
     * @return the transaction details payment request
     */
    public PaymentTransactionDetailsRequest setStatus(PaymentStatus status) {
        this.status = status;
        return this;
    }


    /**
     * Gets the status reason.
     *
     * @return the status reason
     */
    public PaymentStatusReason getStatusReason() {
        return statusReason;
    }


    /**
     * Sets the status reason.
     *
     * @param statusReason the status reason
     * @return the transaction details payment request
     */
    public PaymentTransactionDetailsRequest setStatusReason(PaymentStatusReason statusReason) {
        this.statusReason = statusReason;
        return this;
    }


    /**
     * Gets the number.
     *
     * @return the number
     */
    public Integer getNumber() {
        return number;
    }


    /**
     * Sets the number.
     *
     * @param number the number
     * @return the transaction details payment request
     */
    public PaymentTransactionDetailsRequest setNumber(Integer number) {
        this.number = number;
        return this;
    }


    /**
     * Gets the page number.
     *
     * @return the page number
     */
    public Integer getPage() {
        return page;
    }


    /**
     * Sets the page number.
     *
     * @param page the page number
     * @return the transaction details payment request
     */
    public PaymentTransactionDetailsRequest setPage(Integer page) {
        this.page = page;
        return this;
    }

}
