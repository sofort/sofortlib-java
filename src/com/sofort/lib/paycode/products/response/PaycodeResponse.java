package com.sofort.lib.paycode.products.response;

import com.sofort.lib.core.products.response.SofortLibResponse;
import com.sofort.lib.core.products.response.parts.FailureMessage;

import java.util.List;


/**
 * The API SOFORT Paycode response container..
 */
public class PaycodeResponse extends SofortLibResponse {

    /**
     * The generated paycode.
     */
    private String paycode;

    /**
     * The generated paycode url.
     */
    private String paycodeUrl;

    /**
     * The warnings.
     */
    private List<FailureMessage> warnings;


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
     * @param paycode the new paycode
     */
    public void setPaycode(String paycode) {
        this.paycode = paycode;
    }


    /**
     * Gets the paycode url.
     *
     * @return the paycode url
     */
    public String getPaycodeUrl() {
        return paycodeUrl;
    }


    /**
     * Sets the paycode url.
     *
     * @param paycodeUrl the new paycode url
     */
    public void setPaycodeUrl(String paycodeUrl) {
        this.paycodeUrl = paycodeUrl;
    }


    /**
     * Checks for warnings.
     *
     * @return true, if successful
     */
    public boolean hasWarnings() {
        return warnings != null && !warnings.isEmpty();
    }


    /**
     * Gets the warnings.
     *
     * @return the warnings
     */
    public List<FailureMessage> getWarnings() {
        return warnings;
    }


    /**
     * Sets the warnings.
     *
     * @param warnings the new warnings
     */
    public void setWarnings(List<FailureMessage> warnings) {
        this.warnings = warnings;
    }
}
