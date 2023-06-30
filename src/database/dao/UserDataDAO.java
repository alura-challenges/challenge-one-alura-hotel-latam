package database.dao;
//hola mundo
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import database.ConnectionManager;
import database.dto.UserDataDTO;

public class UserDataDAO {
	
	private Connection con;
	private static final String SELECT_USER_BY_LOGIN="SELECT id, login, password FROM user_data WHERE login=?";
	
	private synchronized Connection getConnection() {
		if (con == null) {
			con = new ConnectionManager().getConnection();
		} else {
			try {
				if (con.isClosed()) {
					con = new ConnectionManager().getConnection();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				con = new ConnectionManager().getConnection();
			}
		}
		return con;
	}
	
	public UserDataDTO getUserByLogin(String login){
		
		UserDataDTO userDataDTO = null;
		Connection con= getConnection();
		
		try {
			final PreparedStatement statement=con.prepareStatement(SELECT_USER_BY_LOGIN);
			
			try(statement){
				statement.setString(1,login);
				statement.execute();
				final ResultSet resultSet=statement.getResultSet();
					
					try(resultSet){
						if(resultSet.next()) {
								userDataDTO=new UserDataDTO(
								resultSet.getInt("id"),
								resultSet.getString("login"),
								resultSet.getString("password"));
						}
					}
				}
				return userDataDTO;
			}catch(SQLException e) {
				throw new RuntimeException(e);
			}
		}
}
	
	
	


