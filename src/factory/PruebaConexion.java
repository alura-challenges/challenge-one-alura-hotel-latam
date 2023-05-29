package factory;

import java.sql.Connection;
import java.sql.SQLException;

public class PruebaConexion {
	  public static void main(String[] args) throws SQLException {
	        Connection con = new ConnectionFactory().recuperaConexion();
	        System.out.println("Cerrando exitosa");
	        con.close();
	    }
}
