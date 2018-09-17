package com.sofort.lib.refund.internal.transformer.xml;

import com.sofort.lib.core.internal.transformer.xml.XmlConfig;
import com.sofort.lib.core.internal.transformer.xml.XmlRootEntry;
import com.sofort.lib.core.internal.utils.Attribute;
import com.sofort.lib.refund.internal.transformer.parser.RefundResponseParser;
import com.sofort.lib.refund.internal.transformer.renderer.RefundRequestRenderer;
import com.sofort.lib.refund.products.request.RefundRequest;
import com.sofort.lib.refund.products.response.RefundResponse;


/**
 * The XML root/parser/renderer definition for SOFORT Refund request and
 * response.
 */
public class XmlConfigRefund extends XmlConfig {

    /*
     * (non-Javadoc)
     *
     * @see
     * com.sofort.lib.ideal.ideal.refund.refund.billcode.billcode.paycode.paycode.payment.payment.core.core.internal.transformer.xml.XmlConfig#initRootEntryMapping()
     */
    @Override
    protected void initRootEntryMapping() {
        rootEntryMapping.put(RefundRequest.class, new XmlRootEntry("refunds", new Attribute("version", "3.0")));
    }


    /*
     * (non-Javadoc)
     *
     * @see
     * com.sofort.lib.ideal.ideal.refund.refund.billcode.billcode.paycode.paycode.payment.payment.core.core.internal.transformer.xml.XmlConfig#initRendererMapping()
     */
    @Override
    protected void initRendererMapping() {
        rendererMapping.put(RefundRequest.class, new RefundRequestRenderer());
    }


    /*
     * (non-Javadoc)
     *
     * @see
     * com.sofort.lib.ideal.ideal.refund.refund.billcode.billcode.paycode.paycode.payment.payment.core.core.internal.transformer.xml.XmlConfig#initParserMapping()
     */
    @Override
    protected void initParserMapping() {
        parserMapping.put(RefundResponse.class, new RefundResponseParser());
    }

}
