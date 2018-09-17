package com.sofort.lib.core.products.response;

import com.sofort.lib.core.products.response.parts.FailureMessage;

import java.util.List;


/**
 * Definition of a SofortLib response.
 */
public abstract class SofortLibResponse {

    /**
     * The global response errors.
     */
    private List<FailureMessage> responseErrors;

    /**
     * The global response warnings.
     */
    private List<FailureMessage> responseWarnings;


    /**
     * Gets the global response warnings.
     *
     * @return the global response warnings
     */
    public List<FailureMessage> getResponseWarnings() {
        return responseWarnings;
    }


    /**
     * Sets the global response warnings.
     *
     * @param responseWarnings the new global response warnings
     */
    public void setResponseWarnings(List<FailureMessage> responseWarnings) {
        this.responseWarnings = responseWarnings;
    }


    /**
     * Checks for global response warnings.
     *
     * @return true, if global response warnings are present
     */
    public boolean hasResponseWarnings() {
        return responseWarnings != null && !responseWarnings.isEmpty();
    }


    /**
     * Gets the global response errors.
     *
     * @return the global response errors
     */
    public List<FailureMessage> getResponseErrors() {
        return responseErrors;
    }


    /**
     * Checks for global response errors.
     *
     * @return true, if global response errors are present
     */
    public boolean hasResponseErrors() {
        return responseErrors != null && !responseErrors.isEmpty();
    }


    /**
     * Sets the global response errors.
     *
     * @param responseErrors the new global response errors
     */
    public void setResponseErrors(List<FailureMessage> responseErrors) {
        this.responseErrors = responseErrors;
    }

}
