package co.edu.unbosque.TiendaVirtual.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.unbosque.TiendaVirtual.modelo.*;

public interface UsuariosDao extends JpaRepository<Usuarios, Long> {
	public Optional<Usuarios> findById(Long id);
	public List<Usuarios> findAll();
	public void deleteById(Long id);
}
