package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Huesped;

public class HuespedDAO {
	private Connection connection;

	public HuespedDAO(Connection con) {
		this.connection = con;
	}

	public void guardar(Huesped huesped) {
		try {
			final PreparedStatement statement = this.connection.prepareStatement("INSERT INTO huespedes "
					+ "(nombre, apellido, fechaNacimiento, nacionalidad, telefono, idReserva) "
					+ "VALUES (?, ?, ?, ?, ?, ?)");
			try (statement){
				ejecutarRegistro(statement, huesped);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void ejecutarRegistro(PreparedStatement statement, Huesped huesped) throws SQLException {
		statement.setString(1, huesped.getNombre());
		statement.setString(2, huesped.getApellido());
		statement.setString(3, huesped.getFechaNacimiento());
		statement.setString(4, huesped.getNacionalidad());
		statement.setLong(5, huesped.getTelefono());
		statement.setInt(6, huesped.getIdReserver());
		statement.executeUpdate();
		final ResultSet resultSet = statement.getGeneratedKeys();
		try (resultSet) {
			while (resultSet.next()) {
				huesped.setId(resultSet.getInt(1));
				System.out.println("Fue insertado el " + huesped);
			}
		}
	}
}
