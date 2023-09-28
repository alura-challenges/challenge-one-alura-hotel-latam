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
import model.BookingData;
import model.GuestData;
import model.Nationality;
import model.PaymentMethod;
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
		BookingData bookingData=new BookingData(LocalDateTime.now(),LocalDateTime.of(2023,12,12,10,30),PaymentMethod.CASH,new BigDecimal("6000"));
		bookingData.setId(123);
		Mockito.doReturn(bookingData).when(bookingDataDAO).save(any(BookingData.class));
		
		BookingData bookingData2=new BookingData(LocalDateTime.of(2023,12,29,10,30),LocalDateTime.of(2023,12,30,10,30),PaymentMethod.CREDIT);
		Integer id = bookingService.saveBooking(bookingData2);
		assertEquals((Integer) 123, id);
	}
	
	@Test
	public void testSaveGuest() {
		GuestData guestData=new GuestData("Gabriel","Salinas",LocalDateTime.now(),Nationality.ARGENTIN,"2345777",1);
		Mockito.doReturn(guestData).when(guestDataDAO).save(any(GuestData.class));
		
		bookingService.saveGuest(guestData);

		verify(guestDataDAO).save(any(GuestData.class));
	}
	
	@Test
	public void testLoadBookingList() {
		BookingData bookingData=new BookingData(LocalDateTime.now(),LocalDateTime.of(2023,12,12,10,30),PaymentMethod.CASH,new BigDecimal("6000"));
		BookingData bookingData1=new BookingData(LocalDateTime.now(),LocalDateTime.of(2024,12,12,10,30),PaymentMethod.CASH,new BigDecimal("7000"));
		BookingData bookingData2=new BookingData(LocalDateTime.now(),LocalDateTime.of(2025,12,12,10,30),PaymentMethod.CASH,new BigDecimal("8000"));
		
		List<BookingData>listBookingData=new ArrayList<>();
		
		listBookingData.add(bookingData);
		listBookingData.add(bookingData1);
		listBookingData.add(bookingData2);
		
		Mockito.doReturn(listBookingData).when(bookingDataDAO).searchBookingList();
		
		List<BookingData> loadBookingList = bookingService.loadBookingList();
		
		verify(bookingDataDAO).searchBookingList();
		Assert.assertEquals(listBookingData,loadBookingList);
	}
	
	@Test
	public void testLoadBookingById() {
		BookingData bookingData=new BookingData(LocalDateTime.now(),LocalDateTime.of(2023,12,12,10,30),PaymentMethod.CASH,new BigDecimal("6000"));
		bookingData.setId(1);
		BookingData bookingData1=new BookingData(LocalDateTime.now(),LocalDateTime.of(2024,12,12,10,30),PaymentMethod.CASH,new BigDecimal("7000"));
		bookingData1.setId(2);
		BookingData bookingData2=new BookingData(LocalDateTime.now(),LocalDateTime.of(2025,12,12,10,30),PaymentMethod.CASH,new BigDecimal("8000"));
		bookingData2.setId(3);
		
		List<BookingData>listBookingData=new ArrayList<>();
		
		listBookingData.add(bookingData);
		listBookingData.add(bookingData1);
		listBookingData.add(bookingData2);
		
		Mockito.doReturn(listBookingData.get(0)).when(bookingDataDAO).searchByIdBooking(1);
		Mockito.doReturn(listBookingData.get(1)).when(bookingDataDAO).searchByIdBooking(2);
		Mockito.doReturn(listBookingData.get(2)).when(bookingDataDAO).searchByIdBooking(3);
		
		BookingData loadedBooking = bookingService.loadBookingListById(1);
		BookingData loadedBooking1 = bookingService.loadBookingListById(2);
		BookingData loadedBooking2 = bookingService.loadBookingListById(3);
		
		assertEquals(bookingData, loadedBooking);
		assertEquals(bookingData1, loadedBooking1);
		assertEquals(bookingData2, loadedBooking2);
	}
	
	@Test
	public void testModifiyBooking() {

		BookingData bookingData1Modified=new BookingData(2,LocalDateTime.now(),LocalDateTime.of(2011,11,11,11,11),new BigDecimal("90000"),PaymentMethod.CASH);
		Mockito.doReturn(1).when(bookingDataDAO).modify(bookingData1Modified);
		
		int modifyBooking = bookingService.modifyBooking(bookingData1Modified);
		
		verify(bookingDataDAO).modify(bookingData1Modified);
		
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
			BookingData bookingData=new BookingData(LocalDateTime.of(2023,12,30,10,30),LocalDateTime.of(2023,12,31,11,30),PaymentMethod.CASH,new BigDecimal("6000"));
			bookingData.setId(123);
			Mockito.doReturn(bookingData).when(bookingDataDAO).save(any(BookingData.class));
			
			BookingData bookingData2=new BookingData(LocalDateTime.of(2023,12,30,10,30),LocalDateTime.of(2023,12,30,11,30),PaymentMethod.CREDIT);
			bookingService.saveBooking(bookingData2);
		}catch(RuntimeException e) {
			Assert.assertNotNull(e);
			String error="Entry date can't be equal to departure date";
			Assert.assertEquals(error,e.getMessage());
		}
	}
	@Test
	public void testIncorrectDepartureTime() {
		try {
			BookingData bookingData=new BookingData(LocalDateTime.of(2023,10,12,12,0,0),LocalDateTime.of(2023,10,12,12,0,1),PaymentMethod.CASH);
			bookingService.saveBooking(bookingData);
			Assert.fail("This test should have failed");
		}catch(RuntimeException e) {
			Assert.assertNotNull(e);
			String error="Entry date can't be equal to departure date";
			Assert.assertEquals(error,e.getMessage());
		}
	}
	
	@Test
	public void testLoadGuestList() {
		GuestData guestData=new GuestData(2,"andrea","salinas",LocalDateTime.of(1990,12,12,10,30),Nationality.ARGENTIN,"2345777",1);
		GuestData guestData2=new GuestData(3,"juan","cruz",LocalDateTime.of(1993,12,12,10,30),Nationality.ARGENTIN,"2345778",13);
		GuestData guestData3=new GuestData(4,"harry","cruz",LocalDateTime.of(2019,12,12,10,30),Nationality.ARGENTIN,"2345779",12);

		List<GuestData>listGuestData=new ArrayList<>();
		
		listGuestData.add(guestData);
		listGuestData.add(guestData2);
		listGuestData.add(guestData3);
		
		Mockito.doReturn(listGuestData).when(guestDataDAO).searchGuestList();
		
		List<GuestData> loadGuestList = bookingService.loadGuestList();
		
		verify(guestDataDAO).searchGuestList();
		Assert.assertEquals(listGuestData,loadGuestList);
	}
	
	@Test
	public void testLoadGuestById() {
		GuestData guestData=new GuestData(2,"andrea","salinas",LocalDateTime.of(1990,12,12,10,30),Nationality.ARGENTIN,"2345777",1);
		guestData.setId(1);
		GuestData guestData1=new GuestData(3,"juan","cruz",LocalDateTime.of(1993,12,12,10,30),Nationality.ARGENTIN,"2345778",13);
		guestData.setId(2);
		GuestData guestData2=new GuestData(4,"harry","cruz",LocalDateTime.of(2019,12,12,10,30),Nationality.ARGENTIN,"2345779",12);
		guestData.setId(3);
		
		List<GuestData>listGuestData=new ArrayList<>();
		
		listGuestData.add(guestData);
		listGuestData.add(guestData1);
		listGuestData.add(guestData2);
		
		Mockito.doReturn(listGuestData.get(0)).when(guestDataDAO).searchByIdGuest(1);
		Mockito.doReturn(listGuestData.get(1)).when(guestDataDAO).searchByIdGuest(2);
		Mockito.doReturn(listGuestData.get(2)).when(guestDataDAO).searchByIdGuest(3);
		
		GuestData loadedGuest = bookingService.loadGuestById(1);
		GuestData loadedGuest1 = bookingService.loadGuestById(2);
		GuestData loadedGuest2 = bookingService.loadGuestById(3);
		
		assertEquals(guestData, loadedGuest);
		assertEquals(guestData1, loadedGuest1);
		assertEquals(guestData2, loadedGuest2);
	}

	@Test
	public void testModifyGuest() {
	Mockito.doReturn(1).when(guestDataDAO).modify(any(GuestData.class));
	GuestData guestData=new GuestData("andrea","salinas",LocalDateTime.of(1990,12,12,10,30),Nationality.ARGENTIN,"2345777",1);

	int modifyGuest= bookingService.modifyGuest(guestData);

	verify(guestDataDAO).modify(any(GuestData.class));

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
			GuestData guestData=new GuestData(null,"salinas",LocalDateTime.of(1990,12,12,10,30),Nationality.ARGENTIN,"2345777",1);
			bookingService.saveGuest(guestData);
		}catch(RuntimeException e) {
			Assert.assertNotNull(e);
			String error="Name was null";
			Assert.assertEquals(error,e.getMessage());
		}
	}
	
	@Test
	public void testNullLastNameGuest() {	
		try {
			GuestData guestData=new GuestData ("andrea",null,LocalDateTime.of(1990,12,12,10,30),Nationality.ARGENTIN,"2345777",1);
			bookingService.saveGuest(guestData);
		}catch(RuntimeException e) {
			Assert.assertNotNull(e);
			String error="Lastname was null";
			Assert.assertEquals(error,e.getMessage());
		}
	}
	
	@Test
	public void testBirthdateIncorrect() {	
		try {
			GuestData guestData=new GuestData ("andrea","salinas",LocalDateTime.of(2023,12,12,10,30),Nationality.ARGENTIN,"2345777",1);
			bookingService.saveGuest(guestData);
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

