package com.sofort.lib.core.internal.transformer.xml;

import com.sofort.lib.core.internal.utils.Attribute;


/**
 * XML root entry with name and attributes.
 */
public class XmlRootEntry {

	/** The name. */
	private final String name;

	/** The attributes. */
	private final Attribute[] attributes;


	/**
	 * Defines a XML root entry.
	 * 
	 * @param name
	 *            tag name of the XML root entry.
	 * @param attributes
	 *            the XML element attributes.
	 */
	public XmlRootEntry(String name, Attribute... attributes) {
		this.name = name;
		this.attributes = attributes;
	}


	/**
	 * Gets the name of the XML root entry.
	 * 
	 * @return the tag name of the XML root entry.
	 */
	public String getName() {
		return name;
	}


	/**
	 * Gets the XML root entry attributes.
	 * 
	 * @return the XML root entry attributes.
	 */
	public Attribute[] getAttributes() {
		return attributes;
	}

}
