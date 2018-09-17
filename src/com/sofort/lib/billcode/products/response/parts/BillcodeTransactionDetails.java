package com.sofort.lib.billcode.products.response.parts;

import com.sofort.lib.billcode.products.common.BillcodeTransactionStatus;
import com.sofort.lib.billcode.products.common.BillcodeTransactionStatusReason;
import com.sofort.lib.core.products.response.parts.TransactionDetails;

import java.util.Date;
import java.util.List;


/**
 * The API SOFORT Billcode transaction details container based on the
 * {@link TransactionDetails}.
 */
public class BillcodeTransactionDetails extends TransactionDetails {

    /**
     * The status.
     */
    private BillcodeTransactionStatus status;

    /**
     * The status reason.
     */
    private BillcodeTransactionStatusReason statusReason;

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
     * The billcode code.
     */
    private String billcode;

    /**
     * The status history items.
     */
    private List<BillcodeStatusHistoryItem> statusHistoryItems;


    /**
     * Gets the status.
     *
     * @return the status
     */
    public BillcodeTransactionStatus getStatus() {
        return status;
    }


    /**
     * Sets the status (read the API doc to find out the available statuses).
     *
     * @param status the new status
     */
    public void setStatus(BillcodeTransactionStatus status) {
        this.status = status;
    }


    /**
     * Gets the status reason (read the API doc to find out the available status
     * reasons).
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
     * Gets the status history items.
     *
     * @return the status history items
     */
    public List<BillcodeStatusHistoryItem> getStatusHistoryItems() {
        return statusHistoryItems;
    }


    /**
     * Sets the status history items.
     *
     * @param statusHistoryItems the new status history items
     */
    public void setStatusHistoryItems(List<BillcodeStatusHistoryItem> statusHistoryItems) {
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
     * Gets the billcode code.
     *
     * @return billcode code
     */
    public String getBillcode() {
        return billcode;
    }


    /**
     * Sets the billcode code.
     *
     * @param billcode billcode code
     */
    public void setBillcode(String billcode) {
        this.billcode = billcode;
    }

}
