package controllers;

import java.util.Date;
import java.util.List;

import dao.HuespedDAO;
import factory.ConnectionFactory;
import models.Huesped;

public class HuespedController {

	private HuespedDAO huespedDAO;
	
	public HuespedController() {
		ConnectionFactory con = new ConnectionFactory();
		this.huespedDAO = new HuespedDAO(con.recuperaConexion());
		
	}

	public void guardar(Huesped huesped) {
		huespedDAO.guardar(huesped);
		
	}

	public List<Huesped> listar() {
		return huespedDAO.listar();
	}

	public int modificar(Integer id, String nombre, String apellido, Date fechaNacimiento, String nacionalidad,
			String telefono, Integer idReserva) {
		return huespedDAO.modificar(id, nombre, apellido, fechaNacimiento, nacionalidad, telefono, idReserva);
	}

	public int eliminar(Integer id) {
		return huespedDAO.eliminar(id);
	}
	
}
