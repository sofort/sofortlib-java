package com.sofort.lib.ideal.products.ideal;

import com.sofort.lib.core.internal.utils.HashAlgorithm;
import com.sofort.lib.core.internal.utils.HashBuilder;
import com.sofort.lib.ideal.products.response.IDealNotificationResponse;


/**
 * The API iDEAL notification response hash calculator.
 * 
 * Implements the exactly the same way of hash calculation like on the API side.
 */
public class IDealNotificationResponseHashCalculator {

	/** The filled notification response. */
	private final IDealNotificationResponse notification;

	/** The container. */
	private StringBuilder container;


	/**
	 * Instantiates a new iDEAL notification response hash calculator.
	 * 
	 * @param notification
	 *            the notification
	 */
	public IDealNotificationResponseHashCalculator(IDealNotificationResponse notification) {
		this.notification = notification;
	}


	/**
	 * Calculates the hash value for the given notification response with given
	 * hash algorithm and the password defined in the control center.
	 * 
	 * @param password
	 *            the password defined in the control center
	 * @param hash
	 *            the hash algorithm defined in the control center
	 * @return the hash value for given notification response
	 */
	public String getHash(String password, HashAlgorithm hash) {

		container = new StringBuilder();

		append(notification.getTransId());
		append(String.valueOf(notification.getUserId()));
		append(String.valueOf(notification.getProjectId()));
		append(notification.getSenderHolder());
		append(notification.getSenderAccountNumber());
		append(notification.getSenderBankName());
		append(notification.getSenderBankBic());
		append(notification.getSenderIban());
		append(notification.getSenderCountryId());
		append(notification.getRecepientHolder());
		append(notification.getRecepientAccountNumber());
		append(notification.getRecepientBankCode());
		append(notification.getRecepientBankName());
		append(notification.getRecepientBankBic());
		append(notification.getRecepientIban());
		append(notification.getRecepientCountryId());
		append(String.valueOf(notification.getAmount()));
		append(notification.getCurrencyId());
		append(notification.getReason1());
		append(notification.getReason2());
		append(notification.getUserVariable0());
		append(notification.getUserVariable1());
		append(notification.getUserVariable2());
		append(notification.getUserVariable3());
		append(notification.getUserVariable4());
		append(notification.getUserVariable5());
		append(notification.getCreated());
		append(notification.getStatus());
		append(notification.getStatusModified());

		append(password);

		return new HashBuilder(container.toString()).getHashCode(hash);
	}


	/**
	 * Appends the value to container in proper way.
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
