package database.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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
import database.dao.GuestDataDAO;
import database.dto.BookingDataDTO;
import database.dto.GuestDataDTO;
import database.dto.NationalityDTO;
import database.dto.PaymentMethodDTO;
import service.BookingService;

@RunWith(JUnit4.class)
public class BookingServiceTest {
	
	@Mock
	private BookingDataDAO bookingDataDAO;
	
	@Mock
	private GuestDataDAO guestDataDAO;
	
	private BookingService bookingService;
	
	@Before 
	public void setUp() throws SQLException {
		MockitoAnnotations.openMocks(this);
		bookingService = new BookingService(bookingDataDAO,guestDataDAO);
	}
	
	@Test
	public void testSaveBooking() {
		BookingDataDTO bookingDataDTO=new BookingDataDTO(LocalDateTime.now(),LocalDateTime.of(2023,12,12,10,30),PaymentMethodDTO.CASH,new BigDecimal("6000"));
		bookingDataDTO.setId(123);
		Mockito.doReturn(bookingDataDTO).when(bookingDataDAO).save(any(BookingDataDTO.class));
		
		Integer id = bookingService.saveBooking(LocalDateTime.now(),LocalDateTime.of(2023,12,30,10,30),PaymentMethodDTO.CREDIT);
		assertEquals((Integer) 123, id);
	}
	
	@Test
	public void testSaveGuest() {
		GuestDataDTO guestDataDTO=new GuestDataDTO("Gabriel","Salinas",LocalDateTime.now(),NationalityDTO.ARGENTIN,"2345777",1);
		Mockito.doReturn(guestDataDTO).when(guestDataDAO).save(any(GuestDataDTO.class));
		
		bookingService.saveGuest("Gabriel","Salinas",LocalDateTime.now(),NationalityDTO.ARGENTIN,"2345777",1);

		verify(guestDataDAO).save(any(GuestDataDTO.class));
	}
}
