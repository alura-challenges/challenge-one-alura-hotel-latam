package database;

import java.sql.Connection;
import java.sql.SQLException;
import org.junit.jupiter.api.Test;
import junit.framework.Assert;

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
