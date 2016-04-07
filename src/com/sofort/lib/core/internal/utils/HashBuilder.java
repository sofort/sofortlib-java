package com.sofort.lib.core.internal.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * Hash builder helper.
 */
public class HashBuilder {

	private final String txt;


	/**
	 * Defines the hash builder helper with the given text content.
	 * 
	 * @param txt
	 *            the text hash value will be generated for
	 */
	public HashBuilder(final String txt) {
		this.txt = txt;
	}


	/**
	 * Gets the md5.
	 * 
	 * @return the MD5 hash value for defined text content
	 */
	public String getMd5() {
		return getHashCode(HashAlgorithm.MD5);
	}


	/**
	 * Gets the sha1.
	 * 
	 * @return the SHA-1 hash value for defined text content
	 */
	public String getSha1() {
		return getHashCode(HashAlgorithm.SHA1);
	}


	/**
	 * Gets the sha256.
	 * 
	 * @return the SHA-256 hash value for defined text content
	 */
	public String getSha256() {
		return getHashCode(HashAlgorithm.SHA256);
	}


	/**
	 * Gets the sha512.
	 * 
	 * @return the SHA-512 hash value for defined text content
	 */
	public String getSha512() {
		return getHashCode(HashAlgorithm.SHA512);
	}


	/**
	 * Calculates the hash code with the given hash algorithm.
	 * 
	 * @param algorithm
	 *            the algorithm
	 * @return the hash code
	 */
	public String getHashCode(HashAlgorithm algorithm) {

		if (txt == null) {
			return null;
		}

		MessageDigest md = getMessageDigest(algorithm.algorithm());
		md.update(txt.getBytes());
		byte[] hashBytes = md.digest();

		return toHexString(hashBytes);
	}


	/**
	 * Gets the message digest.
	 * 
	 * @param algorithm
	 *            the algorithm
	 * @return the message digest for the given hash algorithm
	 */
	protected static MessageDigest getMessageDigest(String algorithm) {
		try {
			return MessageDigest.getInstance(algorithm);
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException("No such algorithm " + algorithm + ". " + e.getMessage());
		}
	}


	/**
	 * Convert to hex string.
	 * 
	 * @param hashBytes
	 *            the hash bytes
	 * @return the string
	 */
	private static String toHexString(byte[] hashBytes) {

		final StringBuilder sb = new StringBuilder();
		for (final byte b : hashBytes) {

			final String hexString = Integer.toHexString(b & 0x000000FF);
			if (hexString.length() < 2) {
				sb.append('0');
			}
			sb.append(hexString);
		}

		return sb.toString();
	}

}
