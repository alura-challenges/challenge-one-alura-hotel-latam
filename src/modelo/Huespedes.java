package modelo;

import java.sql.Date;

public class Huespedes {

	private Integer id;
	private String nombre;
	private String apellido;
	private Date fecha_de_nacimiento;
	private String nacionalidad;
	private String telefono;
	private Integer id_reseva;
	
	
	

	public Huespedes(String nombre, String apellido, Date fecha_de_nacimiento, String nacionalidad, String telefono,
			Integer id_reseva) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.fecha_de_nacimiento = fecha_de_nacimiento;
		this.nacionalidad = nacionalidad;
		this.telefono = telefono;
		this.id_reseva = id_reseva;
	}
	
	

	public Huespedes(Integer id, String nombre, String apellido, Date fecha_de_nacimiento, String nacionalidad,
			String telefono, Integer id_reseva) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fecha_de_nacimiento = fecha_de_nacimiento;
		this.nacionalidad = nacionalidad;
		this.telefono = telefono;
		this.id_reseva = id_reseva;
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

	public Date getFecha_de_nacimiento() {
		return fecha_de_nacimiento;
	}

	public void setFecha_de_nacimiento(Date fecha_de_nacimiento) {
		this.fecha_de_nacimiento = fecha_de_nacimiento;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Integer getId_reseva() {
		return id_reseva;
	}

	public void setId_reseva(Integer  id_reseva) {
		this.id_reseva = id_reseva;
	}
	
}
