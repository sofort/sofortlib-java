package com.sofort.lib.billcode.internal.transformer.renderer;

import com.sofort.lib.billcode.products.request.BillcodeRequest;
import com.sofort.lib.billcode.products.request.BillcodeStatusRequest;
import com.sofort.lib.core.internal.transformer.renderer.XmlRequestRenderer;
import com.sofort.lib.core.internal.utils.xml.XmlElementRenderable;
import com.sofort.lib.core.products.request.SofortLibRequest;


/**
 * The renderer for {@link BillcodeRequest}.
 */
public class BillcodeStatusRequestRenderer implements XmlRequestRenderer {

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
        render((BillcodeStatusRequest) request, element);
    }


    /**
     * Render the {@link BillcodeRequest} into given
     * {@link XmlElementRenderable} element.
     *
     * @param request the billcode request
     * @param element the renderable element
     */
    public void render(BillcodeStatusRequest request, XmlElementRenderable element) {
        element.append("billcode", request.getBillcode());
    }
}
