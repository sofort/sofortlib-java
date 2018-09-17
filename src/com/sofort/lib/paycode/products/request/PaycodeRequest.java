package com.sofort.lib.paycode.products.request;

import com.sofort.lib.core.products.common.Bank;
import com.sofort.lib.core.products.request.SofortLibRequest;
import com.sofort.lib.core.products.request.parts.Notification;

import java.util.Date;
import java.util.List;


/**
 * The API paycode request container.
 */
public class PaycodeRequest extends SofortLibRequest {

    /**
     * The project id.
     */
    private final Integer projectId;

    /**
     * The interface version.
     */
    private String interfaceVersion;

    /**
     * The language code.
     */
    private String languageCode;

    /**
     * The start date.
     */
    private Date startDate;

    /**
     * The end date.
     */
    private Date endDate;

    /**
     * The amount.
     */
    private final Double amount;

    /**
     * The currency code.
     */
    private String currencyCode;

    /**
     * The sender.
     */
    private Bank sender;

    /**
     * The reasons.
     */
    private final List<String> reasons;

    /**
     * The success url.
     */
    private String successUrl;

    /**
     * The success link redirect.
     */
    private Boolean successLinkRedirect;

    /**
     * The abort url.
     */
    private String abortUrl;

    /**
     * The notification urls.
     */
    private List<Notification> notificationUrls;

    /**
     * The notification emails.
     */
    private List<Notification> notificationEmails;

    /**
     * The user variables.
     */
    private List<String> userVariables;


    /**
     * Instantiates a new sofort paycode request.
     *
     * @param projectId the project identification number
     * @param amount    double value
     * @param reasons   transfer reasons
     */
    public PaycodeRequest(Integer projectId, Double amount, List<String> reasons) {
        this.projectId = projectId;
        this.amount = amount;
        this.reasons = reasons;
    }


    /**
     * Gets the project id.
     *
     * @return the project id
     */
    public Integer getProjectId() {
        return projectId;
    }


    /**
     * Gets the interface version.
     *
     * @return the interface version
     */
    public String getInterfaceVersion() {
        return interfaceVersion;
    }


    /**
     * Sets the interface version.
     *
     * @param interfaceVersion the interface version
     * @return the sofort paycode request
     */
    public PaycodeRequest setInterfaceVersion(String interfaceVersion) {
        this.interfaceVersion = interfaceVersion;
        return this;
    }


    /**
     * Gets the language code.
     *
     * @return the language code
     */
    public String getLanguageCode() {
        return languageCode;
    }


    /**
     * Sets the language code.
     *
     * @param languageCode the language code
     * @return the sofort paycode request
     */
    public PaycodeRequest setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
        return this;
    }


    /**
     * Gets the start date.
     *
     * @return the start date
     */
    public Date getStartDate() {
        return startDate;
    }


    /**
     * Sets the start date.
     *
     * @param startDate the start date
     * @return the sofort paycode request
     */
    public PaycodeRequest setStartDate(Date startDate) {
        this.startDate = startDate;
        return this;
    }


    /**
     * Gets the end date.
     *
     * @return the end date
     */
    public Date getEndDate() {
        return endDate;
    }


    /**
     * Sets the end date.
     *
     * @param endDate the end date
     * @return the sofort paycode request
     */
    public PaycodeRequest setEndDate(Date endDate) {
        this.endDate = endDate;
        return this;
    }


    /**
     * Gets the amount.
     *
     * @return the amount
     */
    public Double getAmount() {
        return amount;
    }


    /**
     * Gets the currency code.
     *
     * @return the currency code
     */
    public String getCurrencyCode() {
        return currencyCode;
    }


    /**
     * Sets the currency code.
     *
     * @param currencyCode the new currency code
     * @return the sofort paycode request
     */
    public PaycodeRequest setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
        return this;
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
     * @param sender the sender
     * @return the sofort paycode request
     */
    public PaycodeRequest setSender(Bank sender) {
        this.sender = sender;
        return this;
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
     * Gets the success url.
     *
     * @return the success url
     */
    public String getSuccessUrl() {
        return successUrl;
    }


    /**
     * Sets the success url.
     *
     * @param successUrl the success url
     * @return the sofort paycode request
     */
    public PaycodeRequest setSuccessUrl(String successUrl) {
        this.successUrl = successUrl;
        return this;
    }


    /**
     * Gets the success link redirect.
     *
     * @return the success link redirect
     */
    public Boolean getSuccessLinkRedirect() {
        return successLinkRedirect;
    }


    /**
     * Sets the success link redirect.
     *
     * @param successLinkRedirect the new success link redirect
     * @return the sofort paycode request
     */
    public PaycodeRequest setSuccessLinkRedirect(boolean successLinkRedirect) {
        this.successLinkRedirect = Boolean.valueOf(successLinkRedirect);
        return this;
    }


    /**
     * Gets the abort url.
     *
     * @return the abort url
     */
    public String getAbortUrl() {
        return abortUrl;
    }


    /**
     * Sets the abort url.
     *
     * @param abortUrl the abort url
     * @return the sofort paycode request
     */
    public PaycodeRequest setAbortUrl(String abortUrl) {
        this.abortUrl = abortUrl;
        return this;
    }


    /**
     * Gets the notification urls.
     *
     * @return the notification urls
     */
    public List<Notification> getNotificationUrls() {
        return notificationUrls;
    }


    /**
     * Sets the notification urls.
     *
     * @param notificationUrls the notification urls
     * @return the sofort paycode request
     */
    public PaycodeRequest setNotificationUrls(List<Notification> notificationUrls) {
        this.notificationUrls = notificationUrls;
        return this;
    }


    /**
     * Gets the notification emails.
     *
     * @return the notification emails
     */
    public List<Notification> getNotificationEmails() {
        return notificationEmails;
    }


    /**
     * Sets the notification emails.
     *
     * @param notificationEmails the notification emails
     * @return the sofort paycode request
     */
    public PaycodeRequest setNotificationEmails(List<Notification> notificationEmails) {
        this.notificationEmails = notificationEmails;
        return this;
    }


    /**
     * Gets the user variables.
     *
     * @return the user variables
     */
    public List<String> getUserVariables() {
        return userVariables;
    }


    /**
     * Sets the user variables.
     *
     * @param userVariables the user variables
     * @return the sofort paycode request
     */
    public PaycodeRequest setUserVariables(List<String> userVariables) {
        this.userVariables = userVariables;
        return this;
    }

}