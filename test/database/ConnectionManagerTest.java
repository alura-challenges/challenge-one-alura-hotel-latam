package database;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import config.Setting;
import junit.framework.Assert;

public class ConnectionManagerTest {
	
	private ConnectionManager connectionManager = new ConnectionManager();

	@Test
	public void testConnectionManager() throws SQLException {
		Connection connection = connectionManager.restoreConnection();
		Assert.assertFalse("Connection should be open", connection.isClosed());
		System.out.println("Test connection manager");
	}

}
