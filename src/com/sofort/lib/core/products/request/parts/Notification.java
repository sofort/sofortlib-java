package com.sofort.lib.core.products.request.parts;

/**
 * The API notification container.
 */
public class Notification {

	/** The target. */
	private final String target;

	/** The notify on. */
	private String notifyOn;


	/**
	 * Instantiates a new notification.
	 * 
	 * @param target
	 *            the target
	 */
	public Notification(String target) {
		this.target = target;
	}


	/**
	 * Gets the target.
	 * 
	 * @return the target
	 */
	public String getTarget() {
		return target;
	}


	/**
	 * Gets the notify on.
	 * 
	 * @return the notify on
	 */
	public String getNotifyOn() {
		return notifyOn;
	}


	/**
	 * Sets the notify on (read the API documentation to find out the possible
	 * values).
	 * 
	 * @param notifyOn
	 *            the notify on
	 * @return the notification
	 */
	public Notification setNotifyOn(String notifyOn) {
		this.notifyOn = notifyOn;
		return this;
	}

}
