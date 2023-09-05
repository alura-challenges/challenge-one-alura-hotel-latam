package controlador;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import Registro.registrarHuesped;
import factory.connectionFactory;
import modelo.Huespedes;

public class RegistroControlador {
	
	private registrarHuesped huespedes;
	private Connection con;
	
	
	public RegistroControlador() throws SQLException {
		this.con = new connectionFactory().getConexion();
		this.huespedes = new registrarHuesped(con);
	}
	
	public void guardar(Huespedes huespedes) {
		this.huespedes.guardar(huespedes);
	}

	public  List<Huespedes> listar() throws SQLException{
		return this.huespedes.listar();
	}
	
	public  void actualizar(Huespedes huespedes) {
		this.huespedes.actualizar(huespedes);
	}
	
	public void eliminar(Integer id) {
		this.huespedes.eliminar(id);
	}
	
	public  List<Huespedes> buscarHuespedes(String id) throws SQLException{
		return this.huespedes.buscarHuesped(id);
	}
}
