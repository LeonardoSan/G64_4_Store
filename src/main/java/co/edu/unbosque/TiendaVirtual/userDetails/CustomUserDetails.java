package co.edu.unbosque.TiendaVirtual.userDetails;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import co.edu.unbosque.TiendaVirtual.model.UsuarioModel;

@SuppressWarnings("serial")
public class CustomUserDetails implements UserDetails {

	private UsuarioModel usuario;
	
	public CustomUserDetails(UsuarioModel usuario) {
		this.usuario = usuario;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() {
		return usuario.getPassword();
	}

	@Override
	public String getUsername() {
		return usuario.getUsuario();
	}
	
	public Long getCedula() {
		return usuario.getCedula_usuario();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
