package co.edu.unbosque.TiendaVirtual.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import co.edu.unbosque.TiendaVirtual.dao.UsuariosDao;
import co.edu.unbosque.TiendaVirtual.modelo.Usuarios;

@Controller
public class AppController {

	@Autowired
	private UsuariosDao usuarioDao;
	
	@GetMapping("")
	public String viewHomePage() {
		return "index.html";
	}
	
	@GetMapping("/dash")
	public String viewDashboard(Model model) {
		
		Usuarios usuario = new Usuarios();
		
		List<Usuarios> listUsers = usuarioDao.findAll();
		
		usuario.setNombre_usuario("Martin Santiago");
		model.addAttribute("logUser", usuario);
		model.addAttribute("listUsers", listUsers);
		
		return "dash";
	}
	
	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new Usuarios());
		
		return "signup_form";
	}
	
	@PostMapping("/process_register")
	public String processRegister(Usuarios usuario) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(usuario.getPassword());
		usuario.setPassword(encodedPassword);
		
		
		usuarioDao.save(usuario);
		
		return "register_success";
	}
}
