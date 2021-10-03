package co.edu.unbosque.TiendaVirtual.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.unbosque.TiendaVirtual.modelo.Clientes;

public interface ClientesDao extends JpaRepository<Clientes, Long> {
	public Optional<Clientes> findById(Long id);
	public List<Clientes> findAll();
	public void deleteById(Long id);
}
