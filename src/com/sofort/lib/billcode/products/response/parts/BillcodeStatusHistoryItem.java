package com.sofort.lib.billcode.products.response.parts;

import com.sofort.lib.billcode.products.common.BillcodeTransactionStatus;
import com.sofort.lib.billcode.products.common.BillcodeTransactionStatusReason;

import java.util.Date;


/**
 * The API payment status history item container.
 */
public class BillcodeStatusHistoryItem {

    /**
     * The status.
     */
    private BillcodeTransactionStatus status;

    /**
     * The status reason.
     */
    private BillcodeTransactionStatusReason statusReason;

    /**
     * The time.
     */
    private Date time;


    /**
     * Gets the status.
     *
     * @return the status
     */
    public BillcodeTransactionStatus getStatus() {
        return status;
    }


    /**
     * Sets the status.
     *
     * @param status the new status
     */
    public void setStatus(BillcodeTransactionStatus status) {
        this.status = status;
    }


    /**
     * Gets the status reason.
     *
     * @return the status reason
     */
    public BillcodeTransactionStatusReason getStatusReason() {
        return statusReason;
    }


    /**
     * Sets the status reason.
     *
     * @param statusReason the new status reason
     */
    public void setStatusReason(BillcodeTransactionStatusReason statusReason) {
        this.statusReason = statusReason;
    }


    /**
     * Gets the time.
     *
     * @return the time
     */
    public Date getTime() {
        return time;
    }


    /**
     * Sets the time.
     *
     * @param time the new time
     */
    public void setTime(Date time) {
        this.time = time;
    }
}
