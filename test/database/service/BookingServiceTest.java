package database.service;

import static org.junit.Assert.assertEquals;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import database.dao.BookingDataDAO;
import database.dto.BookingDataDTO;
import database.dto.PaymentMethodDTO;
import service.BookingService;

@RunWith(JUnit4.class)
public class BookingServiceTest {
	
	@Mock
	private BookingDataDAO bookingDataDAO;
	
	private BookingService bookingService;
	
	@Before 
	public void setUp() throws SQLException {
		MockitoAnnotations.openMocks(this);
		bookingService = new BookingService(bookingDataDAO);
	}
	
	@Test
	public void testSaveBooking() {
		BookingDataDTO bookingDataDTO=new BookingDataDTO(LocalDateTime.now(),LocalDateTime.of(2023,12,12,10,30),PaymentMethodDTO.CASH,new BigDecimal("6000"));
		bookingDataDTO.setId(123);
		Mockito.doReturn(bookingDataDTO).when(bookingDataDAO).save(bookingDataDTO);
		
		Integer id = bookingService.saveBooking(LocalDateTime.now(),LocalDateTime.of(2023,12,12,10,30),PaymentMethodDTO.CASH);
		assertEquals((Integer) 123, id);
	}
}
