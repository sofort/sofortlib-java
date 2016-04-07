package com.sofort.lib.ideal.products.response;

/**
 * The API IDeal notification response container.
 */
public class IDealNotificationResponse {

	/** The trans id. */
	private String transId;

	/** The user id. */
	private int userId;

	/** The project id. */
	private int projectId;

	/** The sender holder. */
	private String senderHolder;

	/** The sender account number. */
	private String senderAccountNumber;

	/** The sender bank name. */
	private String senderBankName;

	/** The sender bank bic. */
	private String senderBankBic;

	/** The sender iban. */
	private String senderIban;

	/** The sender country id. */
	private String senderCountryId;

	/** The recepient holder. */
	private String recepientHolder;

	/** The recepient account number. */
	private String recepientAccountNumber;

	/** The recepient bank code. */
	private String recepientBankCode;

	/** The recepient bank name. */
	private String recepientBankName;

	/** The recepient bank bic. */
	private String recepientBankBic;

	/** The recepient iban. */
	private String recepientIban;

	/** The recepient country id. */
	private String recepientCountryId;

	/** The amount. */
	private double amount;

	/** The currency id. */
	private String currencyId;

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

	/** The created. */
	private String created;

	/** The status. */
	private String status;

	/** The status. */
	private String statusReason;

	/** The status modified. */
	private String statusModified;

	/** The hash. */
	private String hash;


	/**
	 * Gets the trans id.
	 * 
	 * @return the trans id
	 */
	public String getTransId() {
		return transId;
	}


	/**
	 * Sets the trans id.
	 * 
	 * @param transId
	 *            the trans id
	 * @return the iDEAL notification response
	 */
	public IDealNotificationResponse setTransId(String transId) {
		this.transId = transId;
		return this;
	}


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
	 * @return the iDEAL notification response
	 */
	public IDealNotificationResponse setUserId(int userId) {
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
	 * @return the iDEAL notification response
	 */
	public IDealNotificationResponse setProjectId(int projectId) {
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
	 * @return the iDEAL notification response
	 */
	public IDealNotificationResponse setSenderHolder(String senderHolder) {
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
	 * @return the iDEAL notification response
	 */
	public IDealNotificationResponse setSenderAccountNumber(String senderAccountNumber) {
		this.senderAccountNumber = senderAccountNumber;
		return this;
	}


	/**
	 * Gets the sender bank name.
	 * 
	 * @return the sender bank name
	 */
	public String getSenderBankName() {
		return senderBankName;
	}


	/**
	 * Sets the sender bank name.
	 * 
	 * @param senderBankName
	 *            the sender bank name
	 * @return the iDEAL notification response
	 */
	public IDealNotificationResponse setSenderBankName(String senderBankName) {
		this.senderBankName = senderBankName;
		return this;
	}


	/**
	 * Gets the sender bank bic.
	 * 
	 * @return the sender bank bic
	 */
	public String getSenderBankBic() {
		return senderBankBic;
	}


	/**
	 * Sets the sender bank bic.
	 * 
	 * @param senderBankBic
	 *            the sender bank bic
	 * @return the iDEAL notification response
	 */
	public IDealNotificationResponse setSenderBankBic(String senderBankBic) {
		this.senderBankBic = senderBankBic;
		return this;
	}


	/**
	 * Gets the sender iban.
	 * 
	 * @return the sender iban
	 */
	public String getSenderIban() {
		return senderIban;
	}


	/**
	 * Sets the sender iban.
	 * 
	 * @param senderIban
	 *            the sender iban
	 * @return the iDEAL notification response
	 */
	public IDealNotificationResponse setSenderIban(String senderIban) {
		this.senderIban = senderIban;
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
	 * @return the iDEAL notification response
	 */
	public IDealNotificationResponse setSenderCountryId(String senderCountryId) {
		this.senderCountryId = senderCountryId;
		return this;
	}


	/**
	 * Gets the recepient holder.
	 * 
	 * @return the recepient holder
	 */
	public String getRecepientHolder() {
		return recepientHolder;
	}


	/**
	 * Sets the recepient holder.
	 * 
	 * @param recepientHolder
	 *            the recepient holder
	 * @return the iDEAL notification response
	 */
	public IDealNotificationResponse setRecepientHolder(String recepientHolder) {
		this.recepientHolder = recepientHolder;
		return this;
	}


	/**
	 * Gets the recepient account number.
	 * 
	 * @return the recepient account number
	 */
	public String getRecepientAccountNumber() {
		return recepientAccountNumber;
	}


	/**
	 * Sets the recepient account number.
	 * 
	 * @param recepientAccountNumber
	 *            the recepient account number
	 * @return the iDEAL notification response
	 */
	public IDealNotificationResponse setRecepientAccountNumber(String recepientAccountNumber) {
		this.recepientAccountNumber = recepientAccountNumber;
		return this;
	}


	/**
	 * Gets the recepient bank code.
	 * 
	 * @return the recepient bank code
	 */
	public String getRecepientBankCode() {
		return recepientBankCode;
	}


	/**
	 * Sets the recepient bank code.
	 * 
	 * @param recepientBankCode
	 *            the recepient bank code
	 * @return the iDEAL notification response
	 */
	public IDealNotificationResponse setRecepientBankCode(String recepientBankCode) {
		this.recepientBankCode = recepientBankCode;
		return this;
	}


	/**
	 * Gets the recepient bank name.
	 * 
	 * @return the recepient bank name
	 */
	public String getRecepientBankName() {
		return recepientBankName;
	}


	/**
	 * Sets the recepient bank name.
	 * 
	 * @param recepientBankName
	 *            the recepient bank name
	 * @return the iDEAL notification response
	 */
	public IDealNotificationResponse setRecepientBankName(String recepientBankName) {
		this.recepientBankName = recepientBankName;
		return this;
	}


	/**
	 * Gets the recepient bank bic.
	 * 
	 * @return the recepient bank bic
	 */
	public String getRecepientBankBic() {
		return recepientBankBic;
	}


	/**
	 * Sets the recepient bank bic.
	 * 
	 * @param recepientBankBic
	 *            the recepient bank bic
	 * @return the iDEAL notification response
	 */
	public IDealNotificationResponse setRecepientBankBic(String recepientBankBic) {
		this.recepientBankBic = recepientBankBic;
		return this;
	}


	/**
	 * Gets the recepient iban.
	 * 
	 * @return the recepient iban
	 */
	public String getRecepientIban() {
		return recepientIban;
	}


	/**
	 * Sets the recepient iban.
	 * 
	 * @param recepientIban
	 *            the recepient iban
	 * @return the iDEAL notification response
	 */
	public IDealNotificationResponse setRecepientIban(String recepientIban) {
		this.recepientIban = recepientIban;
		return this;
	}


	/**
	 * Gets the recepient country id.
	 * 
	 * @return the recepient country id
	 */
	public String getRecepientCountryId() {
		return recepientCountryId;
	}


	/**
	 * Sets the recepient country id.
	 * 
	 * @param recepientCountryId
	 *            the recepient country id
	 * @return the iDEAL notification response
	 */
	public IDealNotificationResponse setRecepientCountryId(String recepientCountryId) {
		this.recepientCountryId = recepientCountryId;
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
	 * @return the iDEAL notification response
	 */
	public IDealNotificationResponse setAmount(double amount) {
		this.amount = amount;
		return this;
	}


	/**
	 * Gets the currency id.
	 * 
	 * @return the currency id
	 */
	public String getCurrencyId() {
		return currencyId;
	}


	/**
	 * Sets the currency id.
	 * 
	 * @param currencyId
	 *            the currency id
	 * @return the iDEAL notification response
	 */
	public IDealNotificationResponse setCurrencyId(String currencyId) {
		this.currencyId = currencyId;
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
	 * @return the iDEAL notification response
	 */
	public IDealNotificationResponse setReason1(String reason1) {
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
	 * @return the iDEAL notification response
	 */
	public IDealNotificationResponse setReason2(String reason2) {
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
	 * @return the iDEAL notification response
	 */
	public IDealNotificationResponse setUserVariable0(String userVariable0) {
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
	 * @return the iDEAL notification response
	 */
	public IDealNotificationResponse setUserVariable1(String userVariable1) {
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
	 * @return the iDEAL notification response
	 */
	public IDealNotificationResponse setUserVariable2(String userVariable2) {
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
	 * @return the iDEAL notification response
	 */
	public IDealNotificationResponse setUserVariable3(String userVariable3) {
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
	 * @return the iDEAL notification response
	 */
	public IDealNotificationResponse setUserVariable4(String userVariable4) {
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
	 * @return the iDEAL notification response
	 */
	public IDealNotificationResponse setUserVariable5(String userVariable5) {
		this.userVariable5 = userVariable5;
		return this;
	}


	/**
	 * Gets the created.
	 * 
	 * @return the created
	 */
	public String getCreated() {
		return created;
	}


	/**
	 * Sets the created.
	 * 
	 * @param created
	 *            the created
	 * @return the iDEAL notification response
	 */
	public IDealNotificationResponse setCreated(String created) {
		this.created = created;
		return this;
	}


	/**
	 * Gets the status.
	 * 
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}


	/**
	 * Sets the status.
	 * 
	 * @param status
	 *            the status
	 * @return the iDEAL notification response
	 */
	public IDealNotificationResponse setStatus(String status) {
		this.status = status;
		return this;
	}


	/**
	 * Gets the status reason.
	 * 
	 * @return the status reason
	 */
	public String getStatusReason() {
		return statusReason;
	}


	/**
	 * Sets the status reason.
	 * 
	 * @param statusReason
	 *            the status reason to set
	 * @return the iDEAL notification response
	 */
	public IDealNotificationResponse setStatusReason(String statusReason) {
		this.statusReason = statusReason;
		return this;
	}


	/**
	 * Gets the status modified.
	 * 
	 * @return the status modified
	 */
	public String getStatusModified() {
		return statusModified;
	}


	/**
	 * Sets the status modified.
	 * 
	 * @param statusModified
	 *            the status modified
	 * @return the iDEAL notification response
	 */
	public IDealNotificationResponse setStatusModified(String statusModified) {
		this.statusModified = statusModified;
		return this;
	}


	/**
	 * Gets the hash.
	 * 
	 * @return the hash
	 */
	public String getHash() {
		return hash;
	}


	/**
	 * Sets the hash.
	 * 
	 * @param hash
	 *            the hash
	 * @return the iDEAL notification response
	 */
	public IDealNotificationResponse setHash(String hash) {
		this.hash = hash;
		return this;
	}

}
