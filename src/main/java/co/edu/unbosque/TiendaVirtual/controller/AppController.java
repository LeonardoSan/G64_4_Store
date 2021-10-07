package co.edu.unbosque.TiendaVirtual.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.unbosque.TiendaVirtual.model.ClienteModel;
import co.edu.unbosque.TiendaVirtual.model.ProveedorModel;
import co.edu.unbosque.TiendaVirtual.model.UsuarioModel;
import co.edu.unbosque.TiendaVirtual.repositories.ClienteRepository;
import co.edu.unbosque.TiendaVirtual.repositories.ProveedorRepository;
import co.edu.unbosque.TiendaVirtual.repositories.UsuarioRepository;

@Controller
public class AppController {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ProveedorRepository proveedorRepository;
	
	@GetMapping("")
	public String viewHomePage() {
		return "index.html";
	}
	
	@GetMapping("/dash")
	public String viewDashboard(@RequestParam(name = "qC", required = false) Long cedula,  Model model) {
		List<UsuarioModel> listUsers = new ArrayList<UsuarioModel>();
				
		if (cedula != null) {
			UsuarioModel tempUser = new UsuarioModel();
			tempUser = usuarioRepository.findByCedula(cedula);
			
			if (tempUser != null) listUsers.add(tempUser);
			else listUsers = usuarioRepository.findAll();
		}
		else listUsers = usuarioRepository.findAll();

		model.addAttribute("listUsers", listUsers);
		model.addAttribute("user", new UsuarioModel());
		
		return "dash";
	}
	
	@GetMapping("/dash/clientes")
	public String viewClientes(Model model) {
			
		List<ClienteModel> listClients = clienteRepository.findAll();
		model.addAttribute("listClients", listClients);
		
		return "clientes";
	}
	
	@GetMapping("/dash/proveedores")
	public String viewProveedores(Model model) {
			
		List<ProveedorModel> listProviders = proveedorRepository.findAll();
		model.addAttribute("listProviders", listProviders);
		
		return "proveedores";
	}
	
	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new UsuarioModel());
		
		return "signup_form";
	}
	
	@PostMapping("/process_register")
	public String processRegister(UsuarioModel usuario) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(usuario.getPassword());
		usuario.setPassword(encodedPassword);
		
		
		usuarioRepository.save(usuario);
		
		return "redirect:/dash";
	}
}
