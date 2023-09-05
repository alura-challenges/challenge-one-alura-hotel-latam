package controlador;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import Registro.RegistroResevas;
import factory.connectionFactory;
import modelo.Reservas;

public class ReservasControlador {

	private RegistroResevas rsesevas;
	private Connection con;
	
	
	public ReservasControlador() throws SQLException {
		this.con = new connectionFactory().getConexion();
		this.rsesevas = new RegistroResevas(con); 
	}
	
	public void guardar(Reservas reserva) throws SQLException {
		 this.rsesevas.guardar(reserva);
	}
	
	public  List<Reservas> listar() throws SQLException{
		return this.rsesevas.listar();
	}
	
	public  void actualizar(Reservas reserva) {
		this.rsesevas.actualizar(reserva);
	}
	
	public int eliminar(int id) {
		return this.rsesevas.eliminar(id);
	}
	
	public  List<Reservas> buscarReservas(String id) throws SQLException{
		return this.rsesevas.bucarReserva(id);
	}
}
