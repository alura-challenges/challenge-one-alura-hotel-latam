package models;

import java.util.Date;

public class Huesped {

	private Integer id;
	private String nombre;
	private String apellido;
	private Date fechaNacimiento; 
	private String nacionalidad;
	private String telefono;
	private Integer idReserva;
	
	public Integer getId() {
		return id;
	}	
	
	public String getNombre() {
		return nombre;
	}


	public String getApellido() {
		return apellido;
	}


	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}


	public String getNacionalidad() {
		return nacionalidad;
	}


	public String getTelefono() {
		return telefono;
	}


	public Integer getIdReserva() {
		return idReserva;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public Huesped(Reserva reserva, String nombre, String apellido, Date fechaNacimiento, String nacionalidad,
			String telefono) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
		this.nacionalidad = nacionalidad;
		this.telefono = telefono;
		this.idReserva = reserva.getId();
	}


	public Huesped(Integer id, String nombre, String apellido, Date fechaNacimiento, String nacionalidad,
			String telefono, Integer idReserva) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
		this.nacionalidad = nacionalidad;
		this.telefono = telefono;
		this.idReserva = idReserva;
	}

	@Override
	public String toString() {
		return "[id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", fechaNacimiento="
				+ fechaNacimiento + ", nacionalidad=" + nacionalidad + ", telefono=" + telefono + ", idReserva="
				+ idReserva + "]";
	}

}
