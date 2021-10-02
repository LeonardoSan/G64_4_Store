package co.edu.unbosque.TiendaVirtual.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import co.edu.unbosque.TiendaVirtual.dao.UsuariosDao;
import co.edu.unbosque.TiendaVirtual.modelo.Usuarios;

@Controller
public class AppController {

	@Autowired
	private UsuariosDao usuarioDao;
	
	@Bean
	public ClassLoaderTemplateResolver secondaryTemplateResolver() {
	    ClassLoaderTemplateResolver secondaryTemplateResolver = new ClassLoaderTemplateResolver();
	    secondaryTemplateResolver.setPrefix("templates/");
	    secondaryTemplateResolver.setSuffix(".html");
	    secondaryTemplateResolver.setTemplateMode(TemplateMode.HTML);
	    secondaryTemplateResolver.setCharacterEncoding("UTF-8");
	    secondaryTemplateResolver.setOrder(2);
	    secondaryTemplateResolver.setCheckExistence(true);
	        
	    return secondaryTemplateResolver;
	}
	
	@GetMapping("")
	public String viewHomePage() {
		return "index.html";
	}
	
	@GetMapping("/dash")
	public String viewDashboard(Model model) {
		Usuarios usuario = new Usuarios();
		usuario.setNombre_usuario("Martin");
		model.addAttribute("logUser", usuario);
		return "dash";
	}
	
	@GetMapping("/test")
	public String testing() {
		return "header";
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
