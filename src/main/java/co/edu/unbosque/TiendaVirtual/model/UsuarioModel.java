package co.edu.unbosque.TiendaVirtual.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "usuarios")
public class UsuarioModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, unique = true)
	private Long cedula_usuario;
	
	private String email_usuario;
	private String nombre_usuario;
	
	@Column(name = "username", nullable = false, unique = true, length = 45)
	private String usuario;
	
	@Column(nullable = false, length = 64)
	private String password;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="cedula_usuario")
	private List<VentasModel> ventas;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}	
	
	public Long getCedula_usuario() {
		return cedula_usuario;
	}
	public void setCedula_usuario(Long cedula_usuario) {
		this.cedula_usuario = cedula_usuario;
	}
	public String getEmail_usuario() {
		return email_usuario;
	}
	public void setEmail_usuario(String email_usuario) {
		this.email_usuario = email_usuario;
	}
	public String getNombre_usuario() {
		return nombre_usuario;
	}
	public void setNombre_usuario(String nombre_usuario) {
		this.nombre_usuario = nombre_usuario;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<VentasModel> getVentas() {
		return ventas;
	}
	public void setVentas(List<VentasModel> ventas) {
		this.ventas = ventas;
	}
}
