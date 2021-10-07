package co.edu.unbosque.TiendaVirtual.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.TiendaVirtual.model.ProveedorModel;
import co.edu.unbosque.TiendaVirtual.repositories.ProveedorRepository;

@RestController
@RequestMapping("proveedores")
public class ProveedorService {
	@Autowired
	private ProveedorRepository proveedorRepository;
	
	@PostMapping("/guardar")
	public void guardar(@RequestBody ProveedorModel proveedor) {
		proveedorRepository.save(proveedor);
	}
	
	@GetMapping("/listar")
	public List<ProveedorModel> listar() {
		return proveedorRepository.findAll();
	}
	
	@GetMapping("/consultar/{id}")
	public Optional<ProveedorModel> consultar(@PathVariable("id") Long id) {
		return proveedorRepository.findById(id);
	}
	
	@DeleteMapping("/eliminar/{id}")
	public void eliminar(@PathVariable("id") Long id) {
		proveedorRepository.deleteById(id);
	}
	
	@PutMapping("/actualizar")
	public void actualizar(@RequestBody ProveedorModel proveedor) {
		proveedorRepository.save(proveedor);
	}
	
}
