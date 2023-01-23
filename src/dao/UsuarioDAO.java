package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.Usuario;

public class UsuarioDAO {
	
	private Connection con;

	public UsuarioDAO(Connection con) {
		this.con = con;
	}

	public Usuario login(Usuario user) {
		
		try {
			final PreparedStatement statement = con.prepareStatement("SELECT contraseña FROM usuario WHERE usuario = ?");
			
			try (statement) {
				statement.setString(1, user.getUsuario());
				
				final ResultSet resultSet = statement.executeQuery();
				
				try (resultSet) {
					while (resultSet.next()) {
						user.setContraseña(resultSet.getString(1));
						//System.out.println(String.format("Fue creada la reserva: %s", reserva));
					}
				}
				
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return user;
	}
}
