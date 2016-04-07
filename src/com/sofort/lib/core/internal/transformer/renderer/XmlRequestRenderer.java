package com.sofort.lib.core.internal.transformer.renderer;

import com.sofort.lib.core.internal.utils.xml.XmlElementRenderable;
import com.sofort.lib.core.products.request.SofortLibRequest;


/**
 * Definition of a SofortLib request to XML renderer.
 */
public interface XmlRequestRenderer {

	/**
	 * Converts the given SofortLib request to the XML structure added to the
	 * given XML Node.
	 * 
	 * @param request
	 *            SofortLib request to render into given XML node
	 * @param element
	 *            a XML node used to integrate the rendered XML structure
	 *            defined for given SofortLib request
	 */
	public void render(SofortLibRequest request, XmlElementRenderable element);

}
