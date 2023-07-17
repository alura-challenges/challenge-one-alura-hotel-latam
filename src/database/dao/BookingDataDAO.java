package database.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import database.dto.BookingDataDTO;

public class BookingDataDAO extends MainDAO {
	
	private Connection con;
	private static final String SAVE_IN_BOOKING="INSERT INTO booking(entry_date,departure_date,method_payment,price)"+
			"VALUES(?,?,?,?)";
	
	public BookingDataDAO() {
	}
	
	public BookingDataDAO(Connection con) {
		this.con = con;
	}
	
	public BookingDataDTO save(BookingDataDTO bookingData) {
		con= super.getConnection();
		
		try {
			final PreparedStatement statement = con.prepareStatement(SAVE_IN_BOOKING, Statement.RETURN_GENERATED_KEYS);
			Integer id = runRecord(bookingData, statement);
			bookingData.setId(id);
			System.out.println(String.format(
					"Fue insertado el producto de ID %s",bookingData));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return bookingData;	
	}

	public Integer runRecord(BookingDataDTO bookingData, PreparedStatement statement) throws SQLException {
		LocalDateTime entryDatebyUser=bookingData.getEntryDate();
		Timestamp entryTimestampUser=Timestamp.valueOf(entryDatebyUser);
		
		LocalDateTime departureDatebyUser=bookingData.getDepartureDate();
		Timestamp departureTimestampUser=Timestamp.valueOf(departureDatebyUser);
		
		String methodPaymentUser = bookingData.getMethodPayment().getName();
		BigDecimal priceByBookingService=bookingData.getPrice();
		
		statement.setTimestamp(1,entryTimestampUser);
		statement.setTimestamp(2, departureTimestampUser);
		statement.setString(3, methodPaymentUser);
		statement.setBigDecimal(4, priceByBookingService);
		
		statement.execute();
		 
		final ResultSet resultSet=statement.getGeneratedKeys();
		try(resultSet){
			while(resultSet.next()) {
				return resultSet.getInt(1);
			}
		}
		return null;
	}
}
