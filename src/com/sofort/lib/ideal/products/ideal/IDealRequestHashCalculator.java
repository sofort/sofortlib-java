package com.sofort.lib.ideal.products.ideal;

import com.sofort.lib.core.internal.utils.HashAlgorithm;
import com.sofort.lib.core.internal.utils.HashBuilder;
import com.sofort.lib.core.internal.utils.NumberUtilities;
import com.sofort.lib.ideal.products.request.IDealRequest;


/**
 * The API iDEAL request hash calculator.
 * 
 * Implements the exactly the same way of hash calculation like on the API side.
 */
public class IDealRequestHashCalculator {

	/** The request. */
	private final IDealRequest request;

	/** The container. */
	private StringBuilder container;


	/**
	 * Instantiates a new iDEAL request hash calculator.
	 * 
	 * @param request
	 *            the request
	 */
	public IDealRequestHashCalculator(IDealRequest request) {
		this.request = request;
	}


	/**
	 * Calculates the hash value for the given iDEAL request with given hash
	 * algorithm and the password defined in the control center.
	 * 
	 * @param password
	 *            the password defined in the control center
	 * @param hash
	 *            the hash algorithm defined in the control center
	 * @return the hash value for given request
	 */
	public String getHash(String password, HashAlgorithm hash) {

		container = new StringBuilder();

		append(String.valueOf(request.getUserId()));
		append(String.valueOf(request.getProjectId()));
		append(request.getSenderHolder());
		append(request.getSenderAccountNumber());
		append(request.getSenderBankCode());
		append(request.getSenderCountryId());
		append(new NumberUtilities().formatAmount(request.getAmount()));
		append(request.getReason1());
		append(request.getReason2());
		append(request.getUserVariable0());
		append(request.getUserVariable1());
		append(request.getUserVariable2());
		append(request.getUserVariable3());
		append(request.getUserVariable4());
		append(request.getUserVariable5());

		append(password);

		return new HashBuilder(container.toString()).getHashCode(hash);
	}


	/**
	 * Appends the value to container in a proper way.
	 * 
	 * @param value
	 *            the value
	 */
	public void append(String value) {
		if (container.length() > 0) {
			container.append('|');
		}

		container.append(value == null ? "" : value);
	}
}
