package Factory;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionFactory {
	
private final DataSource dataSource;
	
	// Inicializando Pool de Conexiones
    public ConnectionFactory() {
    	
    	var pooledDataSource = new ComboPooledDataSource();
    	pooledDataSource.setJdbcUrl("jdbc:mysql://localhost:3306/hotel_alura?useTimeZone=true&serverTimeZone=UTC");
    	pooledDataSource.setUser("userJava");
    	pooledDataSource.setPassword("Java_Proyect");
    	
    	this.dataSource = pooledDataSource;
	}

	public Connection recuperateConnection() {
		
        try {
			return this.dataSource.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

    }
}
