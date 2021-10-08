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

import co.edu.unbosque.TiendaVirtual.model.ClienteModel;
import co.edu.unbosque.TiendaVirtual.repositories.ClienteRepository;

@RestController
@RequestMapping("clientes")
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@PostMapping("/guardar")
	public void guardar(@RequestBody ClienteModel cliente) {
		clienteRepository.save(cliente);
	}
	
	@GetMapping("/listar")
	public List<ClienteModel> listar() {
		return clienteRepository.findAll();
	}
	
	@GetMapping("/consultar/{id}")
	public Optional<ClienteModel> consultar(@PathVariable("id") Long id) {
		return clienteRepository.findById(id);
	}
	
	@DeleteMapping("/eliminar/{id}")
	public void eliminar(@PathVariable("id") Long id) {
		clienteRepository.deleteById(id);
	}
	
	@PutMapping("/actualizar")
	public void actualizar(@RequestBody ClienteModel cliente) {
		clienteRepository.save(cliente);
	}
	
}
