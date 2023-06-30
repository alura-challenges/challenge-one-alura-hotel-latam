package database.dao;

import java.sql.SQLException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import database.dto.UserDataDTO;
import junit.framework.Assert;

@RunWith(JUnit4.class)
public class UserDataDAOTest {
	
	private UserDataDAO userDataDAO = new UserDataDAO();

	@Test
	public void testGetUserByLogin() throws SQLException {
		UserDataDTO userByLogin = userDataDAO.getUserByLogin("cbeltran");
		
		String userLogin=userByLogin.getLogin();

		Assert.assertNotNull(userLogin);	
		
	}
}
