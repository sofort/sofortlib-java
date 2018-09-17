package com.sofort.lib.payment.products.request;

import com.sofort.lib.core.products.common.BankAccount;
import com.sofort.lib.core.products.request.SofortLibRequest;
import com.sofort.lib.core.products.request.parts.Notification;

import java.util.List;


/**
 * The API payment request request container.
 */
public class PaymentRequest extends SofortLibRequest {

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
     * The timeout.
     */
    private Long timeout;

    /**
     * The email customer.
     */
    private String emailCustomer;

    /**
     * The phone customer.
     */
    private String phoneCustomer;

    /**
     * The user variables.
     */
    private List<String> userVariables;

    /**
     * The sender.
     */
    private BankAccount sender;

    /**
     * The amount.
     */
    private final Double amount;

    /**
     * The currency code.
     */
    private final String currencyCode;

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
     * The timeout url.
     */
    private String timeoutUrl;

    /**
     * The notification urls.
     */
    private List<Notification> notificationUrls;

    /**
     * The notification emails.
     */
    private List<Notification> notificationEmails;

    /**
     * The sofort payment special container.
     */
    private final SofortPayment sofortPayment;


    /**
     * Instantiates a new sofort payment request.
     *
     * @param projectId     the project identification number
     * @param amount        double value
     * @param currencyCode  according to ISO 4217, i.e. EUR
     * @param reasons       transfer reasons
     * @param sofortPayment sofort payment parameter class
     */
    public PaymentRequest(Integer projectId, Double amount, String currencyCode, List<String> reasons, SofortPayment sofortPayment) {
        this.projectId = projectId;
        this.amount = amount;
        this.currencyCode = currencyCode;
        this.reasons = reasons;
        this.sofortPayment = sofortPayment;
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
     * @return the sofort payment request
     */
    public PaymentRequest setInterfaceVersion(String interfaceVersion) {
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
     * @return the sofort payment request
     */
    public PaymentRequest setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
        return this;
    }


    /**
     * Gets the timeout.
     *
     * @return the timeout
     */
    public Long getTimeout() {
        return timeout;
    }


    /**
     * Sets the timeout in seconds. Default/Not set - a request without
     * expiration.
     *
     * @param timeout the timeout in seconds
     * @return the sofort payment request
     */
    public PaymentRequest setTimeout(long timeout) {
        this.timeout = Long.valueOf(timeout);
        return this;
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
     * @param emailCustomer the email customer
     * @return the sofort payment request
     */
    public PaymentRequest setEmailCustomer(String emailCustomer) {
        this.emailCustomer = emailCustomer;
        return this;
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
     * @param phoneCustomer the phone customer
     * @return the sofort payment request
     */
    public PaymentRequest setPhoneCustomer(String phoneCustomer) {
        this.phoneCustomer = phoneCustomer;
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
     * @return the sofort payment request
     */
    public PaymentRequest setUserVariables(List<String> userVariables) {
        this.userVariables = userVariables;
        return this;
    }


    /**
     * Gets the sender.
     *
     * @return the sender
     */
    public BankAccount getSender() {
        return sender;
    }


    /**
     * Sets the sender.
     *
     * @param sender the sender
     * @return the sofort payment request
     */
    public PaymentRequest setSender(BankAccount sender) {
        this.sender = sender;
        return this;
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
     * @return the sofort payment request
     */
    public PaymentRequest setSuccessUrl(String successUrl) {
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
     * @return the sofort payment request
     */
    public PaymentRequest setSuccessLinkRedirect(boolean successLinkRedirect) {
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
     * @return the sofort payment request
     */
    public PaymentRequest setAbortUrl(String abortUrl) {
        this.abortUrl = abortUrl;
        return this;
    }


    /**
     * Gets the timeout url.
     *
     * @return the timeout url
     */
    public String getTimeoutUrl() {
        return timeoutUrl;
    }


    /**
     * Sets the timeout url.
     *
     * @param timeoutUrl the timeout url
     * @return the sofort payment request
     */
    public PaymentRequest setTimeoutUrl(String timeoutUrl) {
        this.timeoutUrl = timeoutUrl;
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
     * @return the sofort payment request
     */
    public PaymentRequest setNotificationUrls(List<Notification> notificationUrls) {
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
     * @return the sofort payment request
     */
    public PaymentRequest setNotificationEmails(List<Notification> notificationEmails) {
        this.notificationEmails = notificationEmails;
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
     * Gets the project id.
     *
     * @return the project id
     */
    public Integer getProjectId() {
        return projectId;
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
     * Gets the sofort payment special container.
     *
     * @return the sofort payment special container
     */
    public SofortPayment getSofortPayment() {
        return sofortPayment;
    }

}
