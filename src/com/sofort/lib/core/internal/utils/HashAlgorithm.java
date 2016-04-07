package com.sofort.lib.core.internal.utils;

/**
 * Definition of hash algorithms.
 */
public enum HashAlgorithm {

	MD5("md5"),
	SHA1("sha-1"),
	SHA256("sha-256"),
	SHA512("sha-512");

	/** The algorithm. */
	private final String algorithm;


	/**
	 * Instantiates a new hash algorithm.
	 * 
	 * @param algorithm
	 *            the algorithm
	 */
	private HashAlgorithm(String algorithm) {
		this.algorithm = algorithm;
	}


	/**
	 * Algorithm name.
	 * 
	 * @return the string
	 */
	public String algorithm() {
		return algorithm;
	}


	/**
	 * Gets the algorithm for given name.
	 * 
	 * @param algorithm
	 *            the algorithm
	 * @return the hash algorithm
	 */
	public static HashAlgorithm get(String algorithm) {
		final String normalized = algorithm.toUpperCase().replaceAll("[\\-_\\s]", "");
		for (HashAlgorithm ha : values()) {
			if (ha.name().equals(normalized)) {
				return ha;
			}
		}

		throw new IllegalArgumentException("Unknown algorithm: " + algorithm);
	}
}
