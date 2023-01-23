package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import models.Huesped;

public class HuespedDAO {

	private Connection con;

	public HuespedDAO(Connection con) {
		this.con = con;
	}

	public void guardar(Huesped huesped) {
		try {
			PreparedStatement statement = con.prepareStatement("INSERT INTO HUESPED"
					+ "(nombre, apellido, fecha_de_nacimiento, nacionalidad, telefono, id_reserva)"
					+ " VALUES (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

			try (statement) {
				statement.setString(1, huesped.getNombre());
				statement.setString(2, huesped.getApellido());
				statement.setDate(3, new java.sql.Date(huesped.getFechaNacimiento().getTime()));
				statement.setString(4, huesped.getNacionalidad());
				statement.setString(5, huesped.getTelefono());
				statement.setInt(6, huesped.getIdReserva());

				statement.execute();

				final ResultSet resultSet = statement.getGeneratedKeys();

				try (resultSet) {
					while (resultSet.next()) {
						huesped.setId(resultSet.getInt(1));
						System.out.println(String.format("Fue agregado el huesped: %s", huesped));
					}
				}

			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	public List<Huesped> listar() {
		List<Huesped> resultado = new ArrayList<>();
		try {
			final PreparedStatement statement = con.prepareStatement("SELECT id, nombre, apellido, fecha_de_nacimiento, nacionalidad, telefono, id_reserva FROM huesped");
			
			try (statement) {
				statement.execute();
				final ResultSet resultSet = statement.getResultSet();
				
				try (resultSet){
					while (resultSet.next()) {
						resultado.add(new Huesped(
								resultSet.getInt("id"),
								resultSet.getString("nombre"),
								resultSet.getString("apellido"),
								resultSet.getDate("fecha_de_nacimiento"),
								resultSet.getString("nacionalidad"),
								resultSet.getString("telefono"),
								resultSet.getInt("id_reserva") ));
					}
				}
			}
			
		} catch (SQLException e) {
            throw new RuntimeException(e);
		}
		
		return resultado;
	}

	public int modificar(Integer id, String nombre, String apellido, Date fechaNacimiento, String nacionalidad,
			String telefono, Integer idReserva) {
		try {
			final PreparedStatement statement = con.prepareStatement(
                    "UPDATE huesped SET "
                    + " nombre = ?,"
                    + " apellido = ?,"
                    + " fecha_de_nacimiento = ?,"
                    + " nacionalidad = ?,"
                    + " telefono = ? "
                    + " WHERE id = ?");
			try (statement) {
				statement.setString(1, nombre);
				statement.setString(2, apellido);
				statement.setDate(3, new java.sql.Date(fechaNacimiento.getTime()));
				statement.setString(4, nacionalidad);
				statement.setString(5, telefono);
				statement.setInt(6, id);
				
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
            final PreparedStatement statement = con.prepareStatement("DELETE FROM huesped WHERE id = ?");

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
