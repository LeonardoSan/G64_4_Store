package co.edu.unbosque.TiendaVirtual.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.TiendaVirtual.model.VentasModel;
import co.edu.unbosque.TiendaVirtual.repositories.VentaRepository;

@RestController
@RequestMapping("ventas")
public class VentaService {

	@Autowired
	private VentaRepository ventaRepository;
	
	@PostMapping("/guardar")
	public VentasModel guardar(@RequestBody VentasModel venta) {
		
		
		
		 return ventaRepository.save(venta);
	}
}
