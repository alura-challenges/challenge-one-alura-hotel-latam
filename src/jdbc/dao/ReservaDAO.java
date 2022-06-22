package jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import jdbc.modelo.Reserva;

public class ReservaDAO {
	
	private Connection connection;
	
	public ReservaDAO(Connection connection) {
		this.connection = connection;
	}
	
	public void salvar(Reserva reserva) {
		try {
			String sql = "INSERT INTO reservas (fecha_entrada, fecha_salida, valor, formaPago) VALUES (?, ?, ?, ?)";

			try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

				pstm.setDate(1, reserva.getfechaE());
				pstm.setDate(2, reserva.getfechaS());
				pstm.setString(3, reserva.getvalor());
				pstm.setString(4, reserva.getformaPago());

				pstm.executeUpdate();

				try (ResultSet rst = pstm.getGeneratedKeys()) {
					while (rst.next()) {
						reserva.setId(rst.getInt(1));
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}
}
