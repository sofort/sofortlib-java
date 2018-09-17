package com.sofort.lib.ideal.internal.transformer.renderer;

import com.sofort.lib.core.internal.transformer.renderer.XmlRequestRenderer;
import com.sofort.lib.core.internal.utils.xml.XmlElementRenderable;
import com.sofort.lib.core.products.request.SofortLibRequest;
import com.sofort.lib.ideal.products.request.IDealBanksRequest;


/**
 * The renderer for {@link IDealBanksRequest}.
 */
public class IDealBanksRequestRenderer implements XmlRequestRenderer {

    /*
     * (non-Javadoc)
     *
     * @see
     * com.sofort.lib.ideal.ideal.refund.refund.billcode.billcode.paycode.paycode.payment.payment.core.core.internal.transformer.renderer.XmlRequestRenderer#render
     * (com.sofort.lib.ideal.ideal.refund.refund.billcode.billcode.paycode.paycode.payment.payment.core.core.products.request.SofortLibRequest,
     * com.sofort.lib.ideal.ideal.refund.refund.billcode.billcode.paycode.paycode.payment.payment.core.core.internal.utils.xml.XmlElementRenderable)
     */
    @Override
    public void render(SofortLibRequest request, XmlElementRenderable element) {
        /* noOp */
    }
}
