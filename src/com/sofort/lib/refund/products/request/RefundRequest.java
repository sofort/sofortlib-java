package com.sofort.lib.refund.products.request;

import com.sofort.lib.core.products.request.SofortLibRequest;
import com.sofort.lib.refund.products.RefundBankAccount;
import com.sofort.lib.refund.products.request.parts.Refund;

import java.util.List;


/**
 * The API refund request container.
 */
public class RefundRequest extends SofortLibRequest {

    /**
     * The title.
     */
    private String title;

    /**
     * The sender.
     */
    private RefundBankAccount sender;

    /**
     * The refunds.
     */
    private final List<Refund> refunds;


    /**
     * Instantiates a new refund request with at least one {@link Refund}.
     *
     * @param refunds the refunds
     */
    public RefundRequest(List<Refund> refunds) {
        this.refunds = refunds;
    }


    /**
     * Gets the title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }


    /**
     * Sets the title.
     *
     * @param title the title
     * @return the refund request
     */
    public RefundRequest setTitle(String title) {
        this.title = title;
        return this;
    }


    /**
     * Gets the sender.
     *
     * @return the sender
     */
    public RefundBankAccount getSender() {
        return sender;
    }


    /**
     * Sets the sender.
     *
     * @param sender the sender
     * @return the refund request
     */
    public RefundRequest setSender(RefundBankAccount sender) {
        this.sender = sender;
        return this;
    }


    /**
     * Gets the refunds.
     *
     * @return the refunds
     */
    public List<Refund> getRefunds() {
        return refunds;
    }

}
