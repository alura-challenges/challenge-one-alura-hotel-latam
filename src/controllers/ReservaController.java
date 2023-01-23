package controllers;

import java.util.Date;
import java.util.List;

import dao.ReservaDAO;
import factory.ConnectionFactory;
import models.Reserva;

public class ReservaController {
	
	private ReservaDAO reservaDAO;
	
	public ReservaController() {
		ConnectionFactory factory = new ConnectionFactory();
		this.reservaDAO = new ReservaDAO(factory.recuperaConexion());
	}


	public void guardar(Reserva reserva) {
		reservaDAO.guardar(reserva);
	}

	public List<Reserva> listar() {
		return reservaDAO.listar();
	}

	public List<Reserva> cargarBusqueda(String text) {
		return reservaDAO.listarConHuespedes(text);
	}

	public int modificar(Integer id, Date fechaEntrada, Date fechaSalida, Double valor, String formaPago) {
		return reservaDAO.modificar(id, fechaEntrada, fechaSalida, valor, formaPago);
	}

	public int eliminar(Integer id) {
		return reservaDAO.eliminar(id);
	}

}
