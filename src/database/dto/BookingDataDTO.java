package database.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class BookingDataDTO {
	
	private Integer id; 
	private LocalDateTime entryDate;
	private LocalDateTime departureDate;
	private BigDecimal price;
	private PaymentMethodDTO paymentMethod;
	
	public BookingDataDTO(LocalDateTime entryDate, LocalDateTime departureDate, PaymentMethodDTO paymentMethod) {
		this.entryDate=entryDate;
		this.departureDate=departureDate;
		this.paymentMethod=paymentMethod;
	}
	
	public BookingDataDTO(LocalDateTime entryDate, LocalDateTime departureDate, PaymentMethodDTO paymentMethod,
			BigDecimal bookingPrice) {
		this.entryDate=entryDate;
		this.departureDate=departureDate;
		this.paymentMethod=paymentMethod;
		this.price=bookingPrice;
	}

	public BookingDataDTO(Integer id, LocalDateTime entryDate, LocalDateTime departureDate, BigDecimal price,
			PaymentMethodDTO paymentMethod) {
		this.id=id;
		this.entryDate=entryDate;
		this.departureDate=departureDate;
		this.paymentMethod=paymentMethod;
		this.price=price;

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
	
	public PaymentMethodDTO getPaymentMethod() {
		return paymentMethod;
	}
	
	public void setMethodPayment(PaymentMethodDTO paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public BigDecimal getPrice() {
		return price;
	}
	
	/*@Override
	public String toString() {
		String formattedDateTime=entryDate.toString();
		return String.format("id: %s and date: %s", this.id+formattedDateTime);
	}*/
	
	@Override
	public String toString() {
		return "BookingDataDTO"+"Id="+this.id+"entrydate="+this.entryDate +"departuredate="+this.departureDate+"price="+this.price+"paymentMethod="+this.paymentMethod;
	}
}
