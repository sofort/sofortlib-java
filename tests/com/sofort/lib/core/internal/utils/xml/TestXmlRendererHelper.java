package com.sofort.lib.core.internal.utils.xml;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;
import static org.testng.AssertJUnit.fail;

import org.testng.annotations.Test;
import org.w3c.dom.Element;

import com.sofort.lib.core.internal.utils.Attribute;


public class TestXmlRendererHelper {

	@Test
	public void testRendererFails() throws XmlRendererHelperException {

		XmlRendererHelper renderer = new XmlRendererHelper();
		renderer.appendRootElement("html");

		try {
			renderer.appendRootElement("html");
			fail("An exception have to be thrown here!");
		} catch (Exception e) {
			assertTrue(e instanceof XmlRendererHelperException);
		}
	}


	@Test
	public void testRenderer() throws XmlRendererHelperException {

		XmlRendererHelper renderer = new XmlRendererHelper();
		renderer.getXml();

		Element html = renderer.appendRootElement("html");

		Element head = renderer.createElement("head");
		renderer.append(html, head);

		Element title = renderer.createElement("title", "Title");
		renderer.append(head, title);

		Element body = renderer.createElement("body");
		renderer.append(html, body);

		Element div = renderer.createElement("div", "Content");
		div.setAttribute("style", "float:left;");
		renderer.append(body, div);

		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<html>\n  <head>\n    <title>Title</title>\n  </head>\n  <body>\n    <div style=\"float:left;\">Content</div>\n  </body>\n</html>\n";
		String builderXml = renderer.getXml();
		assertEquals(xml, builderXml);

		/* test edge cases */
		renderer.append(null, null);
		renderer.append(html, null);
		renderer.append(null, html);
		renderer.createElement((String) null, (String) null);
		renderer.createElement("", (String) null);
		renderer.createElement("tmp1", "tmp2", null);
		renderer.setContent(null, null);
		renderer.setContent(html, null);
		renderer.setContent(null, "");
		renderer.setContent(html, "");

		Attribute attr1 = new Attribute(null, null);
		Attribute attr2 = new Attribute("abc", null);
		Attribute attr3 = new Attribute("abc", "def");
		renderer.setAttribute(html, null);
		renderer.setAttribute(html, attr1);
		renderer.setAttribute(html, attr2);
		renderer.setAttribute(html, attr3);
	}
}
