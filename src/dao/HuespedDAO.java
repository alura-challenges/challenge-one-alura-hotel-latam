package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modelo.Huespedes;

public class HuespedDAO {
	final private Connection con;

	public HuespedDAO(Connection con) {
		this.con = con;
	}

	public void guardar(Huespedes huesped) {
		try { PreparedStatement statement = con.prepareStatement(
				"INSERT INTO HUESPEDES (NOMBRE, APELLIDO, FECHA_NACIMIENTO, NACIONALIDAD, TELEFONO, RESERVA_ID)" 
						+ "VALUES(?,?,?,?,?,?)",
				Statement.RETURN_GENERATED_KEYS);
				ejecutaRegistro (huesped,  statement);
			
		
		}catch (SQLException e) {
			throw new RuntimeException (e);
	}	
}	

private void ejecutaRegistro(Huespedes huesped, PreparedStatement statement)
		throws SQLException {
	statement.setString(1, huesped.getNombre());
	statement.setString(2, huesped.getApellido());
	statement.setDate(3, huesped.getFechaNacimiento());
	statement.setString(4, huesped.getNacionalidad());
	statement.setString(5, huesped.getTelefono());
	statement.setInt(6, huesped.getId_reserva());
	statement.execute();
	
	final ResultSet resultSet = statement.getGeneratedKeys();
		while (resultSet.next()) {
		huesped.setId(resultSet.getInt(1));
		System.out.println(String.format("Fue insertado el huesped %s", huesped));
		
	}	
}

public List<Huespedes> listarHuespedes() {
	List<Huespedes> resultado = new ArrayList<>();
	try {
		final PreparedStatement statement = con.prepareStatement("SELECT ID, NOMBRE, APELLIDO,"
				+ " FECHA_NACIMIENTO, NACIONALIDAD, TELEFONO, RESERVA_ID FROM HUESPEDES");
			statement.execute();

			final ResultSet resultset = statement.getResultSet();
			while (resultset.next()) {
				Huespedes fila = new Huespedes(resultset.getInt("ID"),
						resultset.getString("NOMBRE"),
						resultset.getString("APELLIDO"),
						resultset.getDate("FECHA_NACIMIENTO"),
						resultset.getString("NACIONALIDAD"),
						resultset.getString("TELEFONO"),
						resultset.getInt("RESERVA_ID"));
				
				resultado.add(fila);
			
		
	}
			return resultado;
	} catch (SQLException e) {
		throw new RuntimeException(e);
	}
}

public List<Huespedes> listarHuespedesApellido(String apellido) {
	List<Huespedes> resultado = new ArrayList<>();
	try {
		final PreparedStatement statement = con.prepareStatement("SELECT ID, NOMBRE, APELLIDO,"
				+ " FECHA_NACIMIENTO, NACIONALIDAD, TELEFONO, RESERVA_ID FROM HUESPEDES"
				+ " WHERE APELLIDO = ? ");
			statement.setString(1, apellido);
			statement.execute();

			final ResultSet resultset = statement.getResultSet();
			while (resultset.next()) {
				Huespedes fila = new Huespedes(resultset.getInt("ID"),
						resultset.getString("NOMBRE"),
						resultset.getString("APELLIDO"),
						resultset.getDate("FECHA_NACIMIENTO"),
						resultset.getString("NACIONALIDAD"),
						resultset.getString("TELEFONO"),
						resultset.getInt("RESERVA_ID"));
				
				resultado.add(fila);
			}
		
	
			return resultado;
	} catch (SQLException e) {
		throw new RuntimeException(e);
	}
}

public int modificarHuesped (Integer id, String nombre, String apellido, Date fechaNacimiento, String nacionalidad,
		String telefono, Integer id_reserva) {
	try {
		final PreparedStatement statement = con.prepareStatement(
				"UPDATE HUESPEDES SET NOMBRE = ? , APELLIDO = ? , FECHA_NACIMIENTO = ? ,"
				+ " NACIONALIDAD = ? , TELEFONO = ? , RESERVA_ID = ? WHERE ID = ?");
			statement.setString(1, nombre );
			statement.setString(2, apellido );
			statement.setDate(3, fechaNacimiento);
			statement.setString(4, nacionalidad);
			statement.setString(5, telefono);
			statement.setInt(6, id_reserva);
			statement.setInt(7, id);
			statement.execute();	

			int updateCount = statement.getUpdateCount();

			return updateCount;
		
	} catch (SQLException e) {
		throw new RuntimeException(e);
	}

}

public int eliminarHuesped(Integer id) {
	try {
		final PreparedStatement statement = con.prepareStatement("DELETE FROM HUESPEDES WHERE ID = ?");
		
			statement.setInt(1, id);
			statement.execute();
			int updateCount = statement.getUpdateCount();

			return updateCount;
		
	} catch (SQLException e) {
		throw new RuntimeException(e);
	}
}


}
