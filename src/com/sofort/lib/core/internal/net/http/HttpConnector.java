package com.sofort.lib.core.internal.net.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;

import com.sofort.lib.core.internal.net.ConnectionData;
import com.sofort.lib.core.internal.net.ConnectionException;
import com.sofort.lib.core.internal.net.Connector;
import com.sofort.lib.core.internal.transformer.RawRequest;
import com.sofort.lib.core.internal.transformer.RawResponse;
import com.sofort.lib.core.internal.transformer.RawResponse.Status;


/**
 * A default HTTP(S) connector needed to communicate with the current
 * implementation of SofortLib API on the lowest level.
 * 
 * The HTTP connector is a pure Java implementation of communication over
 * HTTP(S) protocol.
 * 
 * Defaults: POST (if content of request is not empty), UTF-8.
 * 
 * Supports: gzip, basic authorization.
 * 
 * For developer: can be replaced with a more powerful HTTP(S) client if needed.
 */
public class HttpConnector implements Connector {

	public static final String VERSION = "v1.0.4";


	@Override
	public RawResponse doRequest(RawRequest request, ConnectionData cd) {
		final long start = System.currentTimeMillis();

		final String normalizedPostData = request.getContent() != null ? request.getContent().trim() : "";

		HttpURLConnection connection = null;
		HttpConnectionData connectionData = (HttpConnectionData) cd;

		InputStreamReader in = null;
		OutputStreamWriter out = null;

		try {

			/* initialize connection */
			connection = openConnection(connectionData);
			prepareConnection(connection, connectionData.getAuthorisation(), normalizedPostData.length());
			connection.connect();

			if (!normalizedPostData.isEmpty()) {
				/* send the post request to server */
				out = new OutputStreamWriter(connection.getOutputStream());
				out.write(normalizedPostData);
				/* clean up */
				out.flush();
				out.close();
				out = null;
			}

			/* receive the response from server */
			InputStream is = connection.getInputStream();
			if ("gzip".equalsIgnoreCase(connection.getContentEncoding())) {
				is = new GZIPInputStream(is);
			}
			in = new InputStreamReader(is, Charset.forName("UTF-8"));

			final StringBuilder container = new StringBuilder();
			final char[] readBuffer = new char[4096];
			int read;
			while ((read = in.read(readBuffer)) > 0) {
				container.append(readBuffer, 0, read);
			}

			final long responseTime = System.currentTimeMillis() - start;

			return new RawResponse(Status.OK, container.toString(), responseTime);

		} catch (final Exception e) {

			final Pattern pattern = Pattern.compile("Server returned HTTP response code: (\\d{3})", Pattern.CASE_INSENSITIVE);
			final Matcher matcher = pattern.matcher(e.getMessage());
			if (matcher.find()) {

				final int responseCode = Integer.parseInt(matcher.group(1));
				if (responseCode == 401) {
					throw new HttpAuthorizationException(e.getMessage());
				}

				throw new HttpConnectionException(responseCode, e.getMessage());
			}

			throw new ConnectionException(e);

		} finally {

			/* close out stream */
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					/* NoOp */
				}
			}

			/* close in stream */
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					/* NoOp */
				}
			}

			/* disconnect */
			if (connection != null) {
				connection.disconnect();
			}
		}
	}


	/**
	 * Opens the connection.
	 * 
	 * @param connectionData
	 *            connection data with target URL the connection will be opened
	 *            to.
	 * @return the opened HTTP connection
	 * @throws IOException
	 *             thrown on call of {@link URL#openConnection()}
	 */
	protected HttpURLConnection openConnection(HttpConnectionData connectionData) throws IOException {
		return (HttpURLConnection) connectionData.getTarget().openConnection();
	}


	/**
	 * Initializes common connection settings.
	 * 
	 * @param connection
	 *            opened HTTP connection
	 * @param authorization
	 *            the basic HTTP authorization
	 * @param postDataLength
	 *            the length of the data to be posted, used for setting the
	 *            proper value of the Content-Length header
	 * 
	 * @throws ProtocolException
	 *             thrown exception on wrong call of
	 *             {@link HttpURLConnection#setRequestMethod(String)}
	 */
	protected void prepareConnection(HttpURLConnection connection, BasicHttpAuthorization authorization, int postDataLength) throws ProtocolException {
		connection.setConnectTimeout((int) TimeUnit.SECONDS.toMillis(30));

		if (postDataLength > 0) {
			connection.setDoOutput(true);
			connection.setRequestProperty("Content-Length", String.valueOf(postDataLength));
		}

		connection.setDoInput(true);
		connection.setUseCaches(false);
		connection.setInstanceFollowRedirects(true);

		/* initialize interface settings */
		connection.setRequestMethod("POST");
		if (authorization != null) {
			connection.setRequestProperty("Authorization", authorization.getValue());
		}
		connection.setRequestProperty("Content-Type", "application/xml; charset=UTF-8");
		connection.setRequestProperty("Accept", "application/xml; charset=UTF-8");

		/* initialize additional settings */
		connection.setRequestProperty("User-Agent", "Sofort Lib Java " + getClass().getSimpleName() + " " + VERSION + " (SofortLib v@sofortlib-version@)");
		connection.setRequestProperty("Accept-Encoding", "gzip, deflate");

	}

}
