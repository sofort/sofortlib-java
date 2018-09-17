package com.sofort.lib.billcode.internal.transformer.xml;

import com.sofort.lib.billcode.internal.transformer.parser.BillcodeResponseParser;
import com.sofort.lib.billcode.internal.transformer.parser.BillcodeStatusResponseParser;
import com.sofort.lib.billcode.internal.transformer.parser.BillcodeTransactionDetailsResponseParser;
import com.sofort.lib.billcode.internal.transformer.renderer.BillcodeRequestRenderer;
import com.sofort.lib.billcode.internal.transformer.renderer.BillcodeStatusRequestRenderer;
import com.sofort.lib.billcode.internal.transformer.renderer.BillcodeTransactionDetailsRequestRenderer;
import com.sofort.lib.billcode.products.request.BillcodeRequest;
import com.sofort.lib.billcode.products.request.BillcodeStatusRequest;
import com.sofort.lib.billcode.products.request.BillcodeTransactionDetailsRequest;
import com.sofort.lib.billcode.products.response.BillcodeResponse;
import com.sofort.lib.billcode.products.response.BillcodeStatusResponse;
import com.sofort.lib.billcode.products.response.BillcodeTransactionDetailsResponse;
import com.sofort.lib.core.internal.transformer.xml.XmlConfig;
import com.sofort.lib.core.internal.transformer.xml.XmlRootEntry;
import com.sofort.lib.core.internal.utils.Attribute;


/**
 * The XML root/parser/renderer definition for SOFORT Billcode requests and
 * responses.
 */
public class XmlConfigBillcode extends XmlConfig {

    /*
     * (non-Javadoc)
     *
     * @see
     * com.sofort.lib.ideal.ideal.refund.refund.billcode.billcode.paycode.paycode.payment.payment.core.core.internal.transformer.xml.XmlConfig#initRootEntryMapping()
     */
    @Override
    protected void initRootEntryMapping() {
        rootEntryMapping.put(BillcodeRequest.class, new XmlRootEntry("billcode"));
        rootEntryMapping.put(BillcodeStatusRequest.class, new XmlRootEntry("billcode_request"));
        rootEntryMapping.put(BillcodeTransactionDetailsRequest.class, new XmlRootEntry("transaction_request", new Attribute("version", "2.0")));
    }


    /*
     * (non-Javadoc)
     *
     * @see
     * com.sofort.lib.ideal.ideal.refund.refund.billcode.billcode.paycode.paycode.payment.payment.core.core.internal.transformer.xml.XmlConfig#initRendererMapping()
     */
    @Override
    protected void initRendererMapping() {
        rendererMapping.put(BillcodeRequest.class, new BillcodeRequestRenderer());
        rendererMapping.put(BillcodeStatusRequest.class, new BillcodeStatusRequestRenderer());
        rendererMapping.put(BillcodeTransactionDetailsRequest.class, new BillcodeTransactionDetailsRequestRenderer());
    }


    /*
     * (non-Javadoc)
     *
     * @see
     * com.sofort.lib.ideal.ideal.refund.refund.billcode.billcode.paycode.paycode.payment.payment.core.core.internal.transformer.xml.XmlConfig#initParserMapping()
     */
    @Override
    protected void initParserMapping() {
        parserMapping.put(BillcodeResponse.class, new BillcodeResponseParser());
        parserMapping.put(BillcodeStatusResponse.class, new BillcodeStatusResponseParser());
        parserMapping.put(BillcodeTransactionDetailsResponse.class, new BillcodeTransactionDetailsResponseParser());
    }

}
