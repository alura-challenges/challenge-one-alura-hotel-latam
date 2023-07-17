package database.dao;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import database.dto.BookingDataDTO;
import database.dto.PaymentMethodDTO;

@RunWith(JUnit4.class)
public class BookingDataDAOTest {
		
	private BookingDataDAO bookingDataDAO = new BookingDataDAO();

	@Test
	public void testSaveBooking() throws SQLException {
		BookingDataDTO booking=new BookingDataDTO(LocalDateTime.now(),LocalDateTime.of(2023,12,12,10,30),PaymentMethodDTO.CASH,new BigDecimal("6000"));
		
		 bookingDataDAO.save(booking);
		
		System.out.println("estuve aquí");
		}
	}
