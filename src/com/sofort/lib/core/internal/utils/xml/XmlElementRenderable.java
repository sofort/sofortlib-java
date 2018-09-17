package com.sofort.lib.core.internal.utils.xml;

import com.sofort.lib.core.internal.utils.Attribute;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.util.Date;
import java.util.List;


/**
 * The Class XmlElementRenderable.
 */
public class XmlElementRenderable {

    /**
     * The element.
     */
    private final Element element;

    /**
     * The renderer.
     */
    private final XmlRendererHelper renderer;


    /**
     * Instantiates a new xml element renderable.
     *
     * @param element  the element
     * @param renderer the renderer
     */
    public XmlElementRenderable(Element element, XmlRendererHelper renderer) {
        this.element = element;
        this.renderer = renderer;
    }


    /**
     * Instantiates a new xml element renderable.
     *
     * @param name       the name
     * @param attributes the attributes
     * @throws XmlRendererHelperException the xml renderer helper exception
     */
    public XmlElementRenderable(String name, Attribute[] attributes) throws XmlRendererHelperException {
        renderer = new XmlRendererHelper();
        element = renderer.appendRootElement(name, attributes);
    }


    /**
     * Sets the content.
     *
     * @param value the new content
     */
    public void setContent(String value) {
        renderer.setContent(element, value);
    }


    /**
     * Sets the attribute.
     *
     * @param attribute the new attribute
     */
    public void setAttribute(Attribute attribute) {
        renderer.setAttribute(element, attribute);
    }


    /**
     * Append the given value with given tag name.
     *
     * @param childName the child name
     * @param value     the value
     */
    public void append(String childName, String value) {
        renderer.append(element, renderer.createElement(childName, value));
    }


    /**
     * Append the given value with given tag name.
     *
     * @param childName the child name
     * @param value     the value
     */
    public void append(String childName, Number value) {
        renderer.append(element, renderer.createElement(childName, value));
    }


    /**
     * Append the given value with given tag name.
     *
     * @param childName    the child name
     * @param value        the value
     * @param invalidValue the invalid value
     */
    public void append(String childName, Number value, Number invalidValue) {
        renderer.append(element, renderer.createElement(childName, value, invalidValue));
    }


    /**
     * Append the given value with given tag name.
     *
     * @param childName the child name
     * @param value     the value
     */
    public void append(String childName, Date value) {
        renderer.append(element, renderer.createElement(childName, value));
    }


    /**
     * Append the given value with given tag name and the given date format.
     *
     * @param childName the child name
     * @param value     the value
     * @param format    date format
     */
    public void append(String childName, Date value, String format) {
        renderer.append(element, renderer.createElement(childName, value, format));
    }


    /**
     * Append the given value with given tag name.
     *
     * @param childName the child name
     * @param value     the value
     */
    public void append(String childName, Boolean value) {
        renderer.append(element, renderer.createElement(childName, value));
    }


    /**
     * Append the given value with given tag name.
     *
     * @param parentName the parent name
     * @param childName  the child name
     * @param list       the list
     */
    public void append(String parentName, String childName, List<String> list) {
        renderer.append(element, renderer.createElement(parentName, childName, list));
    }


    /**
     * Append the given value with given tag name.
     *
     * @param childName the child name
     * @return the xml element renderable
     */
    public XmlElementRenderable append(String childName) {
        Element child = renderer.createElement(childName);
        renderer.append(element, child);

        return new XmlElementRenderable(child, renderer);
    }


    /**
     * Returns the internal/wrapped node.
     *
     * @return the internal/wrapped node
     */
    protected Node getNode() {
        return element;
    }

}
