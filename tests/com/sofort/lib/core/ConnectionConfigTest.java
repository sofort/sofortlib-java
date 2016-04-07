package com.sofort.lib.core;

import com.sofort.lib.core.internal.net.Connection;
import com.sofort.lib.core.internal.net.ConnectionConfig;
import com.sofort.lib.core.internal.net.ConnectionData;
import com.sofort.lib.core.internal.net.Connector;
import com.sofort.lib.core.products.request.SofortLibRequest;


public class ConnectionConfigTest implements ConnectionConfig {

	private final Connection connection;


	public ConnectionConfigTest(Connector connector) {
		this(connector, null);
	}


	public ConnectionConfigTest(Connector connector, ConnectionData config) {
		this.connection = new Connection(connector, config);
	}


	@Override
	public Connection getConnection(Class<? extends SofortLibRequest> requestClass) {
		return connection;
	}

}
