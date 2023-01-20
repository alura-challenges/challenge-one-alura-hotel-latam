package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Reserva {

	private Integer id;
	private Date fechaEntrada;
	private Date fechaSalida;
	private Double valor;
	private String formaPago;
	
	private List<Huesped> huespedes = new ArrayList<>();
	
	private static Double priceDay = 16.0;
	
	public Reserva(Date fechaEntrada, Date fechaSalida, String formaPago) {
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.formaPago = formaPago;
		this.valor = calculoValor(fechaSalida, fechaEntrada);

	}

	public Reserva(int id, java.sql.Date fechaEntrada, java.sql.Date fechaSalida, Double valor, String formaPago) {
		this.id = id;
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.valor = valor;
		this.formaPago = formaPago;
	}

	public Date getFechaEntrada() {
		return fechaEntrada;
	}

	public Date getFechaSalida() {
		return fechaSalida;
	}

	public Double getValor() {
		return valor;
	}
	
	public Integer getId() {
		return id;
	}
	
	public String getFormaPago() {
		return formaPago;
	}
	
	public List<Huesped> getHuespedes() {
		return huespedes;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public static Double calculoValor(Date fechaSalida, Date fechaEntrada) {
		Integer days = Integer.valueOf((int) ((fechaSalida.getTime() - fechaEntrada.getTime()) / (24 * 60 * 60 * 1000)));
		return (double) priceDay * days;
	}
	
	public void agregar(Huesped huesped) {
		this.huespedes.add(huesped);
	}
	
	@Override
	public String toString() {
		return "[id=" + id + ", fechaEntrada=" + fechaEntrada + ", fechaSalida=" + fechaSalida + ", valor="
				+ valor + ", formaPago=" + formaPago + "]";
	}

}
