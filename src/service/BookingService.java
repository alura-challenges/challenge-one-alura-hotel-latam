package service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import config.Setting;
import database.dao.BookingDataDAO;
import database.dao.GuestDataDAO;
import model.BookingData;
import model.GuestData;
import model.Nationality;
import model.PaymentMethod;

public class BookingService {
	
	private final BookingDataDAO bookingDataDAO;
	private final GuestDataDAO guestDataDAO;
	private final Setting setting;
	
	public BookingService() {
		this.bookingDataDAO=new BookingDataDAO();
		this.guestDataDAO = new GuestDataDAO();
		this.setting = new Setting();
	}
	
	public BookingService(BookingDataDAO bookingDataDAO,GuestDataDAO guestDataDAO) {
		this.bookingDataDAO=bookingDataDAO;
		this.guestDataDAO = guestDataDAO;
		this.setting = new Setting();
	}

	public Integer saveBooking(LocalDateTime entryDate, LocalDateTime departureDate, PaymentMethod methodPayment)  {
		if (entryDate == null) {
			throw new IllegalArgumentException("Entry date was null");
		}
		if (departureDate == null) {
			throw new IllegalArgumentException("Departure date was null");
		}
		if (methodPayment == null) {
			throw new IllegalArgumentException("Method payment was null");
		}		
		if (departureDate.toLocalDate().isBefore(entryDate.toLocalDate())){
			throw new IllegalArgumentException("Departure date can't be before entry date ");
		}
		if (departureDate.toLocalDate().isEqual(entryDate.toLocalDate())){
			throw new IllegalArgumentException("Entry date can't be equal to departure date");
		}
		if (entryDate.isBefore(LocalDateTime.now())) {
			throw new IllegalArgumentException("Entry date can't be before today");
		}
		
		Duration duration=Duration.between(entryDate, departureDate);
		BigDecimal days=new BigDecimal(duration.toDays());
		
		BookingData bookingData=new BookingData(entryDate,departureDate,methodPayment,bookingPrice(days));
		BookingData saveBooking = bookingDataDAO.save(bookingData);
		return saveBooking.getId();
	}

	private  BigDecimal bookingPrice(BigDecimal days) {
		final BigDecimal pricePerNight=new BigDecimal(setting.getProperty("price_per_night"));
		return  pricePerNight.multiply(days);
	}
	
	public void saveGuest(String name, String lastname,LocalDateTime birthDate, Nationality nationality, String phoneNumber, Integer idBooking) {
		if(name==null) {
			throw new IllegalArgumentException("Name was null");
		}
		if(lastname==null) {
			throw new IllegalArgumentException("Lastname was null");
		}
		
		if(birthDate!=null && birthDate.isAfter(LocalDateTime.now())) {
			throw new IllegalArgumentException("Birthdate can't be before today");
		}
		
		GuestData guestData=new GuestData(name,lastname,birthDate,nationality,phoneNumber,idBooking);	
		guestDataDAO.save(guestData);
	}

	public List<BookingData> loadBookingList() {
		return bookingDataDAO.searchBookingList();
	}
	
	public BookingData loadBookingListById(int idSearch) {
		return bookingDataDAO.searchByIdBooking(idSearch);
	}

	public int modifyBooking (BookingData bookingData) {
		return bookingDataDAO.modify(bookingData);
	}
	
	public int deleteBooking (int id){
		return bookingDataDAO.delete(id);
	}
	
	public List<GuestData> loadGuestList() {
		return guestDataDAO.searchGuestList();
	}
	
	public GuestData loadGuestById(int idSearch) {
		if(idSearch>0) {
			return guestDataDAO.searchByIdGuest(idSearch);
		}else {
			throw new IllegalArgumentException("id can't be less than 0");
		}
	}

	public int modifyGuest (String name, String lastname,LocalDateTime birthDate, Nationality nationality, String phoneNumber, Integer idBooking) {
		if(name==null) {
			throw new IllegalArgumentException("Name was null");
		}
		if(lastname==null) {
			throw new IllegalArgumentException("Lastname was null");
		}
		if(birthDate!=null) {
			if(birthDate.isAfter(LocalDateTime.now())) {
				throw new IllegalArgumentException("Birthdate can't be after today");
			}
		}

		GuestData guestData=new GuestData(name,lastname,birthDate,nationality,phoneNumber,idBooking);	
		return guestDataDAO.modify(guestData);
	}
	
	public int deleteGuest (int id){
		if(id>0) {
			return guestDataDAO.delete(id); 
		}else {
			throw new IllegalArgumentException("id can't be less than 0");
		}
	}
}
