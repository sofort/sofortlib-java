package com.sofort.lib.core.internal.utils.xml;

/**
 * XML element renderer.
 * 
 * @param <T>
 *            the generic type
 */
public abstract class XmlElementRenderer<T> {

	/**
	 * Renders the given request to XML node.
	 * 
	 * @param request
	 *            request
	 * @param element
	 *            XML node
	 */
	public abstract void render(T request, XmlElementRenderable element);

}
