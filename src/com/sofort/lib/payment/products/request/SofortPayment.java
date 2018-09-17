package com.sofort.lib.payment.products.request;

/**
 * The class represents the sofort payment special container (API -&gt; 'su')
 */
public class SofortPayment {

    private Boolean consumerProtection;


    /**
     * Checks if is consumer protection.
     *
     * @return null, if the consumer protection is not set, true, if consumer
     * protection is on, false if consumer protection is off
     */
    public Boolean isConsumerProtection() {
        return consumerProtection;
    }


    /**
     * Sets the consumer protection flag.
     *
     * @param consumerProtection consumer protection flag
     * @return the sofort payment
     */
    public SofortPayment setConsumerProtection(Boolean consumerProtection) {
        this.consumerProtection = consumerProtection;
        return this;
    }

}
