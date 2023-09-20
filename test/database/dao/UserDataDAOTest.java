package database.dao;

import java.sql.SQLException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import model.UserData;

import org.junit.Assert;

@RunWith(JUnit4.class)
public class UserDataDAOTest {
	
	private UserDataDAO userDataDAO = new UserDataDAO();

	@Test
	public void testGetUserByLogin() throws SQLException {
		UserData userByLogin = userDataDAO.getUserByLogin("cbeltran");
		
		String userLogin=userByLogin.getLogin();

		Assert.assertNotNull(userLogin);	
		
	}
}
