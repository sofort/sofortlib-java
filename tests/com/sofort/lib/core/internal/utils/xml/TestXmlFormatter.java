package com.sofort.lib.core.internal.utils.xml;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertNull;
import static org.testng.AssertJUnit.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.testng.annotations.Test;


public class TestXmlFormatter {

	@Test
	public void testFormatDate() {
		Calendar c = Calendar.getInstance();
		c.set(2007, Calendar.JUNE, 5, 4, 3, 2);
		c.set(Calendar.MILLISECOND, 0);

		assertNull(XmlFormatter.format((Date) null));
		assertEquals(
				new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").format(c.getTime()).replaceFirst("(\\d{2})$", ":$1"),
				XmlFormatter.format(c.getTime()));

	}


	@Test
	public void testFormatNumber() {
		assertNull(XmlFormatter.format((Number) null));
		assertNull(XmlFormatter.format(0, 0));
		assertEquals("0.12", XmlFormatter.format(Double.valueOf(0.12)));
		assertEquals("12", XmlFormatter.format(Long.valueOf(12)));
		assertEquals("12", XmlFormatter.format(Long.valueOf(12), null));
	}


	@Test
	public void testFormatBoolean() {
		assertNull(XmlFormatter.format((Boolean) null));
		assertEquals("1", XmlFormatter.format(Boolean.TRUE));
		assertEquals("0", XmlFormatter.format(Boolean.FALSE));
	}


	@Test
	public void testParseDate() {
		Calendar c = Calendar.getInstance();
		c.set(2007, Calendar.JUNE, 5, 4, 3, 2);
		c.set(Calendar.MILLISECOND, 0);

		assertEquals(c.getTime(), XmlFormatter.parseDate(null, c.getTime()));
		assertEquals(c.getTime(), XmlFormatter.parseDate("2012", c.getTime()));
		assertEquals(c.getTime(), XmlFormatter.parseDate(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").format(c.getTime()).replaceFirst("(\\d{2})$", ":$1"), null));

		c.set(2007, Calendar.JUNE, 5, 0, 0, 0);
		assertEquals(c.getTime(), XmlFormatter.parseShortDate(null, c.getTime()));
		assertEquals(c.getTime(), XmlFormatter.parseShortDate("2012", c.getTime()));
		assertEquals(c.getTime(), XmlFormatter.parseShortDate(new SimpleDateFormat("yyyy-MM-dd").format(c.getTime()), null));
	}


	@Test
	public void testParseDouble() {
		assertEquals(0.12, XmlFormatter.parseDouble("0.12", 0.24), 0.001);
		assertEquals(0.24, XmlFormatter.parseDouble(null, 0.24), 0.001);
		assertEquals(0.24, XmlFormatter.parseDouble("", 0.24), 0.001);
	}


	@Test
	public void testParseLong() {
		assertEquals(12, XmlFormatter.parseLong("12", 24));
		assertEquals(24, XmlFormatter.parseLong(null, 24));
		assertEquals(24, XmlFormatter.parseLong("", 24));
	}


	@Test
	public void testParseInt() {
		assertEquals(12, XmlFormatter.parseInt("12", 24));
		assertEquals(24, XmlFormatter.parseInt(null, 24));
		assertEquals(24, XmlFormatter.parseInt("", 24));
	}


	@Test
	public void testParseBoolean() {
		assertTrue(XmlFormatter.parseBoolean(null, true));
		assertTrue(XmlFormatter.parseBoolean("", true));
		assertTrue(XmlFormatter.parseBoolean("x", true));

		assertTrue(XmlFormatter.parseBoolean("true", false));
		assertTrue(XmlFormatter.parseBoolean("1", false));
		assertTrue(XmlFormatter.parseBoolean("yes", false));
		assertTrue(XmlFormatter.parseBoolean("on", false));

		assertFalse(XmlFormatter.parseBoolean("false", true));
		assertFalse(XmlFormatter.parseBoolean("0", true));
		assertFalse(XmlFormatter.parseBoolean("no", true));
		assertFalse(XmlFormatter.parseBoolean("off", true));
	}
}
