package Entities;

import java.time.LocalDate;

public class Reservas {
	private int id;
	private LocalDate fechaEntrada;
	private LocalDate fechaSalida;
	private double valor;
	private String formaDePago;
	
	
	public Reservas() {
	}

	public Reservas(int id, LocalDate fechaEntrada, LocalDate fechaSalida, double valor, String formaDePago) {
		super();
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


	public double getValor() {
		return valor;
	}


	public void setValor(double valor) {
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
