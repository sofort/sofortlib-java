package com.sofort.lib.payment.internal.transformer.xml;

import com.sofort.lib.core.internal.transformer.xml.XmlConfig;
import com.sofort.lib.core.internal.transformer.xml.XmlRootEntry;
import com.sofort.lib.core.internal.utils.Attribute;
import com.sofort.lib.payment.internal.transformer.parser.PaymentResponseParser;
import com.sofort.lib.payment.internal.transformer.parser.PaymentTransactionDetailsResponseParser;
import com.sofort.lib.payment.internal.transformer.renderer.PaymentRequestRenderer;
import com.sofort.lib.payment.internal.transformer.renderer.PaymentTransactionDetailsRequestRenderer;
import com.sofort.lib.payment.products.request.PaymentRequest;
import com.sofort.lib.payment.products.request.PaymentTransactionDetailsRequest;
import com.sofort.lib.payment.products.response.PaymentResponse;
import com.sofort.lib.payment.products.response.PaymentTransactionDetailsResponse;


/**
 * The XML root/parser/renderer definition for SOFORT Payment (SOFORT
 * Überweisung) requests and responses.
 */
public class XmlConfigPayment extends XmlConfig {

    /*
     * (non-Javadoc)
     *
     * @see
     * com.sofort.lib.ideal.ideal.refund.refund.billcode.billcode.paycode.paycode.payment.payment.core.core.internal.transformer.xml.XmlConfig#initRootEntryMapping()
     */
    @Override
    protected void initRootEntryMapping() {
        rootEntryMapping.put(PaymentRequest.class, new XmlRootEntry("multipay", new Attribute("version", "1.0")));
        rootEntryMapping.put(PaymentTransactionDetailsRequest.class, new XmlRootEntry("transaction_request", new Attribute("version", "2.0")));
    }


    /*
     * (non-Javadoc)
     *
     * @see
     * com.sofort.lib.ideal.ideal.refund.refund.billcode.billcode.paycode.paycode.payment.payment.core.core.internal.transformer.xml.XmlConfig#initRendererMapping()
     */
    @Override
    protected void initRendererMapping() {
        rendererMapping.put(PaymentRequest.class, new PaymentRequestRenderer());
        rendererMapping.put(PaymentTransactionDetailsRequest.class, new PaymentTransactionDetailsRequestRenderer());
    }


    /*
     * (non-Javadoc)
     *
     * @see
     * com.sofort.lib.ideal.ideal.refund.refund.billcode.billcode.paycode.paycode.payment.payment.core.core.internal.transformer.xml.XmlConfig#initParserMapping()
     */
    @Override
    protected void initParserMapping() {
        parserMapping.put(PaymentResponse.class, new PaymentResponseParser());
        parserMapping.put(PaymentTransactionDetailsResponse.class, new PaymentTransactionDetailsResponseParser());
    }

}
