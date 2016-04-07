package com.sofort.lib.ideal.products.request;

import com.sofort.lib.core.products.request.SofortLibRequest;

/**
 * The API IDeal request container.
 */
public class IDealRequest extends SofortLibRequest {

	/** The user id. */
	private int userId;

	/** The project id. */
	private int projectId;

	/** The sender holder. */
	private String senderHolder;

	/** The sender account number. */
	private String senderAccountNumber;

	/** The sender bank code. */
	private String senderBankCode;

	/** The sender country id. */
	private String senderCountryId;

	/** The amount. */
	private double amount;

	/** The reason1. */
	private String reason1;

	/** The reason2. */
	private String reason2;

	/** The user variable0. */
	private String userVariable0;

	/** The user variable1. */
	private String userVariable1;

	/** The user variable2. */
	private String userVariable2;

	/** The user variable3. */
	private String userVariable3;

	/** The user variable4. */
	private String userVariable4;

	/** The user variable5. */
	private String userVariable5;

	/** The language id. */
	private String languageId;

	/** The interface timeout. */
	private long interfaceTimeout;

	/** The interface version. */
	private String interfaceVersion;


	/**
	 * Gets the user id.
	 * 
	 * @return the user id
	 */
	public int getUserId() {
		return userId;
	}


	/**
	 * Sets the user id.
	 * 
	 * @param userId
	 *            the user id
	 * @return the iDEAL request
	 */
	public IDealRequest setUserId(int userId) {
		this.userId = userId;
		return this;
	}


	/**
	 * Gets the project id.
	 * 
	 * @return the project id
	 */
	public int getProjectId() {
		return projectId;
	}


	/**
	 * Sets the project id.
	 * 
	 * @param projectId
	 *            the project id
	 * @return the iDEAL request
	 */
	public IDealRequest setProjectId(int projectId) {
		this.projectId = projectId;
		return this;
	}


	/**
	 * Gets the sender holder.
	 * 
	 * @return the sender holder
	 */
	public String getSenderHolder() {
		return senderHolder;
	}


	/**
	 * Sets the sender holder.
	 * 
	 * @param senderHolder
	 *            the sender holder
	 * @return the iDEAL request
	 */
	public IDealRequest setSenderHolder(String senderHolder) {
		this.senderHolder = senderHolder;
		return this;
	}


	/**
	 * Gets the sender account number.
	 * 
	 * @return the sender account number
	 */
	public String getSenderAccountNumber() {
		return senderAccountNumber;
	}


	/**
	 * Sets the sender account number.
	 * 
	 * @param senderAccountNumber
	 *            the sender account number
	 * @return the iDEAL request
	 */
	public IDealRequest setSenderAccountNumber(String senderAccountNumber) {
		this.senderAccountNumber = senderAccountNumber;
		return this;
	}


	/**
	 * Gets the sender bank code.
	 * 
	 * @return the sender bank code
	 */
	public String getSenderBankCode() {
		return senderBankCode;
	}


	/**
	 * Sets the sender bank code.
	 * 
	 * @param senderBankCode
	 *            the sender bank code
	 * @return the iDEAL request
	 */
	public IDealRequest setSenderBankCode(String senderBankCode) {
		this.senderBankCode = senderBankCode;
		return this;
	}


	/**
	 * Gets the sender country id.
	 * 
	 * @return the sender country id
	 */
	public String getSenderCountryId() {
		return senderCountryId;
	}


	/**
	 * Sets the sender country id.
	 * 
	 * @param senderCountryId
	 *            the sender country id
	 * @return the iDEAL request
	 */
	public IDealRequest setSenderCountryId(String senderCountryId) {
		this.senderCountryId = senderCountryId;
		return this;
	}


	/**
	 * Gets the amount.
	 * 
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}


	/**
	 * Sets the amount.
	 * 
	 * @param amount
	 *            the amount
	 * @return the iDEAL request
	 */
	public IDealRequest setAmount(double amount) {
		this.amount = amount;
		return this;
	}


	/**
	 * Gets the reason1.
	 * 
	 * @return the reason1
	 */
	public String getReason1() {
		return reason1;
	}


	/**
	 * Sets the reason1.
	 * 
	 * @param reason1
	 *            the reason1
	 * @return the iDEAL request
	 */
	public IDealRequest setReason1(String reason1) {
		this.reason1 = reason1;
		return this;
	}


	/**
	 * Gets the reason2.
	 * 
	 * @return the reason2
	 */
	public String getReason2() {
		return reason2;
	}


	/**
	 * Sets the reason2.
	 * 
	 * @param reason2
	 *            the reason2
	 * @return the iDEAL request
	 */
	public IDealRequest setReason2(String reason2) {
		this.reason2 = reason2;
		return this;
	}


	/**
	 * Gets the user variable0.
	 * 
	 * @return the user variable0
	 */
	public String getUserVariable0() {
		return userVariable0;
	}


	/**
	 * Sets the user variable0.
	 * 
	 * @param userVariable0
	 *            the user variable0
	 * @return the iDEAL request
	 */
	public IDealRequest setUserVariable0(String userVariable0) {
		this.userVariable0 = userVariable0;
		return this;
	}


	/**
	 * Gets the user variable1.
	 * 
	 * @return the user variable1
	 */
	public String getUserVariable1() {
		return userVariable1;
	}


	/**
	 * Sets the user variable1.
	 * 
	 * @param userVariable1
	 *            the user variable1
	 * @return the iDEAL request
	 */
	public IDealRequest setUserVariable1(String userVariable1) {
		this.userVariable1 = userVariable1;
		return this;
	}


	/**
	 * Gets the user variable2.
	 * 
	 * @return the user variable2
	 */
	public String getUserVariable2() {
		return userVariable2;
	}


	/**
	 * Sets the user variable2.
	 * 
	 * @param userVariable2
	 *            the user variable2
	 * @return the iDEAL request
	 */
	public IDealRequest setUserVariable2(String userVariable2) {
		this.userVariable2 = userVariable2;
		return this;
	}


	/**
	 * Gets the user variable3.
	 * 
	 * @return the user variable3
	 */
	public String getUserVariable3() {
		return userVariable3;
	}


	/**
	 * Sets the user variable3.
	 * 
	 * @param userVariable3
	 *            the user variable3
	 * @return the iDEAL request
	 */
	public IDealRequest setUserVariable3(String userVariable3) {
		this.userVariable3 = userVariable3;
		return this;
	}


	/**
	 * Gets the user variable4.
	 * 
	 * @return the user variable4
	 */
	public String getUserVariable4() {
		return userVariable4;
	}


	/**
	 * Sets the user variable4.
	 * 
	 * @param userVariable4
	 *            the user variable4
	 * @return the iDEAL request
	 */
	public IDealRequest setUserVariable4(String userVariable4) {
		this.userVariable4 = userVariable4;
		return this;
	}


	/**
	 * Gets the user variable5.
	 * 
	 * @return the user variable5
	 */
	public String getUserVariable5() {
		return userVariable5;
	}


	/**
	 * Sets the user variable5.
	 * 
	 * @param userVariable5
	 *            the user variable5
	 * @return the iDEAL request
	 */
	public IDealRequest setUserVariable5(String userVariable5) {
		this.userVariable5 = userVariable5;
		return this;
	}


	/**
	 * Gets the language id.
	 * 
	 * @return the language id
	 */
	public String getLanguageId() {
		return languageId;
	}


	/**
	 * Sets the language id.
	 * 
	 * @param languageId
	 *            the language id
	 * @return the iDEAL request
	 */
	public IDealRequest setLanguageId(String languageId) {
		this.languageId = languageId;
		return this;
	}


	/**
	 * Gets the interface timeout.
	 * 
	 * @return the interface timeout
	 */
	public long getInterfaceTimeout() {
		return interfaceTimeout;
	}


	/**
	 * Sets the interface timeout.
	 * 
	 * @param intrfaceTimeout
	 *            the intrface timeout
	 * @return the iDEAL request
	 */
	public IDealRequest setInterfaceTimeout(long intrfaceTimeout) {
		this.interfaceTimeout = intrfaceTimeout;
		return this;
	}


	/**
	 * Gets the interface version.
	 * 
	 * @return the interface version
	 */
	public String getInterfaceVersion() {
		return interfaceVersion;
	}


	/**
	 * Sets the interface version.
	 * 
	 * @param interfaceVersion
	 *            the interface version
	 * @return the iDEAL request
	 */
	public IDealRequest setInterfaceVersion(String interfaceVersion) {
		this.interfaceVersion = interfaceVersion;
		return this;
	}

}
