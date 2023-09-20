package service;

import database.dao.UserDataDAO;
import model.UserData;

public class UserService {
	
	private final UserDataDAO userDataDAO;
	
	public UserService() {
		userDataDAO = new UserDataDAO();
	}
	
	public UserService(UserDataDAO userDataDAO) {
		this.userDataDAO = userDataDAO;
	}
	
	public boolean loginUser(String login, String password) {
		UserData userByLogin = userDataDAO.getUserByLogin(login);
		
		if(userByLogin!= null){
			if(userByLogin.getPassword().equals(password)){
				return true;
			}
		}
		return false;
	}
}