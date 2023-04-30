package Entities;

import java.sql.Date;

public class Huesped {
	private int id;
	private String nombre;
	private String apellido;
	private Date fechaNacimiento;
	private String nacionalidad;
	private int telefono;
	private int numeroReservas;
	
	
	public Huesped() {
	}
	
	
	public Huesped(int id, String nombre, String apellido, Date fechaNacimiento,  String nacionalidad, int telefono, int numeroReservas) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
		this.nacionalidad = nacionalidad;
		this.telefono = telefono;
		this.numeroReservas = numeroReservas;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getNacionalidad() {
		return nacionalidad;
	}
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	public int getTelefono() {
		return telefono;
	}
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	public int getNumeroReservas() {
		return numeroReservas;
	}
	public void setNumeroReservas(int numeroReservas) {
		this.numeroReservas = numeroReservas;
	}


	@Override
	public String toString() {
		return "ID: " + id + ", NOMBRE: " + nombre + ", APELLIDO: " + apellido + ", FECHA-NAC: "
				+ fechaNacimiento + ", NACIONALIDAD: " + nacionalidad + ", TELEFONO: " + telefono + ", N° RESERVA: "
				+ numeroReservas + "]";
	}
	
	
}
