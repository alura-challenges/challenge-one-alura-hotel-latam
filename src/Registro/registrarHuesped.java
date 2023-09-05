package Registro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import factory.connectionFactory;
import modelo.Huespedes;

import java.sql.SQLException;

public class registrarHuesped {
	
	private Connection con;
 
	public  registrarHuesped(Connection con){
		this.con = con;
    }
    
    public void guardar(Huespedes huesped) {
    	
        try{
            con.setAutoCommit(false);
            final PreparedStatement statement = con.prepareStatement(
                    "INSERT INTO HUESPEDES" +
                            "(NOMBRE,APELLIDO, FECHA_DE_NACIMIENTO,NACIONALIDAD,TELEFONO,ID_RESERVA) VALUES " +
                            "(?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS
            );
            try(statement){
                ejecutarRegistro(statement,huesped);
                con.commit();
            }
        } catch (SQLException e){
            
            throw new RuntimeException(e);
        }
		}

	private void ejecutarRegistro(PreparedStatement statement, Huespedes huesped) throws SQLException {
		statement.setString(1, huesped.getNombre());
		statement.setString(2, huesped.getApellido());
		statement.setDate(3, huesped.getFecha_de_nacimiento());
		statement.setString(4, huesped.getNacionalidad());
		statement.setString(5, huesped.getTelefono());
		statement.setInt(6, huesped.getId_reseva());
		
		statement.execute();
		
        try(ResultSet resultSet = statement.getGeneratedKeys()) {
            while (resultSet.next()) {
                huesped.setId(resultSet.getInt(1));
            }
        }
	}

	public List<Huespedes> listar() throws SQLException{
			List<Huespedes> huespedList = new ArrayList<>();
			try {
				PreparedStatement statement = con.prepareStatement("SELECT ID," +
		                "NOMBRE," +
		                "APELLIDO," +
		                "FECHA_DE_NACIMIENTO," +
		                "NACIONALIDAD," +
		                "TELEFONO," +
		                "ID_RESERVA " +
		                "FROM HUESPEDES");
				
				statement.execute();
				ResultSet resultSet = statement.getResultSet();
				
				while (resultSet.next()) {
					Huespedes huespedes = new Huespedes( 
		
							resultSet.getInt(1),
		                    resultSet.getString(2),
		                    resultSet.getString(3),
		                    resultSet.getDate(4),
		                    resultSet.getString(5),
		                    resultSet.getString(6), 
		                    resultSet.getInt(7));
					
					huespedList.add(huespedes);
				}
				return  huespedList;
				
			}catch (SQLException e) {
	            throw new RuntimeException(e);
	        }
			  
		}
	
	public void actualizar(Huespedes huespedes) {
        try {
            final PreparedStatement statement = con.prepareStatement(
                    "UPDATE huespedes SET (NOMBRE =?, APELLIDO =?, FECHA_DE_NACIMIENTO =?, NACIONALIDAD =?, TELEFONO =?, ID_RESERVA =? WHERE ID =?)");
            try (statement) {
                statement.setString(1, huespedes.getNombre());
                statement.setString(2, huespedes.getApellido());
                statement.setDate(3, huespedes.getFecha_de_nacimiento());
                statement.setString(4, huespedes.getNacionalidad());
                statement.setString(5, huespedes.getTelefono());
                statement.setInt(6, huespedes.getId_reseva());
                statement.setInt(0, huespedes.getId());
                statement.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
	
	public int eliminar(Integer id) {
        try {
            final PreparedStatement statement = con.prepareStatement(
                    "DELETE FROM huespedes WHERE ID = ?");
            try (statement) {
                statement.setInt(1, id);
                statement.execute();
                return statement.getUpdateCount();
            }
        } catch (SQLException e) {
        	throw new RuntimeException(e);
        }
    }
	
	public List<Huespedes> buscarHuesped(String id) throws SQLException{
		List<Huespedes> huespedList = new ArrayList<>();
		Connection con = new connectionFactory().getConexion();
		PreparedStatement statement = con.prepareStatement("SELECT ID," +
                "NOMBRE," +
                "APELLIDO," +
                "FECHA_DE_NACIMIENTO," +
                "NACIONALIDAD," +
                "TELEFONO," +
                "ID_RESERVA" +
                "FROM HUESPEDES WERE ID = ?");
		
		statement.setString(1, id);
		statement.execute();
		ResultSet resultSet = statement.getResultSet();
		
		while (resultSet.next()) {
			Huespedes huespedes = new Huespedes( 

					resultSet.getInt("ID"),
                    resultSet.getString("NOMBRE"),
                    resultSet.getString("APELLIDO"),
                    resultSet.getDate("FECHA_DE_NACIMIENTO"),
                    resultSet.getString("NACIONALIDAD"),
                    resultSet.getString("TELEFONO"), 
                    resultSet.getInt("ID_RESERVA")
            );
			huespedList.add(huespedes);	
		}
		return  huespedList;
		  
	}

	
}
