package co.edu.unbosque.TiendaVirtual.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.edu.unbosque.TiendaVirtual.model.UsuarioModel;


public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> {
	public Optional<UsuarioModel> findById(Long id);
	public List<UsuarioModel> findAll();
	public void deleteById(Long id);
	
	@Query(value = "SELECT u FROM UsuarioModel u WHERE u.usuario = :usuario")
	public UsuarioModel findByUsername(@Param("usuario") String usuario);
	
}
