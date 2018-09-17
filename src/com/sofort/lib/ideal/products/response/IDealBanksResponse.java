package com.sofort.lib.ideal.products.response;

import com.sofort.lib.core.products.response.SofortLibResponse;
import com.sofort.lib.core.products.response.parts.FailureMessage;
import com.sofort.lib.ideal.products.response.parts.IDealBank;

import java.util.List;


/**
 * The API IDeal bank list with codes and names response container.
 */
public class IDealBanksResponse extends SofortLibResponse {

    /**
     * The banks.
     */
    private List<IDealBank> banks;

    /**
     * The errors.
     */
    private List<FailureMessage> errors;


    /**
     * Gets the available list of banks.
     *
     * @return the banks
     */
    public List<IDealBank> getBanks() {
        return banks;
    }


    /**
     * Sets the banks.
     *
     * @param banks the new banks
     */
    public void setBanks(List<IDealBank> banks) {
        this.banks = banks;
    }


    /**
     * Gets the errors.
     *
     * @return the errors
     */
    public List<FailureMessage> getErrors() {
        return errors;
    }


    /**
     * Sets the errors.
     *
     * @param errors the new errors
     */
    public void setErrors(List<FailureMessage> errors) {
        this.errors = errors;
    }


    /**
     * Checks for errors.
     *
     * @return true, if successful
     */
    public boolean hasErrors() {
        return errors != null && !errors.isEmpty();
    }
}
