package com.sofort.lib.core.internal.transformer.renderer.parts;

import com.sofort.lib.core.internal.utils.Attribute;
import com.sofort.lib.core.internal.utils.xml.XmlElementRenderable;
import com.sofort.lib.core.internal.utils.xml.XmlElementRenderer;
import com.sofort.lib.core.products.request.parts.Notification;


/**
 * The rederer for {@link Notification}.
 */
public class NotificationRenderer extends XmlElementRenderer<Notification> {

    /*
     * (non-Javadoc)
     *
     * @see
     * com.sofort.lib.ideal.ideal.refund.refund.billcode.billcode.paycode.paycode.payment.payment.core.core.internal.utils.xml.XmlElementRenderer#render(java.lang
     * .Object, com.sofort.lib.ideal.ideal.refund.refund.billcode.billcode.paycode.paycode.payment.payment.core.core.internal.utils.xml.XmlElementRenderable)
     */
    @Override
    public void render(Notification notification, XmlElementRenderable element) {
        element.setContent(notification.getTarget());
        element.setAttribute(new Attribute("notify_on", notification.getNotifyOn()));
    }

}
