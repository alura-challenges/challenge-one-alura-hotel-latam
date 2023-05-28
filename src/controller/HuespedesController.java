package controller;

import java.sql.Date;
import java.util.List;

import dao.HuespedDAO;
import factory.ConnectionFactory;
import modelo.Huespedes;

public class HuespedesController {
	
	private HuespedDAO huespedDAO;

	public HuespedesController() {
		
		ConnectionFactory factory = new ConnectionFactory();
		
		this.huespedDAO = new HuespedDAO(factory.recuperaConexion());
	
	}
	
	public void guardar (Huespedes huesped) {
		huespedDAO.guardar(huesped);
	}
	
	public List<Huespedes> listarHuespedes(){
		return huespedDAO.listarHuespedes();
	}
	
	public List<Huespedes> listarHuespedesApellido(String apellido){
		return huespedDAO.listarHuespedesApellido(apellido);
	}
	
	public int modificarHuesped (Integer id, String nombre, String apellido, Date fechaNacimiento, String nacionalidad,
			String telefono, Integer id_reserva) {
		return huespedDAO.modificarHuesped(id, nombre, apellido, fechaNacimiento, nacionalidad, telefono, id_reserva);
		
	}
	
	public int eliminarHuesped(Integer id) {
		return huespedDAO.eliminarHuesped(id);
	}

}
