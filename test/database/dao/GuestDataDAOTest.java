package database.dao;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import database.dto.BookingDataDTO;
import database.dto.GuestDataDTO;
import database.dto.NationalityDTO;
import database.dto.PaymentMethodDTO;

@RunWith(JUnit4.class)
public class GuestDataDAOTest {
	private GuestDataDAO guestDataDAO = new GuestDataDAO();
	private BookingDataDAO bookingDataDAO = new BookingDataDAO();

	@Test
	public void testGuestCrudTest() throws SQLException {
		
		LocalDateTime entryDate = LocalDateTime.now();
		LocalDateTime departureDate = LocalDateTime.of(2023,12,12,10,30);
		BigDecimal price = new BigDecimal("6000");
		PaymentMethodDTO paymentMethod = PaymentMethodDTO.CASH;
		
		BookingDataDTO bookingDataDTO=new BookingDataDTO(entryDate,departureDate,paymentMethod,price);
		BookingDataDTO bookingSaved = bookingDataDAO.save(bookingDataDTO);

		GuestDataDTO guest=new GuestDataDTO("Juliana","Salinas",LocalDateTime.of(1990,12,12,10,30),NationalityDTO.ARGENTIN,"1234567",bookingSaved.getId());	
		
		GuestDataDTO guestSaved = guestDataDAO.save(guest);
		Assert.assertNotNull(guestSaved);
		Assert.assertNotNull(guestSaved.getId());
		assertEqualsGuestDataDTO(guest,guestSaved);
	
		GuestDataDTO guestSearched = guestDataDAO.searchByIdGuest(guestSaved.getIdBooking());
		Assert.assertNotNull(guestSearched);
		assertEqualsGuestDataDTO(guestSaved,guestSearched);
		
		GuestDataDTO guestToModify=new GuestDataDTO(guestSearched.getId(),"Juan","Cruz",LocalDateTime.of(1993,12,12,10,30),NationalityDTO.ARGENTIN,"1234568",guestSaved.getIdBooking());
		
		int guestModified = guestDataDAO.modify(guestToModify);
		Assert.assertNotNull(guestSearched);
		Assert.assertTrue(1 == guestModified);
		
		GuestDataDTO guestSearchedModified = guestDataDAO.searchByIdGuest(guestToModify.getIdBooking());
		Assert.assertNotNull(guestSearchedModified);
		assertEqualsGuestDataDTO(guestToModify,guestSearchedModified);
		
		int deletedRecords = guestDataDAO.delete(guestToModify.getId());
		Assert.assertTrue(1 == deletedRecords);
		
		GuestDataDTO guestDeleted = guestDataDAO.searchByIdGuest(guestToModify.getId());
		Assert.assertNull(guestDeleted);
		
		bookingDataDAO.delete(bookingSaved.getId());	

	}

	@Test
	public void testSearchGuestList() {
		LocalDateTime entryDate=LocalDateTime.now().withNano(0);
		BookingDataDTO booking=new BookingDataDTO(entryDate,LocalDateTime.of(2023,12,12,11,30),PaymentMethodDTO.CASH,new BigDecimal("6000.0"));
		BookingDataDTO booking2=new BookingDataDTO(entryDate,LocalDateTime.of(2023,10,12,8,30),PaymentMethodDTO.CASH,new BigDecimal("7000.0"));
		BookingDataDTO booking3=new BookingDataDTO(entryDate,LocalDateTime.of(2023,8,12,07,30),PaymentMethodDTO.CASH,new BigDecimal("8000.0"));
		
		BookingDataDTO bookingSaved = bookingDataDAO.save(booking);
		BookingDataDTO bookingSaved2 = bookingDataDAO.save(booking2);
		BookingDataDTO bookingSaved3 = bookingDataDAO.save(booking3);
		
		GuestDataDTO guest=new GuestDataDTO("Juliana","Salinas",LocalDateTime.of(1990,12,12,10,30),NationalityDTO.ARGENTIN,"1234567",bookingSaved.getId());	
		GuestDataDTO guest2=new GuestDataDTO("Pablo","Salinas",LocalDateTime.of(1993,12,12,10,30),NationalityDTO.ARGENTIN,"1234569",bookingSaved2.getId());	
		GuestDataDTO guest3=new GuestDataDTO("Sebastian","Cruz",LocalDateTime.of(1993,10,13,10,30),NationalityDTO.ARGENTIN,"1234568",bookingSaved3.getId());	
	
		GuestDataDTO guestSaved = guestDataDAO.save(guest);
		GuestDataDTO guestSaved2 = guestDataDAO.save(guest2);
		GuestDataDTO guestSaved3 = guestDataDAO.save(guest3);
		
		List<GuestDataDTO> searchGuestList = guestDataDAO.searchGuestList();
		assertEqualsGuestDataDTO(guestSaved,searchGuestList.get(0));
		assertEqualsGuestDataDTO(guestSaved2,searchGuestList.get(1));
		assertEqualsGuestDataDTO(guestSaved3,searchGuestList.get(2));
		
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
		BookingDataDTO bookingDataDTO=new BookingDataDTO(LocalDateTime.now(),LocalDateTime.of(2023,12,12,10,30),PaymentMethodDTO.CASH,new BigDecimal("6000"));
		BookingDataDTO bookingSaved = bookingDataDAO.save(bookingDataDTO);
		
		try {
			GuestDataDTO guest=new GuestDataDTO(null,null,LocalDateTime.of(1990,12,12,10,30),NationalityDTO.ARGENTIN,"1234567",bookingSaved.getId());
			
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
		BookingDataDTO bookingDataDTO=new BookingDataDTO(LocalDateTime.now(),LocalDateTime.of(2023,12,12,10,30),PaymentMethodDTO.CASH,new BigDecimal("6000"));
		BookingDataDTO bookingSaved = bookingDataDAO.save(bookingDataDTO);
		
		GuestDataDTO guest=new GuestDataDTO("Juliana","Salinas",null,NationalityDTO.ARGENTIN,"1234567",bookingSaved.getId());
		guestDataDAO.save(guest);
		
		GuestDataDTO guestSearched = guestDataDAO.searchByIdGuest(guest.getIdBooking());
		Assert.assertNotNull(guestSearched);
		assertEqualsGuestDataDTO(guest,guestSearched);
		
		Assert.assertEquals(1,guestDataDAO.delete(guest.getId()));
		Assert.assertEquals(1,bookingDataDAO.delete(bookingSaved.getId()));
	}
	
	@Test
	public void testSaveNationalityNull() {
		BookingDataDTO bookingDataDTO=new BookingDataDTO(LocalDateTime.now(),LocalDateTime.of(2023,12,12,10,30),PaymentMethodDTO.CASH,new BigDecimal("6000"));
		BookingDataDTO bookingSaved = bookingDataDAO.save(bookingDataDTO);
		
		GuestDataDTO guest=new GuestDataDTO("Juliana","Salinas",LocalDateTime.of(1990,12,12,10,30),null,"1234567",bookingSaved.getId());
		guestDataDAO.save(guest);
		
		GuestDataDTO guestSearched = guestDataDAO.searchByIdGuest(guest.getIdBooking());
		Assert.assertNotNull(guestSearched);
		assertEqualsGuestDataDTO(guest,guestSearched);
		
		Assert.assertEquals(1,guestDataDAO.delete(guest.getId()));
		Assert.assertEquals(1,bookingDataDAO.delete(bookingSaved.getId()));
	}
	
	private void assertEqualsGuestDataDTO(GuestDataDTO guestToBeCompared, GuestDataDTO guestWhoComparesTo) {
		Assert.assertEquals(guestToBeCompared.getId(), guestWhoComparesTo.getId());
		Assert.assertEquals(guestToBeCompared.getName(), guestWhoComparesTo.getName());
		Assert.assertEquals(guestToBeCompared.getLastName(), guestWhoComparesTo.getLastName());
		Assert.assertEquals(guestToBeCompared.getBirthDate(), guestWhoComparesTo.getBirthDate());
		Assert.assertEquals(guestToBeCompared.getNationality(), guestWhoComparesTo.getNationality());
		Assert.assertEquals(guestToBeCompared.getPhoneNumber(), guestWhoComparesTo.getPhoneNumber());
		Assert.assertEquals(guestToBeCompared.getIdBooking(), guestWhoComparesTo.getIdBooking());
	}
}
