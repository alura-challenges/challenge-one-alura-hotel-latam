package jdbc.modelo;

import java.sql.Date;

public class Huespedes {
	private String Nombre;
	private String Apellido;
	private String Nacionalidad;
	private Date FechaNacimiento;
	private String Telefono;
	private Integer IdReserva;
	
	public Huespedes(String nombre, String apellido, String nacionalidad, Date fechaNacimiento, String telefono,
			Integer idReserva) {
		super();
		Nombre = nombre;
		Apellido = apellido;
		Nacionalidad = nacionalidad;
		FechaNacimiento = fechaNacimiento;
		Telefono = telefono;
		IdReserva = idReserva;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public String getApellido() {
		return Apellido;
	}

	public void setApellido(String apellido) {
		Apellido = apellido;
	}

	public String getNacionalidad() {
		return Nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		Nacionalidad = nacionalidad;
	}

	public Date getFechaNacimiento() {
		return FechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		FechaNacimiento = fechaNacimiento;
	}

	public String getTelefono() {
		return Telefono;
	}

	public void setTelefono(String telefono) {
		Telefono = telefono;
	}

	public Integer getIdReserva() {
		return IdReserva;
	}

	public void setIdReserva(Integer idReserva) {
		IdReserva = idReserva;
	}
	
}
