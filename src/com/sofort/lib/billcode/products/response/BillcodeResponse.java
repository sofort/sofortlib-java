package com.sofort.lib.billcode.products.response;

import com.sofort.lib.core.products.response.SofortLibResponse;
import com.sofort.lib.core.products.response.parts.FailureMessage;

import java.util.List;


/**
 * The API SOFORT Billcode response container..
 */
public class BillcodeResponse extends SofortLibResponse {

    /**
     * The generated billcode.
     */
    private String billcode;

    /**
     * The generated billcode url.
     */
    private String billcodeUrl;

    /**
     * The warnings.
     */
    private List<FailureMessage> warnings;


    /**
     * Gets the billcode.
     *
     * @return the billcode
     */
    public String getBillcode() {
        return billcode;
    }


    /**
     * Sets the billcode.
     *
     * @param billcode the new billcode
     */
    public void setBillcode(String billcode) {
        this.billcode = billcode;
    }


    /**
     * Gets the billcode url.
     *
     * @return the billcode url
     */
    public String getBillcodeUrl() {
        return billcodeUrl;
    }


    /**
     * Sets the billcode url.
     *
     * @param billcodeUrl the new billcode url
     */
    public void setBillcodeUrl(String billcodeUrl) {
        this.billcodeUrl = billcodeUrl;
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
