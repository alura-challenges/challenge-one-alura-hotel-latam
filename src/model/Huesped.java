package model;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Huesped {
	private Integer id;
	private String nombre;
	private String apellido;
	private Date fechaNacimiento;
	private String nacionalidad;
	private Long telefono;
	private Integer idReserver;
	public Huesped() {
		
	}
	
	public Huesped(String nombre, String apellido, Date fechaNacimiento, String nacionalidad, Long telefono,
			Integer idReserver) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
		this.nacionalidad = nacionalidad;
		this.telefono = telefono;
		this.idReserver = idReserver;
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
	public Long getTelefono() {
		return telefono;
	}
	public void setTelefono(Long telefono) {
		this.telefono = telefono;
	}
	public Integer getIdReserver() {
		return idReserver;
	}
	public void setIdReserver(Integer idReserver) {
		this.idReserver = idReserver;
	}

	@Override
	public String toString() {
		return "Huesped [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", fechaNacimiento="
				+ fechaNacimiento + ", nacionalidad=" + nacionalidad + ", telefono=" + telefono + ", idReserver="
				+ idReserver + "]";
	}
	public void formatoFechas() {
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
		// Convertir las fechas a cadenas de texto en el formato especificado
		String fechaNacimientoSQL = formatoFecha.format(this.fechaNacimiento);
		
        try {
        	this.fechaNacimiento = formatoFecha.parse(fechaNacimientoSQL);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
}
