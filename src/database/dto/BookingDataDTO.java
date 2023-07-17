package database.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class BookingDataDTO {
	
	private int id; 
	private LocalDateTime entryDate;
	private LocalDateTime departureDate;
	private BigDecimal price;
	private PaymentMethodDTO methodPayment;
	
	public BookingDataDTO(LocalDateTime entryDate, LocalDateTime departureDate, PaymentMethodDTO methodPayment) {
		this.entryDate=entryDate;
		this.departureDate=departureDate;
		this.methodPayment=methodPayment;
	}
	
	public BookingDataDTO(LocalDateTime entryDate, LocalDateTime departureDate, PaymentMethodDTO methodPayment,
			BigDecimal bookingPrice) {
		this.entryDate=entryDate;
		this.departureDate=departureDate;
		this.methodPayment=methodPayment;
		this.price=bookingPrice;
	}
	
	public LocalDateTime getEntryDate() {
		return entryDate;
	}
	
	public void setEntryDate(LocalDateTime entryDate) {
		this.entryDate = entryDate;
	}
	
	public LocalDateTime getDepartureDate() {
		return departureDate;
	}
	
	public void setDepartureDate(LocalDateTime departureDate) {
		this.departureDate = departureDate;
	}
	
	public PaymentMethodDTO getMethodPayment() {
		return methodPayment;
	}
	
	public void setMethodPayment(PaymentMethodDTO methodPayment) {
		this.methodPayment = methodPayment;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public BigDecimal getPrice() {
		return price;
	}
	
	@Override
	public String toString() {
		String formattedDateTime=String.format("%tY-%tm-%td %tH:%tM:%tS",entryDate,entryDate,entryDate,entryDate,entryDate,entryDate);
		return String.format("id: %s y fecha: ", this.id)+formattedDateTime;
	}
}
