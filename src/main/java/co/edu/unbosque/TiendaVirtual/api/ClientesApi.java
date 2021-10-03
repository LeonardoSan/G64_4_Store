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

import co.edu.unbosque.TiendaVirtual.dao.ClientesDao;
import co.edu.unbosque.TiendaVirtual.modelo.Clientes;

@RestController
@RequestMapping("clientes")
public class ClientesApi {

	@Autowired
	private ClientesDao clientesDao;
	
	@PostMapping("/guardar")
	public void guardar(@RequestBody Clientes cliente) {
		clientesDao.save(cliente);
	}
	
	@GetMapping("/listar")
	public List<Clientes> listar() {
		return clientesDao.findAll();
	}
	
	@GetMapping("/consultar/{id}")
	public Optional<Clientes> consultar(@PathVariable("id") Long id) {
		return clientesDao.findById(id);
	}
	
	@DeleteMapping("/eliminar/{id}")
	public void eliminar(@PathVariable("id") Long id) {
		clientesDao.deleteById(id);
	}
	
	@PutMapping("/actualizar")
	public void actualizar(@RequestBody Clientes cliente) {
		clientesDao.save(cliente);
	}
	
}
