package co.edu.unbosque.TiendaVirtual.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table (name = "productos")
public class ProductosModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, unique = true)
	private Long codigo; //!< Product identification number
	
	@Column(nullable = false)
	private String nombre; //!< Product name
	
	@Column(nullable = false)
	private Double iva_compra; //!< IVA buy
	
	@Column(nullable = false)
	private Double precio_compra; //!< Product price buy
	
	@Column(nullable = false)
	private Double precio_venta; //!< Product price sell
	
	@ManyToOne()
	@JoinColumn(name="nit_proveedor", referencedColumnName = "nit", nullable = false)    
	private ProveedorModel proveedor;
	
	@ManyToMany(cascade = {CascadeType.MERGE},mappedBy="productos")
	private List<VentasModel> ventas;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Double getIva_compra() {
		return iva_compra;
	}

	public void setIva_compra(Double iva_compra) {
		this.iva_compra = iva_compra;
	}

	public Double getPrecio_compra() {
		return precio_compra;
	}

	public void setPrecio_compra(Double precio_compra) {
		this.precio_compra = precio_compra;
	}

	public Double getPrecio_venta() {
		return precio_venta;
	}

	public void setPrecio_venta(Double precio_venta) {
		this.precio_venta = precio_venta;
	}

	public ProveedorModel getProveedor() {
		return proveedor;
	}

	public void setProveedor(ProveedorModel proveedor) {
		this.proveedor = proveedor;
	}
	
	public List<VentasModel> getVentas() {
		return ventas;
	}

	public void setVentas(List<VentasModel> ventas) {
		this.ventas = ventas;
	}

	@Override
	public String toString() {
		return "Producto{" +
				", codigo=" + codigo + '\'' +
				", nombre=" + nombre + '\'' +
				", iva_compra=" + iva_compra + '\'' +
				", precio_compra=" + precio_compra + '\'' +
				", precio_venta=" + precio_venta +
				'}';
	}
}
