package modelo;
import java.sql.Date;

public class Reservas {
	private Integer id;
	private Date fechaEntrada;
	private Date fechaSalida;
	private String	valor;
	private String formaPago;
	
	
	public Reservas(Date fechaEntrada, Date fechaSalida, String valor, String formaPago) {
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.valor = valor;
		this.formaPago = formaPago;
	}
	
	

	public Reservas(Integer id, Date fechaEntrada, Date fechaSalida, String valor, String formaPago) {
		this.id = id;
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.valor = valor;
		this.formaPago = formaPago;
	}



	public Integer getId() {
		return id;
	}

	public Date getFechaEntrada() {
		return fechaEntrada;
	}

	public Date getFechaSalida() {
		return fechaSalida;
	}

	public String getValor() {
		return valor;
	}

	public String getFormaPago() {
		return formaPago;
	}

	public void setId(int id) {
		this.id= id;
		
	}

	@Override
	public String toString() {
		return "id = " + id ;
	}
	
	
}
