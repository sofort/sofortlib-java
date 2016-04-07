package com.sofort.lib.core.internal.net.http;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotSame;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.security.cert.Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;

import org.testng.annotations.Test;

import com.sofort.lib.core.internal.net.Connection;
import com.sofort.lib.core.internal.net.ConnectionException;
import com.sofort.lib.core.internal.transformer.RawRequest;
import com.sofort.lib.core.internal.transformer.RawResponse;
import com.sofort.lib.core.internal.transformer.RawResponse.Status;
import com.sofort.lib.core.products.request.SofortLibRequest;


/**
 * Tests the internal logic of the http(s) connector.
 */
public class TestHttpConnector {

	private static class MockedConnection extends HttpsURLConnection {

		protected MockedConnection() {
			super(null);
		}


		@Override
		public String getCipherSuite() {
			return null;
		}


		@Override
		public Certificate[] getLocalCertificates() {
			return null;
		}


		@Override
		public Certificate[] getServerCertificates() throws SSLPeerUnverifiedException {
			return null;
		}


		@Override
		public void disconnect() {
			/* NoOp */
		}


		@Override
		public boolean usingProxy() {
			return false;
		}


		@Override
		public void connect() throws IOException {
			/* NoOp */
		}


		@Override
		public OutputStream getOutputStream() throws IOException {
			return new ByteArrayOutputStream();
		}


		@Override
		public InputStream getInputStream() throws IOException {
			return new ByteArrayInputStream(new byte[] { 'O', 'K' }, 0, 2);
		}
	}


	@Test
	public void testFailed() {

		RawRequest request = new RawRequest("<ABCDEF>xxx</ABCDEF>");
		final HttpConnector connector = new HttpConnector() {

			@Override
			protected HttpURLConnection openConnection(HttpConnectionData connectionData) throws IOException {
				return new MockedConnection();
			}
		};
		Connection connection = new HttpConnectionConfig(connector, new BasicHttpAuthorization(1, "k")) {

			@Override
			protected void initRequestConnections() {
				addConnection(SofortLibRequest.class, "http://localhost");
			}

		}.getConnection(SofortLibRequest.class);

		RawResponse response = connection.doRequest(request);
		assertEquals(Status.OK, response.getStatus());
		assertEquals("OK", response.getContent());
		assertNotSame(-1, response.getResponseTime());
	}


	@Test(expectedExceptions = ConnectionException.class, expectedExceptionsMessageRegExp = "java.net.UnknownHostException: domain.int")
	public void testFailed2() {
		RawRequest request = new RawRequest(null);
		HttpConnectionData connectionData = new HttpConnectionData("https://domain.int", null);
		HttpConnector connector = new HttpConnector();

		connector.doRequest(request, connectionData);
	}


	@Test(expectedExceptions = HttpAuthorizationException.class, expectedExceptionsMessageRegExp = "Server returned HTTP response code: 401 for URL: https://api.sofort.com/api/xml")
	public void testFailed3() {
		RawRequest request = new RawRequest("<ABCDEF>xxx</ABCDEF>");
		HttpConnector connector = new HttpConnector();
		Connection connection = new HttpConnectionConfig(connector, new BasicHttpAuthorization(0, "")) {

			@Override
			protected void initRequestConnections() {
				addConnection(SofortLibRequest.class, "https://api.sofort.com/api/xml");
			}

		}.getConnection(SofortLibRequest.class);

		connection.doRequest(request);

	}
}
