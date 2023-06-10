package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
public class Reserva {
	private Integer id;
	private Date fechaEntrada;
	private Date fechaSalida;
	private String formaPago;
	private Double valor;
	
	public Reserva() {
	}

	public Reserva(Integer id, Date fechaEntrada, Date fechaSalida, String formaPago, Double valor) {
		this.id = id;
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.formaPago = formaPago;
		this.valor = valor;
	}
	public Reserva(Date fechaEntrada, Date fechaSalida, String formaPago, Double valor) {
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.formaPago = formaPago;
		this.valor = valor;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getFechaEntrada() {
		return fechaEntrada;
	}

	public void setFechaEntrada(Date fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	public Date getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public String getFormaPago() {
		return formaPago;
	}

	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "Reserva [id=" + id + ", fechaEntrada=" + fechaEntrada + ", fechaSalida=" + fechaSalida + ", formaPago="
				+ formaPago + ", valor=" + valor + "]";
	}
	public void formatoFechas() {
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
		// Convertir las fechas a cadenas de texto en el formato especificado
		String fechaEntradaSQL = formatoFecha.format(this.fechaEntrada);
		String fechaSalidaSQL = formatoFecha.format(this.fechaSalida);
		
        try {
        	this.fechaEntrada = formatoFecha.parse(fechaEntradaSQL);
			this.fechaSalida = formatoFecha.parse(fechaSalidaSQL);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
		
}
