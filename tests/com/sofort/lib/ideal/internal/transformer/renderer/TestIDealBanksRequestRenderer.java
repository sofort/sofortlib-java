package com.sofort.lib.ideal.internal.transformer.renderer;

import static org.testng.AssertJUnit.assertEquals;

import org.testng.annotations.Test;

import com.sofort.lib.core.internal.utils.xml.XmlDocumentRenderable;
import com.sofort.lib.core.internal.utils.xml.XmlRendererHelperException;
import com.sofort.lib.ideal.internal.transformer.renderer.IDealBanksRequestRenderer;
import com.sofort.lib.ideal.products.request.IDealBanksRequest;


public class TestIDealBanksRequestRenderer {

	@Test
	public void testRenderer() throws XmlRendererHelperException {

		IDealBanksRequest request = new IDealBanksRequest();

		XmlDocumentRenderable doc = new XmlDocumentRenderable("");
		new IDealBanksRequestRenderer().render(request, null);
		String xml = doc.getXml();

		assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\"?>", xml.trim());
	}
}
