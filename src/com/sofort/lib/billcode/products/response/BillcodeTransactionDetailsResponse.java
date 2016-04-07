package com.sofort.lib.billcode.products.response;

import java.util.List;

import com.sofort.lib.billcode.products.response.parts.BillcodeTransactionDetails;
import com.sofort.lib.core.products.response.SofortLibResponse;


/**
 * The paycode transaction details container.
 */
public class BillcodeTransactionDetailsResponse extends SofortLibResponse {

	/** The transaction details list. */
	private List<BillcodeTransactionDetails> transactionDetailsList;


	/**
	 * Sets the transaction details paycode list.
	 * 
	 * @param transactionDetailsList
	 *            the transaction details list
	 */
	public void setTransactionDetailsList(List<BillcodeTransactionDetails> transactionDetailsList) {
		this.transactionDetailsList = transactionDetailsList;
	}


	/**
	 * Gets the transaction details paycode list.
	 * 
	 * @return the transaction details paycode lists
	 */
	public List<BillcodeTransactionDetails> getTransactionDetailsList() {
		return transactionDetailsList;
	}

}
