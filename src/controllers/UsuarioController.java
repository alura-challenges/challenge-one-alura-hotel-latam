package controllers;

import dao.UsuarioDAO;
import factory.ConnectionFactory;
import models.Usuario;

public class UsuarioController {

	private UsuarioDAO usuarioDAO;
	
	public UsuarioController() {
		ConnectionFactory factory = new ConnectionFactory();
		this.usuarioDAO = new UsuarioDAO(factory.recuperaConexion());
	}
	
	public Usuario login(Usuario user) {
		return this.usuarioDAO.login(user);
	}

}
