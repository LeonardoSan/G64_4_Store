package co.edu.unbosque.TiendaVirtual.model;

import java.util.List;

import javax.persistence.*;

@Entity
@Table (name = "clientes")
public class ClienteModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, unique = true)
	private Long cedula; //!< Customer identification number
	
	@Column(nullable = false)
	private String nombre; //!< Customer name
	
	@Column(nullable = false)
	private String direccion; //!< Customer address
	
	@Column(nullable = false)
	private String telefono; //!< Customer phone's number
	
	@Column(nullable = false)
	private String email; //!< Customer email
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="cedula_cliente")
	private List<VentasModel> ventas;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCedula() {
		return cedula;
	}

	public void setCedula(Long cedula) {
		this.cedula = cedula;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public List<VentasModel> getVentas() {
		return ventas;
	}

	public void setVentas(List<VentasModel> ventas) {
		this.ventas = ventas;
	}

	@Override
	public String toString() {
		return "Cliente{" +
				", cedula=" + cedula + '\'' +
				", nombre=" + nombre + '\'' +
				", correo=" + email + '\'' +
				", telefono=" + telefono + '\'' +
				", direccion=" + direccion +
				'}';
	}
	
}
