package Registro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import factory.connectionFactory;
import modelo.Reservas;

public class RegistroResevas {
	
	private Connection con;

	public RegistroResevas (Connection con) {
		this.con = con;
	};
	
	public void guardar(Reservas reservas) {
		
		try{
			con.setAutoCommit(false);
            final PreparedStatement statement = con.prepareStatement(
                    "INSERT INTO reservas" +
                            "(FECHA_ENTRADA,FECHA_SALIDA,VALOR,FORMAPAGO) VALUES " +
                            "(?,?,?,?)", Statement.RETURN_GENERATED_KEYS
            );
            try(statement){
                ejecutar(statement,reservas);
                con.commit();
            }
        } catch (SQLException e){
            
            throw new RuntimeException(e);
        }
		
	}

	private void ejecutar(PreparedStatement statement, Reservas reserva) throws SQLException {
        statement.setDate(1,reserva.getFecha_entrada());
        statement.setDate(2,reserva.getFecha_salida());
        statement.setString(3,reserva.getValor());
        statement.setString(4,reserva.getForma_de_pago());

        statement.executeUpdate();

         
        try(ResultSet resultSet = statement.getGeneratedKeys()) {
            while (resultSet.next()) {
                reserva.setId((resultSet.getInt(1)));
            }
        }
    }
	
	public List<Reservas> listar() throws SQLException{
		List<Reservas> listaReserva = new ArrayList<>();
		Connection con = new connectionFactory().getConexion();
		try {
			PreparedStatement statement = con.prepareStatement("SELECT ID,"
					+ "FECHA_ENTRADA,"
					+ "FECHA_SALIDA,"
					+ "VALOR,"
					+ "FORMAPAGO FROM reservas");
			
			statement.execute();
			ResultSet resultSet = statement.getResultSet();
			
			while (resultSet.next()) {
				Reservas reserva = new Reservas( 
						resultSet.getDate("FECHA_ENTRADA"), 
						resultSet.getDate("FECHA_SALIDA"),
						resultSet.getString("VALOR"), 
						resultSet.getString("FORMAPAGO"));
				
				reserva.setId(resultSet.getInt("ID"));
				
				listaReserva.add(reserva);
			
				
			}
			
			return  listaReserva;
			
		}catch (SQLException e) {
            throw new RuntimeException(e);
        }
		
	}
	
	public void actualizar(Reservas reserva) {
		
        try (PreparedStatement statement = con.prepareStatement(
                    "UPDATE reservas SET FECHA_ENTRADA =?, FECHA_SALIDA =?, VALOR =?, FORMAPAGO =? WHERE ID =?")) {
                statement.setDate(1, reserva.getFecha_entrada());
                statement.setDate(2, reserva.getFecha_salida());
                statement.setString(3, reserva.getValor());
                statement.setString(4, reserva.getForma_de_pago());
                statement.setInt(5, reserva.getId());
                
                 statement.execute();
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
	
	public int eliminar(int id) {
        try {
            final PreparedStatement statement = con.prepareStatement(
                    "DELETE FROM reservas WHERE ID = ?");
            try (statement) {
                statement.setInt(1, id);
                statement.execute();
                return statement.getUpdateCount();
            }
        } catch (SQLException e) {
        	throw new RuntimeException(e);
        }
		
    }
	
	public List<Reservas> bucarReserva(String id) throws SQLException{
		List<Reservas> listaReserva = new ArrayList<>();
		Connection con = new connectionFactory().getConexion();
		PreparedStatement statement = con.prepareStatement("SELECT ID,"
				+ "FECHA_ENTRADA,"
				+ "FECHA_SALIDA,"
				+ "VALOR,"
				+ "FORMAPAGO FROM reservas WHERE ID = ?");
		
		statement.setString(1, id);;
		statement.execute();
		ResultSet resultSet = statement.getResultSet();
		
		while (resultSet.next()) {
			Reservas reserva = new Reservas(
					resultSet.getDate("FECHA_ENTRADA"), 
					resultSet.getDate("FECHA_SALIDA"),
					resultSet.getString("VALOR"), 
					resultSet.getString("FORMAPAGO"));
			
			reserva.setId(resultSet.getInt("ID"));	
			listaReserva.add(reserva);
		
		}
		return  listaReserva;
	}
	
}
