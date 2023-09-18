package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import database.dto.GuestDataDTO;
import database.dto.NationalityDTO;

public class GuestDataDAO extends MainDAO {
	
	private static final String SAVE_IN_GUEST="INSERT INTO guest(name, lastname, birthdate, nationality, telephone, id_booking)"+
			"VALUES(?,?,?,?,?,?)";
	private static final String SELECT_GUEST_TABLE = "SELECT id, name, lastname, birthdate, nationality, telephone, id_booking FROM guest";
	
	private static final String SELECT_GUEST_TABLE_BY_ID="SELECT id, name, lastname, birthdate, nationality, telephone, id_booking FROM guest WHERE id_booking=?";
	
	private static final String DELETE_GUEST_BY_ID="DELETE FROM guest WHERE id=?";
	
	private static final String MODIFY_GUEST="UPDATE guest SET name=?, lastname=?, birthdate=?, nationality=?, telephone=? WHERE id=?";
	
	public GuestDataDTO save(GuestDataDTO guestData) {
		Connection con= super.getConnection();	
		
		try {
			final PreparedStatement statement = con.prepareStatement(SAVE_IN_GUEST, Statement.RETURN_GENERATED_KEYS);
			try (statement){
				Integer id=saveRecordGuest(guestData, statement);
				guestData.setId(id);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return guestData;	
	}
	
	Integer saveRecordGuest(GuestDataDTO guestData, PreparedStatement statement) throws SQLException {
		String name = guestData.getName();
		String lastName = guestData.getLastName();
		
		LocalDateTime birthDate=guestData.getBirthDate();
		Timestamp brithDateTimestamp=null;
		if(birthDate!=null) {
			brithDateTimestamp=Timestamp.valueOf(birthDate);
		}
		
		NationalityDTO nationalityMethodUser=guestData.getNationality();
		String nationality=null;
		if(nationalityMethodUser!=null) {
			nationality=nationalityMethodUser.getName();
		}
		
		String phoneNumber=guestData.getPhoneNumber();
		Integer idBooking=guestData.getIdBooking();
		
		statement.setString(1,name);
		statement.setString(2, lastName);
		statement.setTimestamp(3, brithDateTimestamp);
		statement.setString(4,nationality);
		statement.setString(5,phoneNumber);
		statement.setInt(6, idBooking);
		
		statement.execute();
		
		final ResultSet resultSet=statement.getGeneratedKeys();
		try(resultSet){
			if(resultSet.next()) {
				return resultSet.getInt(1);
			}
		}
		return null;
	}
	
	public List<GuestDataDTO> searchGuestList(){
		List<GuestDataDTO> guestdataList=new ArrayList<>();
		Connection con= super.getConnection();

		try {
			final PreparedStatement statement=con.prepareStatement(SELECT_GUEST_TABLE);
			
			try(statement){
				statement.execute();
				final ResultSet resultSet=statement.getResultSet();
				guestdataList = parseGuestAttributes(resultSet);
			}	
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
		return guestdataList;
	}
	
	public GuestDataDTO searchByIdGuest(int idSearch) {
		Connection con= super.getConnection();
		GuestDataDTO guestdata=null;
		
		try {
			final PreparedStatement statement=con.prepareStatement(SELECT_GUEST_TABLE_BY_ID);
			
			try(statement){
				statement.setInt(1, idSearch);
				statement.execute();
				final ResultSet resultSet=statement.getResultSet();
				List<GuestDataDTO> guestdataList = parseGuestAttributes(resultSet);
				if (!guestdataList.isEmpty()) {
					guestdata = guestdataList.get(0);
				}
			}	
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
		return guestdata;
	}
	
	public List<GuestDataDTO> parseGuestAttributes(ResultSet resultSet) {					
		List<GuestDataDTO> guestdataList=new ArrayList<>();
		
		try(resultSet){
			while(resultSet.next()) {
				int id = resultSet.getInt("id");
				String name=resultSet.getString("name");
				String lastname=resultSet.getString("lastname");	

				Timestamp birthDateTimestamp=resultSet.getTimestamp("birthdate");
				LocalDateTime birthdate=null;
				if(birthDateTimestamp!=null) {
					birthdate=birthDateTimestamp.toLocalDateTime();
				}
			
				String nationalityMethod=resultSet.getString("nationality");
				NationalityDTO nationality=null;	
				if(nationalityMethod!=null) {
					nationality=NationalityDTO.valueOf(nationalityMethod);
				}
					
				String telephone=resultSet.getString("telephone");			
				int idBooking = resultSet.getInt("id_booking");
				
				GuestDataDTO guestRow=new GuestDataDTO(
						id,
						name,
						lastname,
						birthdate,
						nationality,
						telephone,
						idBooking);
				guestdataList.add(guestRow);
			}
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
		return guestdataList;	
	}
	
	public int delete(int id) {
		Connection con= super.getConnection();
		try {
			final PreparedStatement statement=con.prepareStatement(DELETE_GUEST_BY_ID);
			
			try(statement){
				statement.setInt(1, id);
				return statement.executeUpdate();
			}
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public int modify(GuestDataDTO guestDataDTO) {
		Connection con= super.getConnection();
		try {
			final PreparedStatement statement=con.prepareStatement(MODIFY_GUEST);
			
			try(statement){
				
				String name=guestDataDTO.getName();
				String lastname=guestDataDTO.getLastName();
				Timestamp birthdateTimestamp=Timestamp.valueOf(guestDataDTO.getBirthDate());
				String nationality=guestDataDTO.getNationality().getName();
				String telephone=guestDataDTO.getPhoneNumber();
				int id=guestDataDTO.getId();
				
				statement.setString(1, name);
				statement.setString(2, lastname);
				statement.setTimestamp(3, birthdateTimestamp);
				statement.setString(4, nationality);
				statement.setString(5, telephone);
				statement.setInt(6, id);
				statement.execute();
				
				int updateCount=statement.getUpdateCount();
				
				return updateCount;	
			}
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
