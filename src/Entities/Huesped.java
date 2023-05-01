package Entities;

import java.time.LocalDate;

public class Huesped {
	private Integer id;
	private String nombre;
	private String apellido;
	private LocalDate fechaNacimiento;
	private String nacionalidad;
	private Integer telefono;
	private Integer numeroReservas;
	
	
	public Huesped() {
	}
	
	
	
	public Huesped(String nombre, String apellido, LocalDate fechaNacimiento, String nacionalidad, Integer telefono, Integer numeroReserva) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
		this.nacionalidad = nacionalidad;
		this.telefono = telefono;
		this.numeroReservas = numeroReserva;
	}



	public Huesped(Integer id, String nombre, String apellido, LocalDate fechaNacimiento,  String nacionalidad, Integer telefono, Integer numeroReservas) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
		this.nacionalidad = nacionalidad;
		this.telefono = telefono;
		this.numeroReservas = numeroReservas;
	}


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
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
	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getNacionalidad() {
		return nacionalidad;
	}
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	public Integer getTelefono() {
		return telefono;
	}
	public void setTelefono(Integer telefono) {
		this.telefono = telefono;
	}
	public Integer getNumeroReservas() {
		return numeroReservas;
	}
	public void setNumeroReservas(Integer numeroReservas) {
		this.numeroReservas = numeroReservas;
	}


	@Override
	public String toString() {
		return "ID: " + id + ", NOMBRE: " + nombre + ", APELLIDO: " + apellido + ", FECHA-NAC: "
				+ fechaNacimiento + ", NACIONALIDAD: " + nacionalidad + ", TELEFONO: " + telefono + ", N° RESERVA: "
				+ numeroReservas + "]";
	}
	
	
}
