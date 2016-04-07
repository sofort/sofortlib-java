package com.sofort.lib.core.internal.transformer;

/**
 * A container for the raw response data.
 */
public class RawResponse {

	/**
	 * Response status enum.
	 */
	public static enum Status {
		OK,
		ERROR;
	}

	/** The response status. */
	private final Status status;

	/** The response content. */
	private final String content;

	/** The response duration time. */
	private final long responseTime;


	/**
	 * Defines the raw response.
	 * 
	 * @param status
	 *            response status
	 * @param content
	 *            response content
	 */
	public RawResponse(Status status, String content) {
		this(status, content, -1);
	}


	/**
	 * Defines the raw response.
	 * 
	 * @param status
	 *            response status
	 * @param content
	 *            response content
	 * @param responseTime
	 *            response duration time
	 */
	public RawResponse(Status status, String content, long responseTime) {
		this.status = status;
		this.content = content;
		this.responseTime = responseTime;
	}


	/**
	 * Gets the status.
	 * 
	 * @return response status
	 */
	public Status getStatus() {
		return status;
	}


	/**
	 * Gets the content.
	 * 
	 * @return response content
	 */
	public String getContent() {
		return content;
	}


	/**
	 * Gets the response time.
	 * 
	 * @return response duration time
	 */
	public long getResponseTime() {
		return responseTime;
	}

}
