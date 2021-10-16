package co.edu.unbosque.TiendaVirtual.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.TiendaVirtual.model.DetalleVentaModel;
import co.edu.unbosque.TiendaVirtual.repositories.DetalleVentaRepository;

@RestController
@RequestMapping("detalle_ventas")
public class DetalleVentaService {
	
	@Autowired
	private DetalleVentaRepository detalleVentaRepository;
	
	@PostMapping("/guardar")
	public boolean guardar(@RequestBody DetalleVentaModel venta) {
		return detalleVentaRepository.save(venta) != null;
	}
	
}
