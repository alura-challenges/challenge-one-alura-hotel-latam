package service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import config.Setting;
import database.dao.BookingDataDAO;
import database.dto.BookingDataDTO;
import database.dto.PaymentMethodDTO;

public class BookingService {
	
	private final BookingDataDAO bookingDataDAO;
	private final Setting setting;
	
	
	public BookingService() {
		this.bookingDataDAO=new BookingDataDAO();
		this.setting = new Setting();
	}

	public BookingService(BookingDataDAO bookingDataDAO) {
		this.bookingDataDAO=bookingDataDAO;
		this.setting = new Setting();
	}
	
	public Integer savedBooking(LocalDateTime entryDate, LocalDateTime departureDate, PaymentMethodDTO methodPayment)  {
		Duration duration=Duration.between(entryDate, departureDate);
		BigDecimal days=new BigDecimal(duration.toDays());
		
		BookingDataDTO bookingDataDTO=new BookingDataDTO(entryDate,departureDate,methodPayment,bookingPrice(days));
		BookingDataDTO saveBooking = bookingDataDAO.save(bookingDataDTO);
		return saveBooking.getId();
	}

	private  BigDecimal bookingPrice(BigDecimal days) {
		final BigDecimal pricePerNight=new BigDecimal(setting.getProperty("price_per_night"));
		return  pricePerNight.multiply(days);
	}
}
