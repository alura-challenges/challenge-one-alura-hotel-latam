package database.dao;

import java.sql.Connection;
import java.sql.SQLException;
import database.ConnectionManager;

public class MainDAO implements AutoCloseable{

	private Connection con;
	
	protected synchronized Connection getConnection() {
		if (con == null) {
			con = new ConnectionManager().getConnection();
		} else {
			try{
				if (con.isClosed()) {
					con = new ConnectionManager().getConnection();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				con = new ConnectionManager().getConnection();
			}
		}
		return con;
	}

	@Override
	public void close() throws Exception {
		con.close();
		
	}

}
