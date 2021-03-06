package co.edu.unbosque.TiendaVirtual.model;

import javax.persistence.*;

@Entity
@Table (name = "ventas")
public class VentasModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo; //!< Sell identification number
	
	@Column(nullable = false)
	private Double iva_venta; //!< IVA sell
	
	@Column(nullable = false)
	private Double valor_venta; //!< value sell
	
	@Column(nullable = false)
	private Double total_venta; //!< total sell
	
	@ManyToOne
	private ClienteModel cliente;
	
	@ManyToOne
	private UsuarioModel usuario;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Double getIva_venta() {
		return iva_venta;
	}

	public void setIva_venta(Double iva_venta) {
		this.iva_venta = iva_venta;
	}

	public Double getValor_venta() {
		return valor_venta;
	}

	public void setValor_venta(Double valor_venta) {
		this.valor_venta = valor_venta;
	}

	public Double getTotal_venta() {
		return total_venta;
	}

	public void setTotal_venta(Double total_venta) {
		this.total_venta = total_venta;
	}

	public ClienteModel getCliente() {
		return cliente;
	}

	public void setCliente(ClienteModel cliente) {
		this.cliente = cliente;
	}

	public UsuarioModel getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioModel usuario) {
		this.usuario = usuario;
	}
}
