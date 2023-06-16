package tests;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import database.ConnectionManager;

public class ConnectionManagerTest {
	
	public static void main(String[] args) throws SQLException {
		ConnectionManager connectionManager=new ConnectionManager();
	
		for (int i=0;i<20;i++) {
			Connection con=connectionManager.restoreConnection();
		
			System.out.println("Abriendo la conexion número "+ (i+1));
		
		}
	}
}
