package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
					+ "VALUES (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			try (statement){
				ejecutarRegistro(statement, huesped);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void ejecutarRegistro(PreparedStatement statement, Huesped huesped) throws SQLException {
		java.sql.Date fechaNacimientoSQL = new java.sql.Date(huesped.getFechaNacimiento().getTime());
		statement.setString(1, huesped.getNombre());
		statement.setString(2, huesped.getApellido());
		statement.setDate(3, fechaNacimientoSQL);
		statement.setString(4, huesped.getNacionalidad());
		statement.setLong(5, huesped.getTelefono());
		statement.setInt(6, huesped.getidReserva());
		statement.executeUpdate();
		final ResultSet resultSet = statement.getGeneratedKeys();
		try (resultSet) {
			while (resultSet.next()) {
				huesped.setId(resultSet.getInt(1));
				System.out.println("Fue insertado el " + huesped);
			}
		}
	}

	public List<Huesped> listarHuespedes() {
		List<Huesped> listaHuespedes = new ArrayList<>();
		try {
			final PreparedStatement statement = this.connection.prepareStatement("SELECT * from huespedes");
			try(statement){
				statement.execute();
				final ResultSet resultSet = statement.getResultSet();
				try(resultSet){
					while(resultSet.next()) {
						Huesped huesped = new Huesped(resultSet.getInt("id"), resultSet.getString("nombre"), 
								resultSet.getString("apellido"), resultSet.getDate("fechaNacimiento"), 
								resultSet.getString("nacionalidad"), resultSet.getLong("telefono"), 
								resultSet.getInt("idReserva"));
						listaHuespedes.add(huesped);
					}
					return listaHuespedes;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
