package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Entities.Huesped;
import Factory.ConnectionFactory;

public class HuespedesDAO {
	
	Connection con;
	
	public List<Huesped> listar() {

		List<Huesped> lista = new ArrayList<>();
		Huesped usuario = null;
		
		Connection con = new ConnectionFactory().recuperateConnection();

		try (con) {

			final PreparedStatement statement = con.prepareStatement("SELECT * FROM huespedes");

			try (statement) {
				
				statement.execute();

				final ResultSet rs = statement.getResultSet();

				try (rs) {
					
					while (rs.next()) {
						
						int id = rs.getInt("id_huespedes");
						String nombre = rs.getString("nombre");
						String apellido = rs.getString("apellido");
						Date fechaNacimiento = rs.getDate("fecha_nacimiento");
						String nacionalidad = rs.getString("nacionalidad");
						int telefono = rs.getInt("telefono");
						int numeroReserva = rs.getInt("id_reservas");
						
						usuario = new Huesped(id, nombre, apellido, fechaNacimiento, nacionalidad, telefono, numeroReserva);
						lista.add(usuario);
						System.out.println(usuario);
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return lista;
	}

	
}
