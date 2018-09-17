package com.sofort.lib.core.internal.utils.xml;

import com.sofort.lib.core.internal.utils.Attribute;


/**
 * The Class XmlDocumentRenderable.
 */
public class XmlDocumentRenderable {

    /**
     * The root.
     */
    private final XmlElementRenderable root;

    /**
     * The renderer.
     */
    private final XmlRendererHelper renderer;


    /**
     * Instantiates a new xml document renderable.
     *
     * @param name       the name
     * @param attributes the attributes
     * @throws XmlRendererHelperException the xml renderer helper exception
     */
    public XmlDocumentRenderable(String name, Attribute... attributes) throws XmlRendererHelperException {
        renderer = new XmlRendererHelper();
        root = new XmlElementRenderable(renderer.appendRootElement(name, attributes), renderer);
    }


    /**
     * Gets the root.
     *
     * @return the root
     */
    public XmlElementRenderable getRoot() {
        return root;
    }


    /**
     * Gets the xml.
     *
     * @return the xml
     * @throws XmlRendererHelperException the xml renderer helper exception
     */
    public String getXml() throws XmlRendererHelperException {
        return renderer.getXml();
    }

}
