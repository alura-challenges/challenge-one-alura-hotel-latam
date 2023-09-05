package modelo;

import java.sql.Date;

public class Reservas {

	private Integer id;
	private Date fecha_entrada;
	private Date fecha_salida;
	private String valor;
	private String forma_de_pago;
	
	public  Reservas(Date fecha_entrada,Date fecha_salida,String valor,String forma_de_pago){
		this.fecha_entrada = fecha_entrada;
		this.fecha_salida = fecha_salida;
		this.valor = valor;
		this.forma_de_pago = forma_de_pago;
	}
	
	

	public Reservas(Integer id, Date fecha_entrada, Date fecha_salida, String valor, String forma_de_pago) {
		this.id = id;
		this.fecha_entrada = fecha_entrada;
		this.fecha_salida = fecha_salida;
		this.valor = valor;
		this.forma_de_pago = forma_de_pago;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getFecha_entrada() {
		return fecha_entrada;
	}

	public void setFecha_entrada(Date fecha_entrada) {
		this.fecha_entrada = fecha_entrada;
	}

	public Date getFecha_salida() {
		return fecha_salida;
	}

	public void setFecha_salida(Date fecha_salida) {
		this.fecha_salida = fecha_salida;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getForma_de_pago() {
		return forma_de_pago;
	}

	public void setForma_de_pago(String forma_de_pago) {
		this.forma_de_pago = forma_de_pago;
	}
	
	
}
