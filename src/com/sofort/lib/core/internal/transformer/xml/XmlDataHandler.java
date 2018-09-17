package com.sofort.lib.core.internal.transformer.xml;

import com.sofort.lib.core.internal.transformer.DataHandler;
import com.sofort.lib.core.internal.transformer.DataHandlerException;
import com.sofort.lib.core.internal.transformer.RawRequest;
import com.sofort.lib.core.internal.transformer.RawResponse;
import com.sofort.lib.core.internal.utils.xml.*;
import com.sofort.lib.core.products.request.SofortLibRequest;
import com.sofort.lib.core.products.response.SofortLibResponse;

import static com.sofort.lib.core.Logger.log;


/**
 * A default XML implementation of the data handler for current SofortLib API.
 */
public class XmlDataHandler implements DataHandler {

    private final XmlConfig config;


    /**
     * Defines the XML data handler with the given configuration.
     *
     * @param config XML configuration
     */
    public XmlDataHandler(XmlConfig config) {
        this.config = config;
    }


    /*
     * (non-Javadoc)
     *
     * @see
     * com.sofort.lib.ideal.ideal.refund.refund.billcode.billcode.paycode.paycode.payment.payment.core.core.internal.transformer.DataHandler#render(com.sofort.lib.ideal.ideal.refund.refund.billcode.billcode.paycode.paycode.payment.payment.core.core
     * .products.request.SofortLibRequest)
     */
    @Override
    public RawRequest render(SofortLibRequest dataRequest) {
        log.debug("Start the rendering of " + dataRequest.getClass().getName());

        log.debug("Fetch the config entry for request.");
        XmlRootEntry configEntry = config.getConfigEntry(dataRequest.getClass());

        final String content;
        if (configEntry.getName() == null) {
            log.debug("No data to render (no root node name configured).");
            content = "";

        } else {
            try {
                log.debug("Create the root node with configured name and attributes.");
                XmlDocumentRenderable document = new XmlDocumentRenderable(configEntry.getName(), configEntry.getAttributes());

                log.debug("Fetch the configured renderer and render the request as XML structure.");
                config.getRequestRenderer(dataRequest.getClass()).render(dataRequest, document.getRoot());
                content = document.getXml();

            } catch (XmlRendererHelperException e) {
                throw new DataHandlerException("Could not render the request: " + dataRequest.getClass().getName(), e);
            }
        }

        log.debug("Rendering is DONE.");
        return new RawRequest(content);
    }


    /*
     * (non-Javadoc)
     *
     * @see
     * com.sofort.lib.ideal.ideal.refund.refund.billcode.billcode.paycode.paycode.payment.payment.core.core.internal.transformer.DataHandler#parse(com.sofort.lib.ideal.ideal.refund.refund.billcode.billcode.paycode.paycode.payment.payment.core.core.
     * internal.transformer.RawResponse, java.lang.Class)
     */
    @Override
    public SofortLibResponse parse(RawResponse rawResponse, Class<? extends SofortLibResponse> responseClass) {
        try {
            log.debug("Parse the response as XML structure.");
            XmlElementParsable rootNode = new XmlDocumentParsable(rawResponse.getContent()).getRoot();

            log.debug("Parse the XML structure as the data structure.");
            SofortLibResponse response = config.getResponseParser(responseClass).parse(rootNode);

            log.debug("Parsing is DONE.");
            return response;

        } catch (XmlParserHelperException e) {
            throw new DataHandlerException("Could not parse the respone XML: " + rawResponse.getContent(), e);
        }
    }
}
