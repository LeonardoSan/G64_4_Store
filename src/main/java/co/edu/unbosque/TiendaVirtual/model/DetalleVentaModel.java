package co.edu.unbosque.TiendaVirtual.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table (name = "detalle_ventas")
public class DetalleVentaModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo_detalle_venta; //!< Sell detail code
	
	@Column(nullable = false)
	private Long cantidad_producto; //!< IVA sell
	
	@Column(nullable = false)
	private Double valor_total; //!< total value
	
	@Column(nullable = false)
	private Double valor_venta; //!< sell value
	
	@Column(nullable = false)
	private Double valor_iva; //!< iva value
	
	@ManyToOne
	private VentasModel ventas;
	
	@ManyToOne
	private ProductosModel productos;

	public Long getCodigo_detalle_venta() {
		return codigo_detalle_venta;
	}

	public void setCodigo_detalle_venta(Long codigo_detalle_venta) {
		this.codigo_detalle_venta = codigo_detalle_venta;
	}

	public Long getCantidad_producto() {
		return cantidad_producto;
	}

	public void setCantidad_producto(Long cantidad_producto) {
		this.cantidad_producto = cantidad_producto;
	}

	public Double getValor_total() {
		return valor_total;
	}

	public void setValor_total(Double valor_total) {
		this.valor_total = valor_total;
	}

	public Double getValor_venta() {
		return valor_venta;
	}

	public void setValor_venta(Double valor_venta) {
		this.valor_venta = valor_venta;
	}

	public Double getValor_iva() {
		return valor_iva;
	}

	public void setValor_iva(Double valor_iva) {
		this.valor_iva = valor_iva;
	}

	public VentasModel getVentas() {
		return ventas;
	}

	public void setVentas(VentasModel ventas) {
		this.ventas = ventas;
	}

	public ProductosModel getProductos() {
		return productos;
	}

	public void setProductos(ProductosModel productos) {
		this.productos = productos;
	}
	
	
}
