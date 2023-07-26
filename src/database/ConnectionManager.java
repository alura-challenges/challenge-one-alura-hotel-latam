package database;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import config.Setting;

public class ConnectionManager {
	
	private final DataSource datasource;
	private final Setting setting;
	private static final int MAX_POOL_SIZE=10;
	
	public ConnectionManager() {
		ComboPooledDataSource pooledDataSource = new ComboPooledDataSource();
		setting=new Setting();
		pooledDataSource.setJdbcUrl(setting.getProperty("db_url"));
		pooledDataSource.setUser(System.getenv("USERNAME"));
		pooledDataSource.setPassword(System.getenv("PASSWORD"));
		pooledDataSource.setMaxPoolSize(MAX_POOL_SIZE);		
		this.datasource=pooledDataSource;
	}
	
	public Connection getConnection() {
		try {
			return this.datasource.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}