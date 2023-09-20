package model;

import java.time.LocalDateTime;

public class GuestData {
	
	private Integer id;
	private String name;
	private String lastName;
	private LocalDateTime birthDate;
	private Nationality nationality;
	private String phoneNumber;
	private Integer idBooking;
		
	public GuestData(String name, String lastName, LocalDateTime birthDate, Nationality nationality,
			String phoneNumber, Integer idBooking) {
		this.name=name;
		this.lastName=lastName;
		this.birthDate=birthDate;
		this.nationality=nationality;
		this.phoneNumber=phoneNumber;
		this.idBooking=idBooking;
	}

	public GuestData(Integer id, String name, String lastName, LocalDateTime birthDate, Nationality nationality,
			String phoneNumber, Integer idBooking) {
		this.id=id;
		this.name=name;
		this.lastName=lastName;
		this.birthDate=birthDate;
		this.nationality=nationality;
		this.phoneNumber=phoneNumber;
		this.idBooking=idBooking;
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
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

	public Nationality getNationality() {
		return nationality;
	}

	public void setNationality(Nationality nationality) {
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
