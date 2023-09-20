package database.service;

import static org.mockito.ArgumentMatchers.eq;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import database.dao.UserDataDAO;
import model.UserData;
import service.UserService;

@RunWith(JUnit4.class)
public class UserServiceTest {
	
	@Mock
	private UserDataDAO userDataDAO;	
	private UserService userService;
	
	@Before 
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		userService = new UserService(userDataDAO);
	}
	
	@Test
	public void testLoginUser() {
		UserData mockedUser = new UserData(1, "cbeltran", "goodpassword");
		Mockito.doReturn(mockedUser).when(userDataDAO).getUserByLogin(eq("cbeltran"));
		
		boolean loginUser = userService.loginUser("cbeltran", "goodpassword");
		Assert.assertTrue("Couln't login user", loginUser);
	}
	
	@Test
	public void testNoLoginUser() {
		UserData mockedUser = new UserData(1, "cbeltran", "goodpassword");
		Mockito.doReturn(mockedUser).when(userDataDAO).getUserByLogin("cbeltran");
		
		boolean loginUser = userService.loginUser("dbeltran", "goodpassword");
		Assert.assertFalse("User shouldn't have login", loginUser);
	}


}
