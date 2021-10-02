package co.edu.unbosque.TiendaVirtual.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.edu.unbosque.TiendaVirtual.modelo.Usuarios;


public interface UsuariosDao extends JpaRepository<Usuarios, Long> {
	public Optional<Usuarios> findById(Long id);
	public List<Usuarios> findAll();
	public void deleteById(Long id);
	
	@Query(value = "SELECT u FROM Usuarios u WHERE u.usuario = :usuario")
	public Usuarios findByUsername(@Param("usuario") String usuario);
	
}
