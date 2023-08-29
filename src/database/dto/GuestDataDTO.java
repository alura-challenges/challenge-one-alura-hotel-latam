package database.dto;

import java.time.LocalDateTime;

public class GuestDataDTO {
	
	private String name;
	private String lastName;
	private LocalDateTime birthDate;
	private NationalityDTO nationality;
	private String phoneNumber;
	private Integer idBooking;
		
	public GuestDataDTO(String name, String lastName, LocalDateTime birthDate, NationalityDTO nationality,
			String phoneNumber, Integer idBooking) {
		this.name=name;
		this.lastName=lastName;
		this.birthDate=birthDate;
		this.nationality=nationality;
		this.phoneNumber=phoneNumber;
		this.idBooking=idBooking;
	}

	public GuestDataDTO(int id, String name, String lastName, LocalDateTime birthDate, NationalityDTO nationality,
			String phoneNumber, Integer idBooking) {
		this.name=name;
		this.lastName=lastName;
		this.birthDate=birthDate;
		this.nationality=nationality;
		this.phoneNumber=phoneNumber;
		this.idBooking=idBooking;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public LocalDateTime getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDateTime birthDate) {
		this.birthDate = birthDate;
	}

	public NationalityDTO getNationality() {
		return nationality;
	}

	public void setNationality(NationalityDTO nationality) {
		this.nationality = nationality;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Integer getIdBooking() {
		return idBooking;
	}

	public void setIdBooking(Integer idBooking) {
		this.idBooking=idBooking;
	}
}
