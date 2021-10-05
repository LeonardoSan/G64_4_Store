package co.edu.unbosque.TiendaVirtual.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.TiendaVirtual.model.UsuarioModel;
import co.edu.unbosque.TiendaVirtual.repositories.UsuarioRepository;

@RestController
@RequestMapping("usuarios")
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@PostMapping("/guardar")
	public void guardar(@RequestBody UsuarioModel usuario) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(usuario.getPassword());
		usuario.setPassword(encodedPassword);
		usuarioRepository.save(usuario);
	}
	
	@GetMapping("/listar")
	public List<UsuarioModel> listar() {
		return usuarioRepository.findAll();
	}
	
	@GetMapping("/consultar/{id}")
	public Optional<UsuarioModel> consultar(@PathVariable("id") Long id) {
		return usuarioRepository.findById(id);
	}
	
	@DeleteMapping("/eliminar/{id}")
	public void eliminar(@PathVariable("id") Long id) {
		try{
			usuarioRepository.deleteById(id);
		}
		catch(Exception ex) {
			System.out.println (ex.getMessage());
		}
		
	}

	@PutMapping("/actualizar")
	public void actualizar(@RequestBody UsuarioModel usuario) {
		try{
			usuarioRepository.save(usuario);
		}
		catch(Exception ex) {
			System.out.println (ex.getMessage());
		}
	}
	
	
}
