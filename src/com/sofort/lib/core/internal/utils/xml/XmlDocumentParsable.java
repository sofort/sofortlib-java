package com.sofort.lib.core.internal.utils.xml;

/**
 * The Class XmlDocumentParsable.
 */
public class XmlDocumentParsable {

    /**
     * The root.
     */
    private final XmlElementParsable root;

    /**
     * The parser.
     */
    private final XmlParserHelper parser;


    /**
     * Instantiates a new xml document parsable.
     *
     * @param xml the xml
     * @throws XmlParserHelperException the xml parser helper exception
     */
    public XmlDocumentParsable(String xml) throws XmlParserHelperException {
        parser = new XmlParserHelper();
        root = new XmlElementParsable(parser.parseXml(xml).getDocumentElement(), parser);
    }


    /**
     * Gets the root.
     *
     * @return the root
     */
    public XmlElementParsable getRoot() {
        return root;
    }


    /**
     * Verify.
     *
     * @throws XmlVerifierException the xml verifier exception
     */
    public void verify() throws XmlVerifierException {
        parser.verify();
    }

}
