package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Reserva;

public class ReservaDAO {
	final private Connection connection;
	public ReservaDAO(Connection connection) {
		this.connection = connection;
	}
	public void guardar(Reserva reserva) {
		try {
			final PreparedStatement statement = connection.prepareStatement(
					"INSERT INTO reservas (fechaEntrada, fechaSalida, valor, formaPago)" + "VALUES" + "(?,?,?,?);",
					Statement.RETURN_GENERATED_KEYS);
			try (statement) {
				ejecutarRegistro(reserva, statement);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	private void ejecutarRegistro(Reserva reserva, PreparedStatement statement) throws SQLException {
		java.sql.Date fechaEntradaSQL = new java.sql.Date(reserva.getFechaEntrada().getTime());
		java.sql.Date fechaSalidaSQL = new java.sql.Date(reserva.getFechaSalida().getTime());

		try {
			statement.setDate(1, fechaEntradaSQL);
			statement.setDate(2, fechaSalidaSQL);
			statement.setDouble(3, reserva.getValor());
			statement.setString(4, reserva.getFormaPago());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		final ResultSet resultSet = statement.getGeneratedKeys();
		try (resultSet) {
			while (resultSet.next()) {
				reserva.setId(resultSet.getInt(1));
				System.out.println("Fue insertado el " + reserva);
			}
		}
	}
	public int ultimaReserva() {
		try {
			final PreparedStatement statement = this.connection.prepareStatement("SELECT * FROM reservas ORDER BY  id DESC LIMIT 1");
			try (statement) {
				final ResultSet resultSet = statement.executeQuery();
				try (resultSet) {
					resultSet.next();
					return resultSet.getInt("id");
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public List<Reserva> listasReservas() {
		List<Reserva> resultado = new ArrayList<>();
		try {
			final PreparedStatement statement = connection.prepareStatement("Select * from reservas");
			try (statement) {
				statement.execute();
				final ResultSet resultSet = statement.getResultSet();
				try (resultSet) {
					while (resultSet.next()) {
						Reserva fila = new Reserva(resultSet.getInt("id"), resultSet.getDate("fechaEntrada"), resultSet.getDate("fechaSalida"),
								resultSet.getString("formaPago"), resultSet.getDouble("valor"));
						resultado.add(fila);
					}
					return resultado;
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
}
