package com.sofort.lib.core.internal.utils.xml;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import static com.sofort.lib.core.Logger.log;

/**
 * XML verifier.
 */
public class XmlVerifier {

    /**
     * The map.
     */
    private final Map<String, Integer> map;


    /**
     * Defines the XML verifier.
     */
    public XmlVerifier() {
        map = new HashMap<String, Integer>();
    }


    /**
     * Initialize the XML verifier with the given parent node. XML verifier
     * traverse the tree and stores the paths of all found leaf nodes.
     *
     * @param parent parent element
     */
    public void init(Node parent) {
        collectNodes(parent);
    }


    /**
     * Collect nodes.
     *
     * @param parent the parent
     */
    private void collectNodes(Node parent) {
        if (parent == null) {
            return;
        }

        List<Node> list = getChildNodes(parent.getChildNodes());
        if (list == null || list.isEmpty()) {
            String path = getPath(parent, "");
            addMapEntry(path);
            return;
        }

        for (Node child : list) {
            collectNodes(child);
        }
    }


    /**
     * Remove the path for the given leaf node.
     *
     * @param node the node
     */
    public void removePath(Node node) {
        String path = getPath(node, "");

        Integer count = map.get(path);
        if (count == null) {
            log.debug("No path for given node found: " + node);
            return;
        }

        count = Integer.valueOf(count.intValue() - 1);
        if (count.intValue() > 0) {
            map.put(path, count);
        } else {
            log.debug("Remove path: " + path);
            map.remove(path);
        }
    }


    /**
     * Checks if any of collected paths of leaf nodes were not removed.
     *
     * @throws XmlVerifierException thrown if any paths weren't removed
     */
    public void verify() throws XmlVerifierException {
        StringBuilder sb = new StringBuilder();

        for (Entry<String, Integer> entry : map.entrySet()) {
            sb.append(entry.getKey()).append(" -> ").append(entry.getValue()).append(" entr" + (entry.getValue() > 1 ? "ies" : "y") + "\n");
        }

        if (sb.length() != 0) {
            throw new XmlVerifierException("Some collected pathes of leaf nodes were not removed:\n" + sb);
        }
    }


    /**
     * Gets the child nodes.
     *
     * @param list the list
     * @return the child nodes
     */
    private static List<Node> getChildNodes(NodeList list) {

        if (list == null) {
            return null;
        }

        List<Node> result = new ArrayList<Node>();
        for (int i = 0; i < list.getLength(); i++) {

            Node child = list.item(i);
            if (child.getNodeType() == Node.ELEMENT_NODE) {
                result.add(child);
            }
        }

        return result;
    }


    /**
     * Adds the map entry.
     *
     * @param path the path
     */
    private void addMapEntry(String path) {
        Integer count = map.get(path);
        if (count == null) {
            log.debug("Add new: " + path);
            count = Integer.valueOf(1);
        } else {
            log.debug(" Update: " + path);
            count = Integer.valueOf(count.intValue() + 1);
        }

        map.put(path, count);
    }


    /**
     * Gets the path.
     *
     * @param leaf   the leaf
     * @param prefix the prefix
     * @return the path
     */
    private String getPath(Node leaf, String prefix) {

        if (leaf == null) {
            return prefix;
        }

        Node parent = leaf.getParentNode();
        if (parent == null || parent.getNodeType() == Node.DOCUMENT_NODE) {
            return '<' + leaf.getNodeName() + '>' + prefix;
        }

        return getPath(parent, '<' + leaf.getNodeName() + '>' + prefix);
    }

}
