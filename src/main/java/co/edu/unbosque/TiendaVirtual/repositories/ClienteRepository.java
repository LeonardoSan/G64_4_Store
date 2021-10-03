package co.edu.unbosque.TiendaVirtual.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.unbosque.TiendaVirtual.model.ClienteModel;

public interface ClienteRepository extends JpaRepository<ClienteModel, Long> {
	public Optional<ClienteModel> findById(Long id);
	public List<ClienteModel> findAll();
	public void deleteById(Long id);
}
