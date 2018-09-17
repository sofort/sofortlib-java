package com.sofort.lib.paycode.products.response;

import com.sofort.lib.core.products.common.Bank;
import com.sofort.lib.core.products.response.SofortLibResponse;
import com.sofort.lib.core.products.response.parts.TransactionDetails;
import com.sofort.lib.paycode.products.response.parts.PaycodeStatus;

import java.util.Date;
import java.util.List;


/**
 * The API SOFORT Payment (SOFORT Überweisung) transaction details container
 * based on the {@link TransactionDetails}.
 */
public class PaycodeStatusResponse extends SofortLibResponse {

    /**
     * The status.
     */
    private PaycodeStatus status;

    /**
     * The paycode.
     */
    private String paycode;

    /**
     * The project id.
     */
    private int projectId;

    /**
     * The trans id.
     */
    private String transId;

    /**
     * The amount.
     */
    private double amount;

    /**
     * The reasons.
     */
    private List<String> reasons;

    /**
     * The time created.
     */
    private Date timeCreated;

    /**
     * The time used.
     */
    private Date timeUsed;

    /**
     * The start date.
     */
    private Date startDate;

    /**
     * The end date.
     */
    private Date endDate;

    /**
     * The currency code.
     */
    private String currencyCode;

    /**
     * The language code.
     */
    private String languageCode;

    /**
     * The sender.
     */
    private Bank sender;

    /**
     * The user variables.
     */
    private List<String> userVariables;


    /**
     * Gets the status.
     *
     * @return the status
     */
    public PaycodeStatus getStatus() {
        return status;
    }


    /**
     * Sets the status.
     *
     * @param status the status to set
     */
    public void setStatus(PaycodeStatus status) {
        this.status = status;
    }


    /**
     * Gets the paycode.
     *
     * @return the paycode
     */
    public String getPaycode() {
        return paycode;
    }


    /**
     * Sets the paycode.
     *
     * @param paycode the paycode to set
     */
    public void setPaycode(String paycode) {
        this.paycode = paycode;
    }


    /**
     * Gets the project id.
     *
     * @return the projectId
     */
    public int getProjectId() {
        return projectId;
    }


    /**
     * Sets the project id.
     *
     * @param projectId the projectId to set
     */
    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }


    /**
     * Gets the transId.
     *
     * @return the transId
     */
    public String getTransId() {
        return transId;
    }


    /**
     * Sets the transId.
     *
     * @param transId the transId to set
     */
    public void setTransId(String transId) {
        this.transId = transId;
    }


    /**
     * Gets the amount.
     *
     * @return the amount
     */
    public double getAmount() {
        return amount;
    }


    /**
     * Sets the amount.
     *
     * @param amount the amount to set
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }


    /**
     * Gets the reasons.
     *
     * @return the reasons
     */
    public List<String> getReasons() {
        return reasons;
    }


    /**
     * Sets the reasons.
     *
     * @param reasons the reasons to set
     */
    public void setReasons(List<String> reasons) {
        this.reasons = reasons;
    }


    /**
     * Gets the time created.
     *
     * @return the timeCreated
     */
    public Date getTimeCreated() {
        return timeCreated;
    }


    /**
     * Sets the time created.
     *
     * @param timeCreated the timeCreated to set
     */
    public void setTimeCreated(Date timeCreated) {
        this.timeCreated = timeCreated;
    }


    /**
     * Gets the time used.
     *
     * @return the timeUsed
     */
    public Date getTimeUsed() {
        return timeUsed;
    }


    /**
     * Sets the time used.
     *
     * @param timeUsed the timeUsed to set
     */
    public void setTimeUsed(Date timeUsed) {
        this.timeUsed = timeUsed;
    }


    /**
     * Gets the start date.
     *
     * @return the startDate
     */
    public Date getStartDate() {
        return startDate;
    }


    /**
     * Sets the start date.
     *
     * @param startDate the startDate to set
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }


    /**
     * Gets the end date.
     *
     * @return the endDate
     */
    public Date getEndDate() {
        return endDate;
    }


    /**
     * Sets the end date.
     *
     * @param endDate the endDate to set
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }


    /**
     * Gets the currency code.
     *
     * @return the currencyCode
     */
    public String getCurrencyCode() {
        return currencyCode;
    }


    /**
     * Sets the currency code.
     *
     * @param currencyCode the currencyCode to set
     */
    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }


    /**
     * Gets the language code.
     *
     * @return the languageCode
     */
    public String getLanguageCode() {
        return languageCode;
    }


    /**
     * Sets the language code.
     *
     * @param languageCode the languageCode to set
     */
    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }


    /**
     * Gets the sender.
     *
     * @return the sender
     */
    public Bank getSender() {
        return sender;
    }


    /**
     * Sets the sender.
     *
     * @param sender the sender to set
     */
    public void setSender(Bank sender) {
        this.sender = sender;
    }


    /**
     * Gets the user variables.
     *
     * @return the userVariables
     */
    public List<String> getUserVariables() {
        return userVariables;
    }


    /**
     * Sets the user variables.
     *
     * @param userVariables the userVariables to set
     */
    public void setUserVariables(List<String> userVariables) {
        this.userVariables = userVariables;
    }

}
