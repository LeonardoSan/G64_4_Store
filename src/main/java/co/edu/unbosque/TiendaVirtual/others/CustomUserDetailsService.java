package co.edu.unbosque.TiendaVirtual.others;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import co.edu.unbosque.TiendaVirtual.dao.UsuariosDao;
import co.edu.unbosque.TiendaVirtual.modelo.Usuarios;

public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UsuariosDao usuarioDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Usuarios usuario = usuarioDao.findByUsername(username);
		if (usuario == null) {
			throw new UsernameNotFoundException("User not found", null);
		}
			
		return new CustomUserDetails(usuario);
	}

	
}
