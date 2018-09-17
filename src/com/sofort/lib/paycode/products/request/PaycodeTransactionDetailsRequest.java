package com.sofort.lib.paycode.products.request;

import com.sofort.lib.core.products.request.SofortLibRequest;
import com.sofort.lib.paycode.products.common.PaycodeTransactionStatus;
import com.sofort.lib.paycode.products.common.PaycodeTransactionStatusReason;

import java.util.Date;
import java.util.List;


/**
 * The API transaction details request container.
 * <p>
 * If transaction IDs are set the other parameters will be ignored on request!
 */
public class PaycodeTransactionDetailsRequest extends SofortLibRequest {

    /**
     * the product type is constant
     */
    public static final String product = "paycode";

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
    private PaycodeTransactionStatus status;

    /**
     * The status reason.
     */
    private PaycodeTransactionStatusReason statusReason;

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
     * @return the paycode transaction details request
     */
    public PaycodeTransactionDetailsRequest setTransIds(List<String> transIds) {
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
     * @return the paycode transaction details request
     */
    public PaycodeTransactionDetailsRequest setFromTime(Date fromTime) {
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
     * @return the paycode transaction details request
     */
    public PaycodeTransactionDetailsRequest setToTime(Date toTime) {
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
     * @return the paycode transaction details request
     */
    public PaycodeTransactionDetailsRequest setFromStatusModifiedTime(Date fromStatusModifiedTime) {
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
     * @return the paycode transaction details request
     */
    public PaycodeTransactionDetailsRequest setToStatusModifiedTime(Date toStatusModifiedTime) {
        this.toStatusModifiedTime = toStatusModifiedTime;
        return this;
    }


    /**
     * Gets the status.
     *
     * @return the status
     */
    public PaycodeTransactionStatus getStatus() {
        return status;
    }


    /**
     * Sets the status.
     *
     * @param status the status
     * @return the paycode transaction details request
     */
    public PaycodeTransactionDetailsRequest setStatus(PaycodeTransactionStatus status) {
        this.status = status;
        return this;
    }


    /**
     * Gets the status reason.
     *
     * @return the status reason
     */
    public PaycodeTransactionStatusReason getStatusReason() {
        return statusReason;
    }


    /**
     * Sets the status reason.
     *
     * @param statusReason the status reason
     * @return the paycode transaction details request
     */
    public PaycodeTransactionDetailsRequest setStatusReason(PaycodeTransactionStatusReason statusReason) {
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
     * @return the paycode transaction details request
     */
    public PaycodeTransactionDetailsRequest setNumber(Integer number) {
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
     * @return the paycode transaction details request
     */
    public PaycodeTransactionDetailsRequest setPage(Integer page) {
        this.page = page;
        return this;
    }

}
