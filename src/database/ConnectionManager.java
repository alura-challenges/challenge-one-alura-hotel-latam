package database;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import config.Setting;


public class ConnectionManager  {
	
	private DataSource datasource;
	private Setting setting;
	
	
	public ConnectionManager() {
		ComboPooledDataSource pooledDataSource = new ComboPooledDataSource();
		setting=new Setting();
		pooledDataSource.setJdbcUrl(setting.getUrl("db_url"));
		pooledDataSource.setUser(System.getenv("USERNAME"));
		pooledDataSource.setPassword(System.getenv("PASSWORD"));
		pooledDataSource.setMaxPoolSize(10);
		
		this.datasource=pooledDataSource;
	}
	
	public Connection restoreConnection() {
		try {
			return this.datasource.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
