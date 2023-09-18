package database.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Assert;
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
		
		Integer id = bookingService.saveBooking(LocalDateTime.of(2023,12,29,10,30),LocalDateTime.of(2023,12,30,10,30),PaymentMethodDTO.CREDIT);
		assertEquals((Integer) 123, id);
	}
	
	@Test
	public void testSaveGuest() {
		GuestDataDTO guestDataDTO=new GuestDataDTO("Gabriel","Salinas",LocalDateTime.now(),NationalityDTO.ARGENTIN,"2345777",1);
		Mockito.doReturn(guestDataDTO).when(guestDataDAO).save(any(GuestDataDTO.class));
		
		bookingService.saveGuest("Gabriel","Salinas",LocalDateTime.now(),NationalityDTO.ARGENTIN,"2345777",1);

		verify(guestDataDAO).save(any(GuestDataDTO.class));
	}
	
	@Test
	public void testLoadBookingList() {
		BookingDataDTO bookingDataDTO=new BookingDataDTO(LocalDateTime.now(),LocalDateTime.of(2023,12,12,10,30),PaymentMethodDTO.CASH,new BigDecimal("6000"));
		BookingDataDTO bookingDataDTO1=new BookingDataDTO(LocalDateTime.now(),LocalDateTime.of(2024,12,12,10,30),PaymentMethodDTO.CASH,new BigDecimal("7000"));
		BookingDataDTO bookingDataDTO2=new BookingDataDTO(LocalDateTime.now(),LocalDateTime.of(2025,12,12,10,30),PaymentMethodDTO.CASH,new BigDecimal("8000"));
		
		List<BookingDataDTO>listBookingDataDTO=new ArrayList<>();
		
		listBookingDataDTO.add(bookingDataDTO);
		listBookingDataDTO.add(bookingDataDTO1);
		listBookingDataDTO.add(bookingDataDTO2);
		
		Mockito.doReturn(listBookingDataDTO).when(bookingDataDAO).searchBookingList();
		
		List<BookingDataDTO> loadBookingList = bookingService.loadBookingList();
		
		verify(bookingDataDAO).searchBookingList();
		Assert.assertEquals(listBookingDataDTO,loadBookingList);
	}
	
	@Test
	public void testLoadBookingById() {
		BookingDataDTO bookingDataDTO=new BookingDataDTO(LocalDateTime.now(),LocalDateTime.of(2023,12,12,10,30),PaymentMethodDTO.CASH,new BigDecimal("6000"));
		bookingDataDTO.setId(1);
		BookingDataDTO bookingDataDTO1=new BookingDataDTO(LocalDateTime.now(),LocalDateTime.of(2024,12,12,10,30),PaymentMethodDTO.CASH,new BigDecimal("7000"));
		bookingDataDTO1.setId(2);
		BookingDataDTO bookingDataDTO2=new BookingDataDTO(LocalDateTime.now(),LocalDateTime.of(2025,12,12,10,30),PaymentMethodDTO.CASH,new BigDecimal("8000"));
		bookingDataDTO2.setId(3);
		
		List<BookingDataDTO>listBookingDataDTO=new ArrayList<>();
		
		listBookingDataDTO.add(bookingDataDTO);
		listBookingDataDTO.add(bookingDataDTO1);
		listBookingDataDTO.add(bookingDataDTO2);
		
		Mockito.doReturn(listBookingDataDTO.get(0)).when(bookingDataDAO).searchByIdBooking(1);
		Mockito.doReturn(listBookingDataDTO.get(1)).when(bookingDataDAO).searchByIdBooking(2);
		Mockito.doReturn(listBookingDataDTO.get(2)).when(bookingDataDAO).searchByIdBooking(3);
		
		BookingDataDTO loadedBooking = bookingService.loadBookingListById(1);
		BookingDataDTO loadedBooking1 = bookingService.loadBookingListById(2);
		BookingDataDTO loadedBooking2 = bookingService.loadBookingListById(3);
		
		assertEquals(bookingDataDTO, loadedBooking);
		assertEquals(bookingDataDTO1, loadedBooking1);
		assertEquals(bookingDataDTO2, loadedBooking2);
	}
	
	@Test
	public void testModifiyBooking() {

		BookingDataDTO bookingDataDTO1Modified=new BookingDataDTO(2,LocalDateTime.now(),LocalDateTime.of(2011,11,11,11,11),new BigDecimal("90000"),PaymentMethodDTO.CASH);
		Mockito.doReturn(1).when(bookingDataDAO).modify(bookingDataDTO1Modified);
		
		int modifyBooking = bookingService.modifyBooking(bookingDataDTO1Modified);
		
		verify(bookingDataDAO).modify(bookingDataDTO1Modified);
		
		assertEquals(1,modifyBooking);
	}
	
	@Test
	public void testDeleteBooking() {
		Mockito.doReturn(1).when(bookingDataDAO).delete(3);
		
		int deleteBooking = bookingService.deleteBooking(3);
				
		verify(bookingDataDAO).delete(3);
		
		assertEquals(1,deleteBooking);
	}
	@Test
	public void testIncorrectTimeEntryDate() {	
		try {
			BookingDataDTO bookingDataDTO=new BookingDataDTO(LocalDateTime.of(2023,12,30,10,30),LocalDateTime.of(2023,12,31,11,30),PaymentMethodDTO.CASH,new BigDecimal("6000"));
			bookingDataDTO.setId(123);
			Mockito.doReturn(bookingDataDTO).when(bookingDataDAO).save(any(BookingDataDTO.class));
			
			bookingService.saveBooking(LocalDateTime.of(2023,12,30,10,30),LocalDateTime.of(2023,12,30,11,30),PaymentMethodDTO.CREDIT);
		}catch(RuntimeException e) {
			Assert.assertNotNull(e);
			String error="Entry date can't be equal to departure date";
			Assert.assertEquals(error,e.getMessage());
		}
	}
	@Test
	public void testIncorrectDepartureTime() {
		try {			
			bookingService.saveBooking(LocalDateTime.of(2023,10,12,12,0,0),LocalDateTime.of(2023,10,12,12,0,1),PaymentMethodDTO.CASH);
			Assert.fail("This test should have failed");
		}catch(RuntimeException e) {
			Assert.assertNotNull(e);
			String error="Entry date can't be equal to departure date";
			Assert.assertEquals(error,e.getMessage());
		}
	}
	
	@Test
	public void testLoadGuestList() {
		GuestDataDTO guestDataDTO=new GuestDataDTO(2,"andrea","salinas",LocalDateTime.of(1990,12,12,10,30),NationalityDTO.ARGENTIN,"2345777",1);
		GuestDataDTO guestDataDTO2=new GuestDataDTO(3,"juan","cruz",LocalDateTime.of(1993,12,12,10,30),NationalityDTO.ARGENTIN,"2345778",13);
		GuestDataDTO guestDataDTO3=new GuestDataDTO(4,"harry","cruz",LocalDateTime.of(2019,12,12,10,30),NationalityDTO.ARGENTIN,"2345779",12);

		List<GuestDataDTO>listGuestDataDTO=new ArrayList<>();
		
		listGuestDataDTO.add(guestDataDTO);
		listGuestDataDTO.add(guestDataDTO2);
		listGuestDataDTO.add(guestDataDTO3);
		
		Mockito.doReturn(listGuestDataDTO).when(guestDataDAO).searchGuestList();
		
		List<GuestDataDTO> loadGuestList = bookingService.loadGuestList();
		
		verify(guestDataDAO).searchGuestList();
		Assert.assertEquals(listGuestDataDTO,loadGuestList);
	}
	
	@Test
	public void testLoadGuestById() {
		GuestDataDTO guestDataDTO=new GuestDataDTO(2,"andrea","salinas",LocalDateTime.of(1990,12,12,10,30),NationalityDTO.ARGENTIN,"2345777",1);
		guestDataDTO.setId(1);
		GuestDataDTO guestDataDTO1=new GuestDataDTO(3,"juan","cruz",LocalDateTime.of(1993,12,12,10,30),NationalityDTO.ARGENTIN,"2345778",13);
		guestDataDTO.setId(2);
		GuestDataDTO guestDataDTO2=new GuestDataDTO(4,"harry","cruz",LocalDateTime.of(2019,12,12,10,30),NationalityDTO.ARGENTIN,"2345779",12);
		guestDataDTO.setId(3);
		
		List<GuestDataDTO>listGuestDataDTO=new ArrayList<>();
		
		listGuestDataDTO.add(guestDataDTO);
		listGuestDataDTO.add(guestDataDTO1);
		listGuestDataDTO.add(guestDataDTO2);
		
		Mockito.doReturn(listGuestDataDTO.get(0)).when(guestDataDAO).searchByIdGuest(1);
		Mockito.doReturn(listGuestDataDTO.get(1)).when(guestDataDAO).searchByIdGuest(2);
		Mockito.doReturn(listGuestDataDTO.get(2)).when(guestDataDAO).searchByIdGuest(3);
		
		GuestDataDTO loadedGuest = bookingService.loadGuestById(1);
		GuestDataDTO loadedGuest1 = bookingService.loadGuestById(2);
		GuestDataDTO loadedGuest2 = bookingService.loadGuestById(3);
		
		assertEquals(guestDataDTO, loadedGuest);
		assertEquals(guestDataDTO1, loadedGuest1);
		assertEquals(guestDataDTO2, loadedGuest2);
	}

	@Test
	public void testModifyGuest() {
	Mockito.doReturn(1).when(guestDataDAO).modify(any(GuestDataDTO.class));

	int modifyGuest= bookingService.modifyGuest("andrea","salinas",LocalDateTime.of(1990,12,12,10,30),NationalityDTO.ARGENTIN,"2345777",1);

	verify(guestDataDAO).modify(any(GuestDataDTO.class));

	assertEquals(1,modifyGuest);
	}
	
	@Test
	public void testDeleteGuest() {
		Mockito.doReturn(1).when(guestDataDAO).delete(3);
		
		int deleteGuest = bookingService.deleteGuest(3);
				
		verify(guestDataDAO).delete(3);
		
		assertEquals(1,deleteGuest);
	}
	
	@Test	
	public void testNullNameGuest() {	
		try {
			bookingService.saveGuest(null,"salinas",LocalDateTime.of(1990,12,12,10,30),NationalityDTO.ARGENTIN,"2345777",1);
		}catch(RuntimeException e) {
			Assert.assertNotNull(e);
			String error="Name was null";
			Assert.assertEquals(error,e.getMessage());
		}
	}
	
	@Test
	public void testNullLastNameGuest() {	
		try {
			bookingService.saveGuest("andrea",null,LocalDateTime.of(1990,12,12,10,30),NationalityDTO.ARGENTIN,"2345777",1);
		}catch(RuntimeException e) {
			Assert.assertNotNull(e);
			String error="Lastname was null";
			Assert.assertEquals(error,e.getMessage());
		}
	}
	
	@Test
	public void testBirthdateIncorrect() {	
		try {
			bookingService.saveGuest("andrea","salinas",LocalDateTime.of(2023,12,12,10,30),NationalityDTO.ARGENTIN,"2345777",1);
		}catch(RuntimeException e) {
			Assert.assertNotNull(e);
			String error="Birthdate can't be before today";
			Assert.assertEquals(error,e.getMessage());
		}
	}
	
	@Test
	public void testIdSearchLessThanZero() {	
		try {
			Random rand=new Random();	
			int number=rand.nextInt(1000)-1000;
			bookingService.loadGuestById(number);
			System.out.println(number);
			
		}catch(RuntimeException e) {
			Assert.assertNotNull(e);
			String error="id can't be less than 0";
			Assert.assertEquals(error,e.getMessage());
		}
	}
	
	@Test
	public void testIdDeleteLessThanZero() {	
		try {
			Random rand=new Random();	
			int number=rand.nextInt(1000)-1000;
			bookingService.deleteGuest(number);
		}catch(RuntimeException e) {
			Assert.assertNotNull(e);
			String error="id can't be less than 0";
			Assert.assertEquals(error,e.getMessage());
		}
	}	
}

