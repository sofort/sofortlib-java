package com.sofort.lib.core.products.response.parts;

import com.sofort.lib.core.products.common.BankAccount;

import java.util.Date;
import java.util.List;


/**
 * Container for the API common transaction details.
 */
public abstract class TransactionDetails {

    /**
     * The project id.
     */
    private int projectId;

    /**
     * The trans id.
     */
    private String transId;

    /**
     * The test.
     */
    private boolean test;

    /**
     * The time.
     */
    private Date time;

    /**
     * The payment method.
     */
    private String paymentMethod;

    /**
     * The language code.
     */
    private String languageCode;

    /**
     * The amount.
     */
    private double amount;

    /**
     * The amount refunded.
     */
    private double amountRefunded;

    /**
     * The currency code.
     */
    private String currencyCode;

    /**
     * The reasons.
     */
    private List<String> reasons;

    /**
     * The user variables.
     */
    private List<String> userVariables;

    /**
     * The sender.
     */
    private BankAccount sender;

    /**
     * The recipient.
     */
    private BankAccount recipient;

    /**
     * The exchange rate.
     */
    private double exchangeRate;

    /**
     * The costs.
     */
    private Costs costs;


    /**
     * Gets the project id.
     *
     * @return the project id
     */
    public int getProjectId() {
        return projectId;
    }


    /**
     * Sets the project id.
     *
     * @param projectId the new project id
     */
    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }


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
     * Checks if is test.
     *
     * @return true, if is test
     */
    public boolean isTest() {
        return test;
    }


    /**
     * Sets the test.
     *
     * @param test the new test
     */
    public void setTest(boolean test) {
        this.test = test;
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


    /**
     * Gets the payment method.
     *
     * @return the payment method
     */
    public String getPaymentMethod() {
        return paymentMethod;
    }


    /**
     * Sets the payment method.
     *
     * @param paymentMethod the new payment method
     */
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
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
     * @param languageCode the new language code
     */
    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
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
     * @param amount the new amount
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }


    /**
     * Gets the amount refunded.
     *
     * @return the amount refunded
     */
    public double getAmountRefunded() {
        return amountRefunded;
    }


    /**
     * Sets the amount refunded.
     *
     * @param amountRefunded the new amount refunded
     */
    public void setAmountRefunded(double amountRefunded) {
        this.amountRefunded = amountRefunded;
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
     */
    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
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
     * @param reasons the new reasons
     */
    public void setReasons(List<String> reasons) {
        this.reasons = reasons;
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
     * @param userVariables the new user variables
     */
    public void setUserVariables(List<String> userVariables) {
        this.userVariables = userVariables;
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
     * @param sender the new sender
     */
    public void setSender(BankAccount sender) {
        this.sender = sender;
    }


    /**
     * Gets the recipient.
     *
     * @return the recipient
     */
    public BankAccount getRecipient() {
        return recipient;
    }


    /**
     * Sets the recipient.
     *
     * @param recipient the new recipient
     */
    public void setRecipient(BankAccount recipient) {
        this.recipient = recipient;
    }


    /**
     * Gets the exchange rate.
     *
     * @return the exchange rate
     */
    public double getExchangeRate() {
        return exchangeRate;
    }


    /**
     * Sets the exchange rate.
     *
     * @param exchangeRate the new exchange rate
     */
    public void setExchangeRate(double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }


    /**
     * Gets the costs.
     *
     * @return the costs
     */
    public Costs getCosts() {
        return costs;
    }


    /**
     * Sets the costs.
     *
     * @param costs the new costs
     */
    public void setCosts(Costs costs) {
        this.costs = costs;
    }

}
