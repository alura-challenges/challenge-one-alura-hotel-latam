package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import database.dto.GuestDataDTO;

public class GuestDataDAO extends MainDAO {
	
	private static final String SAVE_IN_GUEST="INSERT INTO guest(name, lastname, birthdate, nationality, telephone, id_booking)"+
			"VALUES(?,?,?,?,?,?)";
	
	public GuestDataDTO save(GuestDataDTO guestData) {
		Connection con= super.getConnection();	
		
		try {
			final PreparedStatement statement = con.prepareStatement(SAVE_IN_GUEST, Statement.RETURN_GENERATED_KEYS);
			try (statement){
				saveRecordGuest(guestData, statement);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return guestData;	
	}
	private void saveRecordGuest(GuestDataDTO guestData, PreparedStatement statement) throws SQLException {
		String name = guestData.getName();
		String lastName = guestData.getLastName();
		LocalDateTime birthDate=guestData.getBirthDate();
		Timestamp brithDateTimestamp=Timestamp.valueOf(birthDate);
		String nationality = guestData.getNationality().getName();
		String phoneNumber=guestData.getPhoneNumber();
		Integer idBooking=guestData.getIdBooking();
		
		statement.setString(1,name);
		statement.setString(2, lastName);
		statement.setTimestamp(3, brithDateTimestamp);
		statement.setString(4,nationality);
		statement.setString(5,phoneNumber);
		statement.setInt(6, idBooking);
		
		statement.execute();
	}
}
