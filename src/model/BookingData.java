package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class BookingData {
	
	private Integer id; 
	private LocalDateTime entryDate;
	private LocalDateTime departureDate;
	private BigDecimal price;
	private PaymentMethod paymentMethod;
	
	public BookingData(LocalDateTime entryDate, LocalDateTime departureDate, PaymentMethod paymentMethod) {
		this.entryDate=entryDate;
		this.departureDate=departureDate;
		this.paymentMethod=paymentMethod;
	}
	
	public BookingData(LocalDateTime entryDate, LocalDateTime departureDate, PaymentMethod paymentMethod,
			BigDecimal bookingPrice) {
		this.entryDate=entryDate;
		this.departureDate=departureDate;
		this.paymentMethod=paymentMethod;
		this.price=bookingPrice;
	}

	public BookingData(Integer id, LocalDateTime entryDate, LocalDateTime departureDate, BigDecimal price,
			PaymentMethod paymentMethod) {
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
	
	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}
	
	public void setMethodPayment(PaymentMethod paymentMethod) {
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
		return "BookingData"+"Id="+this.id+"entrydate="+this.entryDate +"departuredate="+this.departureDate+"price="+this.price+"paymentMethod="+this.paymentMethod;
	}
}
