package database.connection;

import java.sql.Connection;
import java.sql.SQLException;
import org.junit.runner.RunWith;
import org.junit.Test;
import org.junit.runners.JUnit4;
import database.ConnectionManager;
import org.junit.Assert;

@RunWith(JUnit4.class)
public class ConnectionManagerTest {
	
	private ConnectionManager connectionManager = new ConnectionManager();

	@Test
	public void testGetConnection() throws SQLException {
		Connection connection = connectionManager.getConnection();
		Assert.assertFalse("Connection should be open", connection.isClosed());		
		connection.close();
		Assert.assertTrue("Connection should be closed", connection.isClosed());
	}
}
