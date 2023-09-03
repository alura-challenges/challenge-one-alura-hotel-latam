package factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connectionFactory {

	public Connection getConexion() throws SQLException{
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/Hotel?useTimeZone=True&serverTimeZone=UTC", 
                "root",
                "smg72684518");
		
		
	}

}

