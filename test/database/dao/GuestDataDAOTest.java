package database.dao;

import java.sql.SQLException;
import java.time.LocalDateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import database.dto.GuestDataDTO;
import database.dto.NationalityDTO;

@RunWith(JUnit4.class)
public class GuestDataDAOTest {
	private GuestDataDAO guestDataDAO = new GuestDataDAO();

	@Test
	public void testSaveGuest() throws SQLException {
		GuestDataDTO guest=new GuestDataDTO("Juliana","Salinas",LocalDateTime.now(),NationalityDTO.ARGENTIN,"1234567",1);	
		
		 guestDataDAO.save(guest);		
	}

}
