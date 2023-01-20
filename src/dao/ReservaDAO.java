package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import models.Huesped;
import models.Reserva;

public class ReservaDAO {

	private Connection con;

	public ReservaDAO(Connection con) {
		this.con = con;
	}

	public void guardar(Reserva reserva) {
		try {
			PreparedStatement statement = con.prepareStatement(
					"INSERT INTO RESERVA"
					+ "(fecha_entrada, fecha_salida, valor, forma_de_pago)"
					+ " VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			
			try (statement) {
				statement.setDate(1, new java.sql.Date(reserva.getFechaEntrada().getTime()));
				statement.setDate(2, new java.sql.Date(reserva.getFechaSalida().getTime()));
				statement.setDouble(3, reserva.getValor());
				statement.setString(4, reserva.getFormaPago());
				
				statement.execute();
				
				final ResultSet resultSet = statement.getGeneratedKeys();
				
				try (resultSet) {
					while (resultSet.next()) {
						reserva.setId(resultSet.getInt(1));
						System.out.println(String.format("Fue creada la reserva: %s", reserva));
					}
				}
			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	public List<Reserva> listar() {
		List<Reserva> resultado = new ArrayList<>();
		try {
			final PreparedStatement statement = con.prepareStatement("SELECT id, fecha_entrada, fecha_salida, valor, forma_de_pago FROM reserva");
			
			try (statement) {
				statement.execute();
				final ResultSet resultSet = statement.getResultSet();
				
				try (resultSet){
					while (resultSet.next()) {
						resultado.add(new Reserva(
								resultSet.getInt("id"),
								resultSet.getDate("fecha_entrada"),
								resultSet.getDate("fecha_salida"),
								resultSet.getDouble("valor"),
								resultSet.getString("forma_de_pago")));
					}
				}
			}
			
		} catch (SQLException e) {
            throw new RuntimeException(e);
		}
		
		
		return resultado;
	}

	public List<Reserva> listarConHuespedes(String text) {
		List<Reserva> resultado = new ArrayList<>();
		
		try {
			int idReserva = Integer.parseInt(text);
			
			try {
				String sql = "SELECT r.id, r.fecha_entrada, r.fecha_salida, r.valor, r.forma_de_pago, h.id, h.nombre, h.apellido, h.fecha_de_nacimiento, h.nacionalidad, h.telefono, h.id_reserva "
	                    + " FROM reserva r INNER JOIN huesped h "
	                    + " ON r.id = h.id_reserva"
	                    + " WHERE r.id = ? ";
				final PreparedStatement statement = con.prepareStatement(sql);
				try (statement) {
					statement.setInt(1, idReserva);
	                final ResultSet resultSet = statement.executeQuery();
	                try (resultSet) {
						while (resultSet.next()) {
							
							Integer reservaId = resultSet.getInt("r.id");
							Date reservaFechaEntrada = resultSet.getDate("r.fecha_entrada");
							Date reservaFechaSalida = resultSet.getDate("r.fecha_salida");
							Double reservaValor = resultSet.getDouble("r.valor");
							String reservaFormaPago = resultSet.getString("r.forma_de_pago");
							
							Reserva reserva = resultado
									.stream()
									.filter(r -> r.getId().equals(reservaId))
									.findAny()
									.orElseGet(() -> {
										Reserva res = new Reserva(
												reservaId,
												reservaFechaEntrada,
												reservaFechaSalida,
												reservaValor,
												reservaFormaPago
												);
										resultado.add(res);
										return res;
									});
									
							Huesped huesped = new Huesped(
									resultSet.getInt("h.id"),
									resultSet.getString("h.nombre"),
									resultSet.getString("h.apellido"),
									resultSet.getDate("h.fecha_de_nacimiento"),
									resultSet.getString("h.nacionalidad"),
									resultSet.getString("h.telefono"),
									resultSet.getInt("h.id_reserva") );
							
							reserva.agregar(huesped);

						}
					}
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
				
		} catch (NumberFormatException e) {
			String apellido = text;
			
			try {
				String sql = "SELECT r.id, r.fecha_entrada, r.fecha_salida, r.valor, r.forma_de_pago, h.id, h.nombre, h.apellido, h.fecha_de_nacimiento, h.nacionalidad, h.telefono, h.id_reserva "
	                    + " FROM reserva r INNER JOIN huesped h "
	                    + " ON r.id = h.id_reserva"
	                    + " WHERE h.apellido LIKE ? ";
	            
	            final PreparedStatement statement = con.prepareStatement(sql);
	            
	            try (statement) {
	            	statement.setString(1, "%"+apellido+"%");
	                final ResultSet resultSet = statement.executeQuery();
	                
	                try (resultSet) {
	                	while (resultSet.next()) {
	                		Integer reservaId = resultSet.getInt("r.id");
							Date reservaFechaEntrada = resultSet.getDate("r.fecha_entrada");
							Date reservaFechaSalida = resultSet.getDate("r.fecha_salida");
							Double reservaValor = resultSet.getDouble("r.valor");
							String reservaFormaPago = resultSet.getString("r.forma_de_pago");
							
							Reserva reserva = resultado
									.stream()
									.filter(r -> r.getId().equals(reservaId))
									.findAny()
									.orElseGet(() -> {
										Reserva res = new Reserva(
												reservaId,
												reservaFechaEntrada,
												reservaFechaSalida,
												reservaValor,
												reservaFormaPago
												);
										resultado.add(res);
										return res;
									});
									
							Huesped huesped = new Huesped(
									resultSet.getInt("h.id"),
									resultSet.getString("h.nombre"),
									resultSet.getString("h.apellido"),
									resultSet.getDate("h.fecha_de_nacimiento"),
									resultSet.getString("h.nacionalidad"),
									resultSet.getString("h.telefono"),
									resultSet.getInt("h.id_reserva") );
							
							reserva.agregar(huesped);
	                	}
	                }
	            }
			} catch (SQLException ex) {
				throw new RuntimeException(ex);
			}
		}

        return resultado;
	}

	public int modificar(Integer id, java.util.Date fechaEntrada, java.util.Date fechaSalida, Double valor,
			String formaPago) {
		try {
			final PreparedStatement statement = con.prepareStatement(
                    "UPDATE reserva SET "
                    + " fecha_entrada = ?,"
                    + " fecha_salida = ?,"
                    + " valor = ?,"
                    + " forma_de_pago = ?"
                    + " WHERE id = ?");
			try (statement) {
				statement.setDate(1, new java.sql.Date(fechaEntrada.getTime()));
				statement.setDate(2, new java.sql.Date(fechaSalida.getTime()));
				statement.setDouble(3, valor);
				statement.setString(4, formaPago);
				statement.setInt(5, id);
				
				statement.executeUpdate();
				
				int updateCount = statement.getUpdateCount();
				
				return updateCount;
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	public int eliminar(Integer id) {
        try {
            final PreparedStatement statement = con.prepareStatement("DELETE FROM reserva WHERE id = ?");

            try (statement) {
                statement.setInt(1, id);
                statement.execute();

                int updateCount = statement.getUpdateCount();

                return updateCount;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
	}

}
