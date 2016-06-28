package com.sofort.lib.ideal.products.ideal;

import java.net.URL;

import com.sofort.lib.core.internal.utils.HashAlgorithm;
import com.sofort.lib.core.internal.utils.NumberUtilities;
import com.sofort.lib.core.internal.utils.StringUtilities;
import com.sofort.lib.ideal.products.request.IDealRequest;


/**
 * The API iDEAL request payment URL builder.
 * 
 * Builds the redirection payment URL for an iDEAL payment request (GET method).
 */
public class IDealRequestPaymentUrlBuilder {

	/** The iDEAL payment request. */
	private final IDealRequest request;

	/** The container. */
	private StringBuilder container;

	/** The string utilities. */
	private final StringUtilities stringUtilities;


	/**
	 * Instantiates a new iDEAL request payment url builder.
	 * 
	 * @param request
	 *            the request
	 */
	public IDealRequestPaymentUrlBuilder(IDealRequest request) {
		this.request = request;

		stringUtilities = new StringUtilities();
	}


	/**
	 * Gets the full proper encoded redirection iDEAL payment URL (GET method).
	 * 
	 * @param url
	 *            the URL prefix (current defined sofort.com/ideal URL)
	 * @param password
	 *            the password defined in the control center
	 * @param hash
	 *            the hash algorithm defined in the control center
	 * @return the proper encoded redirection iDEAL payment URL
	 */
	public String getPaymentUrl(URL url, String password, HashAlgorithm hash) {

		container = new StringBuilder();

		append("user_id", String.valueOf(request.getUserId()));
		append("project_id", String.valueOf(request.getProjectId()));
		append("amount", new NumberUtilities().formatAmount(request.getAmount()));
		append("reason_1", request.getReason1());
		append("reason_2", request.getReason2());
		append("user_variable_0", request.getUserVariable0());
		append("user_variable_1", request.getUserVariable1());
		append("user_variable_2", request.getUserVariable2());
		append("user_variable_3", request.getUserVariable3());
		append("user_variable_4", request.getUserVariable4());
		append("user_variable_5", request.getUserVariable5());
		append("sender_bank_code", request.getSenderBankCode());
		append("sender_account_number", request.getSenderAccountNumber());
		append("sender_holder", request.getSenderHolder());
		append("sender_country_id", request.getSenderCountryId());

		append("hash", new IDealRequestHashCalculator(request).getHash(password, hash));

		append("language_id", request.getLanguageId());
		append("interface_timeout", String.valueOf(request.getInterfaceTimeout()));
		append("interface_version", request.getInterfaceVersion());

		return url.toExternalForm() + container.toString();
	}


	/**
	 * Append the key and URL encoded value to container in a proper way.
	 * 
	 * @param key
	 *            the key
	 * @param value
	 *            the value
	 */
	public void append(String key, String value) {

		if (value == null) {
			return;
		}

		if (container.length() == 0) {
			container.append('?');
		} else {
			container.append('&');
		}
		container.append(key);
		container.append('=');

		container.append(stringUtilities.urlEncode(value));
	}
}
