package co.edu.unbosque.TiendaVirtual.userDetails;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import co.edu.unbosque.TiendaVirtual.model.UsuarioModel;
import co.edu.unbosque.TiendaVirtual.repositories.UsuarioRepository;

public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UsuarioModel usuario = usuarioDao.findByUsername(username);
		if (usuario == null) {
			throw new UsernameNotFoundException("User not found", null);
		}
			
		return new CustomUserDetails(usuario);
	}

	
}
