package Controllers;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import DAO.HuespedDAO;
import Entities.Huesped;
import Factory.ConnectionFactory;

public class HuespedController {
	
	private HuespedDAO huesped;

	public HuespedController() {
		this.huesped = new HuespedDAO(new ConnectionFactory().recuperateConnection());
	}
	
	
	public List<Huesped> listar() {
		return huesped.listar();
	}
	
	public void guardar(Huesped usuario) {
    	huesped.guardar(usuario);
	}

	public int modificar(Integer id, String nombre,  String apellido, LocalDate fecha_nac, String nacionalidad, Long telefono)  {
		return huesped.modificar(id, nombre, apellido, fecha_nac,  nacionalidad, telefono);
	}

	public int eliminar(Integer id) {
		return huesped.eliminar(id);
	}
}
