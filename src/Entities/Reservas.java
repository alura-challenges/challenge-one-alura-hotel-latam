package Entities;

import java.time.LocalDate;

public class Reservas {
	private int id;
	private LocalDate fechaEntrada;
	private LocalDate fechaSalida;
	private Double valor;
	private String formaDePago;
	
	
	public Reservas() {
	}

	public Reservas(LocalDate fechaEntrada, LocalDate fechaSalida, Double valor, String formaDePago) {
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.valor = valor;
		this.formaDePago = formaDePago;
	}
	
	
	public Reservas(int id, LocalDate fechaEntrada, LocalDate fechaSalida, Double valor, String formaDePago) {
		this.id = id;
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.valor = valor;
		this.formaDePago = formaDePago;
	}
	

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public LocalDate getFechaEntrada() {
		return fechaEntrada;
	}


	public void setFechaEntrada(LocalDate fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}


	public LocalDate getFechaSalida() {
		return fechaSalida;
	}


	public void setFechaSalida(LocalDate fechaSalida) {
		this.fechaSalida = fechaSalida;
	}


	public Double getValor() {
		return valor;
	}


	public void setValor(Double valor) {
		this.valor = valor;
	}


	public String getFormaDePago() {
		return formaDePago;
	}


	public void setFormaDePago(String formaDePago) {
		this.formaDePago = formaDePago;
	}

	@Override
	public String toString() {
		return "Reservas [ ID: " + id + ", FECHA ENTRADA: " + fechaEntrada + ", FECHA SALIDA: " + fechaSalida + ", VALOR: "
				+ valor + ", FORMA DE PAGO: " + formaDePago + " ]";
	}
	
	
	

}
