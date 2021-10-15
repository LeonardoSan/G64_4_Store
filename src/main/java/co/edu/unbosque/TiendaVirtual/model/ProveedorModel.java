package co.edu.unbosque.TiendaVirtual.model;

import javax.persistence.*;

@Entity
@Table (name = "proveedores")
public class ProveedorModel {
	@Id
	@Column(nullable = false, unique = true)
	private Long nit; //!< Provider identification number
	
	@Column(nullable = false)
	private String nombre; //!< Provider name
	
	@Column(nullable = false)
	private String direccion; //!< Provider address
	
	@Column(nullable = false)
	private String telefono; //!< Provider phone's number
	
	@Column(nullable = false)
	private String ciudad; //!< Provider city

	public Long getNit() {
		return nit;
	}

	public void setNit(Long nit) {
		this.nit = nit;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	@Override
	public String toString() {
		return "Proveedor{" +
				", nit=" + nit + '\'' +
				", nombre=" + nombre + '\'' +
				", direccion=" + direccion + '\'' +
				", telefono=" + telefono + '\'' +
				", ciudad=" + ciudad +
				'}';
	}

}
