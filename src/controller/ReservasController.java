package controller;


import java.sql.Date;
import java.util.List;

import dao.ReservaDAO;
import factory.ConnectionFactory;
import modelo.Reservas;

public class ReservasController {
	
	private ReservaDAO reservaDAO;

	public ReservasController() {
		ConnectionFactory factory = new ConnectionFactory();
		
		this.reservaDAO = new ReservaDAO(factory.recuperaConexion());
		
	}
	
	public void guardar (Reservas reserva) {
		reservaDAO.guardar(reserva);
	}
	
	public List<Reservas> listarReservas (){
		return reservaDAO.listarReservas();
	}
	
	public List<Reservas> listarReservasId (int id){
		return reservaDAO.listarReservasId(id);
	}
	
	public int modificarReserva (Date fechaEntrada, Date fechaSalida, String valor, String formaPago, int id ) {
		return reservaDAO.modificarReserva(fechaEntrada, fechaSalida, valor, formaPago, id);
		
	}
	
	public int eliminarReserva(Integer id) {
		return reservaDAO.eliminarReserva(id);
	}

}
