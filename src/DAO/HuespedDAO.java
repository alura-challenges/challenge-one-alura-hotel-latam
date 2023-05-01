package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Entities.Huesped;

public class HuespedDAO {
	
	Connection con;
	
	public HuespedDAO(Connection con) {
		this.con = con;
	}


	public List<Huesped> listar() {

		List<Huesped> lista = new ArrayList<>();
		Huesped usuario = null;

		try {

			final PreparedStatement statement = con.prepareStatement("SELECT * FROM huespedes");

			try (statement) {
				
				statement.execute();

				final ResultSet rs = statement.getResultSet();

				try (rs) {
					
					while (rs.next()) {
						
						Integer id = rs.getInt("id_huespedes");
						String nombre = rs.getString("nombre");
						String apellido = rs.getString("apellido");
						LocalDate fechaNacimiento = rs.getDate("fecha_nacimiento").toLocalDate();
						String nacionalidad = rs.getString("nacionalidad");
						Integer telefono = rs.getInt("telefono");
						Integer numeroReserva = rs.getInt("id_reservas");
						
						usuario = new Huesped(id, nombre, apellido, fechaNacimiento, nacionalidad, telefono, numeroReserva);
						lista.add(usuario);
						System.out.println(usuario);
					} 
				} catch(SQLException e) {
					throw new RuntimeException(e);
				} 
			} catch(SQLException e) {
				throw new RuntimeException(e);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return lista;
	}

	
	
	public void guardar(Huesped usuario) {

    	try {

			final PreparedStatement statement = con.prepareStatement("INSERT INTO huespedes"
																+ "(nombre, apellido, fecha_nacimiento, nacionalidad, telefono, id_reservas) "
																+ "VALUES (?, ?, ?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
			try (statement) {
				
				statement.setString(1, usuario.getNombre());
				statement.setString(2, usuario.getApellido());
				statement.setObject(3, usuario.getFechaNacimiento());
				statement.setString(4, usuario.getNacionalidad());
				statement.setInt(5, usuario.getTelefono());
				statement.setInt(6, usuario.getNumeroReservas());

				statement.execute();
				
				final ResultSet rs = statement.getGeneratedKeys();

				try (rs) {
					while(rs.next()) {
						usuario.setId(rs.getInt(1));
						System.out.println("Fue insertado un nuevo huesped: " + usuario);
					}
				} catch(SQLException e) {
					throw new RuntimeException(e);
				}
			} catch(SQLException e) {
				throw new RuntimeException(e);
			}

		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	public int modificar(Integer id, String nombre, String apellido, LocalDate fecha_nac, String nacionalidad, Integer telefono) {

		try {

			final PreparedStatement statement = con.prepareStatement("UPDATE huespedes SET "
					+ "nombre = ? "
					+ ", apellido = ? "
					+ ", fecha_nacimiento = ? "
					+ ", nacionalidad = ? "
					+ ", telefono = ? "
					//+ ", id_reserva = ? "
					+ " WHERE id_huespedes = ? ");

			try (statement) {
				
				statement.setString(1, nombre);
				statement.setString(2, apellido);
				statement.setObject(3, fecha_nac);
				statement.setString(4, nacionalidad);
				statement.setInt(5, telefono);
				//statement.setInt(6, idReserva);
				statement.setInt(6, id);

				statement.execute();

				System.out.println("Se modificó con éxito el huesped: " + nombre);
				return statement.getUpdateCount();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public int eliminar(Integer id) {

		try {

			final PreparedStatement statement = con.prepareStatement("DELETE FROM huespedes WHERE id_huespedes = ?");

			try (statement) {

				statement.setInt(1, id);
				statement.execute();

				return statement.getUpdateCount();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
