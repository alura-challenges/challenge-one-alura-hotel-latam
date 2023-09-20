package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.UserData;

public class UserDataDAO extends MainDAO{
	
	private Connection con;
	private static final String SELECT_USER_BY_LOGIN="SELECT id, login, password FROM user_data WHERE login=?";
	

	public UserData getUserByLogin(String login){
		
		UserData userData = null;
		con= super.getConnection();
		
		try {
			final PreparedStatement statement=con.prepareStatement(SELECT_USER_BY_LOGIN);
			
			try(statement){
				statement.setString(1,login);
				statement.execute();
				final ResultSet resultSet=statement.getResultSet();
					
					try(resultSet){
						if(resultSet.next()) {
								userData=new UserData(
								resultSet.getInt("id"),
								resultSet.getString("login"),
								resultSet.getString("password"));
						}
					}
				}
				return userData;
			}catch(SQLException e) {
				throw new RuntimeException(e);
			}
		}	
}
	


