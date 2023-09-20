package database.dao;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import model.BookingData;
import model.GuestData;
import model.Nationality;
import model.PaymentMethod;

@RunWith(JUnit4.class)
public class GuestDataDAOTest {
	private GuestDataDAO guestDataDAO = new GuestDataDAO();
	private BookingDataDAO bookingDataDAO = new BookingDataDAO();

	@Test
	public void testGuestCrudTest() throws SQLException {
		
		LocalDateTime entryDate = LocalDateTime.now();
		LocalDateTime departureDate = LocalDateTime.of(2023,12,12,10,30);
		BigDecimal price = new BigDecimal("6000");
		PaymentMethod paymentMethod = PaymentMethod.CASH;
		
		BookingData bookingData=new BookingData(entryDate,departureDate,paymentMethod,price);
		BookingData bookingSaved = bookingDataDAO.save(bookingData);

		GuestData guest=new GuestData("Juliana","Salinas",LocalDateTime.of(1990,12,12,10,30),Nationality.ARGENTIN,"1234567",bookingSaved.getId());	
		
		GuestData guestSaved = guestDataDAO.save(guest);
		Assert.assertNotNull(guestSaved);
		Assert.assertNotNull(guestSaved.getId());
		assertEqualsGuestData(guest,guestSaved);
	
		GuestData guestSearched = guestDataDAO.searchByIdGuest(guestSaved.getIdBooking());
		Assert.assertNotNull(guestSearched);
		assertEqualsGuestData(guestSaved,guestSearched);
		
		GuestData guestToModify=new GuestData(guestSearched.getId(),"Juan","Cruz",LocalDateTime.of(1993,12,12,10,30),Nationality.ARGENTIN,"1234568",guestSaved.getIdBooking());
		
		int guestModified = guestDataDAO.modify(guestToModify);
		Assert.assertNotNull(guestSearched);
		Assert.assertTrue(1 == guestModified);
		
		GuestData guestSearchedModified = guestDataDAO.searchByIdGuest(guestToModify.getIdBooking());
		Assert.assertNotNull(guestSearchedModified);
		assertEqualsGuestData(guestToModify,guestSearchedModified);
		
		int deletedRecords = guestDataDAO.delete(guestToModify.getId());
		Assert.assertTrue(1 == deletedRecords);
		
		GuestData guestDeleted = guestDataDAO.searchByIdGuest(guestToModify.getId());
		Assert.assertNull(guestDeleted);
		
		bookingDataDAO.delete(bookingSaved.getId());	

	}

	@Test
	public void testSearchGuestList() {
		LocalDateTime entryDate=LocalDateTime.now().withNano(0);
		BookingData booking=new BookingData(entryDate,LocalDateTime.of(2023,12,12,11,30),PaymentMethod.CASH,new BigDecimal("6000.0"));
		BookingData booking2=new BookingData(entryDate,LocalDateTime.of(2023,10,12,8,30),PaymentMethod.CASH,new BigDecimal("7000.0"));
		BookingData booking3=new BookingData(entryDate,LocalDateTime.of(2023,8,12,07,30),PaymentMethod.CASH,new BigDecimal("8000.0"));
		
		BookingData bookingSaved = bookingDataDAO.save(booking);
		BookingData bookingSaved2 = bookingDataDAO.save(booking2);
		BookingData bookingSaved3 = bookingDataDAO.save(booking3);
		
		GuestData guest=new GuestData("Juliana","Salinas",LocalDateTime.of(1990,12,12,10,30),Nationality.ARGENTIN,"1234567",bookingSaved.getId());	
		GuestData guest2=new GuestData("Pablo","Salinas",LocalDateTime.of(1993,12,12,10,30),Nationality.ARGENTIN,"1234569",bookingSaved2.getId());	
		GuestData guest3=new GuestData("Sebastian","Cruz",LocalDateTime.of(1993,10,13,10,30),Nationality.ARGENTIN,"1234568",bookingSaved3.getId());	
	
		GuestData guestSaved = guestDataDAO.save(guest);
		GuestData guestSaved2 = guestDataDAO.save(guest2);
		GuestData guestSaved3 = guestDataDAO.save(guest3);
		
		List<GuestData> searchGuestList = guestDataDAO.searchGuestList();
		assertEqualsGuestData(guestSaved,searchGuestList.get(0));
		assertEqualsGuestData(guestSaved2,searchGuestList.get(1));
		assertEqualsGuestData(guestSaved3,searchGuestList.get(2));
		
		int deletedRecords = guestDataDAO.delete(guestSaved.getId());	
		int deletedRecords2 = guestDataDAO.delete(guestSaved2.getId());
		int deletedRecords3 = guestDataDAO.delete(guestSaved3.getId());
		Assert.assertTrue(1 == deletedRecords);
		Assert.assertTrue(1 == deletedRecords2);
		Assert.assertTrue(1 == deletedRecords3);
		
		bookingDataDAO.delete(bookingSaved.getId());
		bookingDataDAO.delete(bookingSaved2.getId());
		bookingDataDAO.delete(bookingSaved3.getId());
	}	
	
	@Test
	public void testGetGuestAttributes() {
		BookingData bookingData=new BookingData(LocalDateTime.now(),LocalDateTime.of(2023,12,12,10,30),PaymentMethod.CASH,new BigDecimal("6000"));
		BookingData bookingSaved = bookingDataDAO.save(bookingData);
		
		try {
			GuestData guest=new GuestData(null,null,LocalDateTime.of(1990,12,12,10,30),Nationality.ARGENTIN,"1234567",bookingSaved.getId());
			
			guestDataDAO.save(guest);
			Assert.fail("This test should have failed");
		}catch(RuntimeException e) {
			Assert.assertNotNull(e);
			String error="java.sql.SQLIntegrityConstraintViolationException: Column 'name' cannot be null";
			Assert.assertEquals(error,e.getMessage());
		}
		bookingDataDAO.delete(bookingSaved.getId());
	}
	
	@Test
	public void testSaveBirthdateNull() {
		BookingData bookingData=new BookingData(LocalDateTime.now(),LocalDateTime.of(2023,12,12,10,30),PaymentMethod.CASH,new BigDecimal("6000"));
		BookingData bookingSaved = bookingDataDAO.save(bookingData);
		
		GuestData guest=new GuestData("Juliana","Salinas",null,Nationality.ARGENTIN,"1234567",bookingSaved.getId());
		guestDataDAO.save(guest);
		
		GuestData guestSearched = guestDataDAO.searchByIdGuest(guest.getIdBooking());
		Assert.assertNotNull(guestSearched);
		assertEqualsGuestData(guest,guestSearched);
		
		Assert.assertEquals(1,guestDataDAO.delete(guest.getId()));
		Assert.assertEquals(1,bookingDataDAO.delete(bookingSaved.getId()));
	}
	
	@Test
	public void testSaveNationalityNull() {
		BookingData bookingData=new BookingData(LocalDateTime.now(),LocalDateTime.of(2023,12,12,10,30),PaymentMethod.CASH,new BigDecimal("6000"));
		BookingData bookingSaved = bookingDataDAO.save(bookingData);
		
		GuestData guest=new GuestData("Juliana","Salinas",LocalDateTime.of(1990,12,12,10,30),null,"1234567",bookingSaved.getId());
		guestDataDAO.save(guest);
		
		GuestData guestSearched = guestDataDAO.searchByIdGuest(guest.getIdBooking());
		Assert.assertNotNull(guestSearched);
		assertEqualsGuestData(guest,guestSearched);
		
		Assert.assertEquals(1,guestDataDAO.delete(guest.getId()));
		Assert.assertEquals(1,bookingDataDAO.delete(bookingSaved.getId()));
	}
	
	private void assertEqualsGuestData(GuestData guestToBeCompared, GuestData guestWhoComparesTo) {
		Assert.assertEquals(guestToBeCompared.getId(), guestWhoComparesTo.getId());
		Assert.assertEquals(guestToBeCompared.getName(), guestWhoComparesTo.getName());
		Assert.assertEquals(guestToBeCompared.getLastName(), guestWhoComparesTo.getLastName());
		Assert.assertEquals(guestToBeCompared.getBirthDate(), guestWhoComparesTo.getBirthDate());
		Assert.assertEquals(guestToBeCompared.getNationality(), guestWhoComparesTo.getNationality());
		Assert.assertEquals(guestToBeCompared.getPhoneNumber(), guestWhoComparesTo.getPhoneNumber());
		Assert.assertEquals(guestToBeCompared.getIdBooking(), guestWhoComparesTo.getIdBooking());
	}
}
