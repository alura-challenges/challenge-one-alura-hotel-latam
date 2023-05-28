package factory;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

public class ConnectionFactory {
	
	private DataSource datasource;
    public ConnectionFactory(){
    	ComboPooledDataSource pooledDataSource = new ComboPooledDataSource();
        pooledDataSource.setJdbcUrl("jdbc:mysql://localhost:3308/alura_hotel?useTimeZone=true&serverTimeZone=UTC");
        pooledDataSource.setUser("roger");
        pooledDataSource.setPassword("roger");

        pooledDataSource.setMinPoolSize(10);
        this.datasource = pooledDataSource;
    }
    public Connection recuperaConexion(){
        try {
            return this.datasource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
