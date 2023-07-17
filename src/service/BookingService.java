package service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;

import database.dao.BookingDataDAO;
import database.dto.BookingDataDTO;
import database.dto.PaymentMethodDTO;

public class BookingService {
	
	private final BookingDataDAO bookingDataDAO;
	private final BigDecimal pricePerNight=new BigDecimal("5000");
	private BigDecimal finalPrice = null;
	
	public BookingService() {
		bookingDataDAO=new BookingDataDAO();
	}

	public BookingService(BookingDataDAO bookingDataDAO) {
		this.bookingDataDAO=bookingDataDAO;
	}
	
	public Integer saveBooking(LocalDateTime entryDate, LocalDateTime departureDate, PaymentMethodDTO methodPayment)  {
		Duration duration=Duration.between(entryDate, departureDate);
		BigDecimal days=new BigDecimal(duration.toDays());
		
		BookingDataDTO bookingDataDTO=new BookingDataDTO(entryDate,departureDate,methodPayment,bookingPrice(days));
		BookingDataDTO saveBooking = bookingDataDAO.save(bookingDataDTO);
		return saveBooking.getId();
	}

	private final BigDecimal bookingPrice(BigDecimal days) {
		return  finalPrice=pricePerNight.multiply(days);
	}
}
