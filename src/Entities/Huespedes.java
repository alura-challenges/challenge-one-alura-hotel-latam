package Entities;

import java.time.LocalDate;

public class Huespedes {
	private int id;
	private String nombre;
	private String apellido;
	private LocalDate fechaNacimiento;
	private int telefono;
	private int numeroReservas;
	
	
	public Huespedes() {
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
	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
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
	
	
}
