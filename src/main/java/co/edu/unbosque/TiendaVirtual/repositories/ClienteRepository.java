package co.edu.unbosque.TiendaVirtual.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.edu.unbosque.TiendaVirtual.model.ClienteModel;

public interface ClienteRepository extends JpaRepository<ClienteModel, Long> {
	public Optional<ClienteModel> findById(Long id);
	public List<ClienteModel> findAll();
	public void deleteById(Long id);
	
	@Query(value = "SELECT u FROM ClienteModel u WHERE u.cedula =:cedula")
	public ClienteModel findByCedula(@Param("cedula") Long cedula);
}
