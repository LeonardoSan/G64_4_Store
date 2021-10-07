package co.edu.unbosque.TiendaVirtual.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.unbosque.TiendaVirtual.model.ProveedorModel;

public interface ProveedorRepository extends JpaRepository<ProveedorModel, Long>{
	public Optional<ProveedorModel> findById(Long id);
	public List<ProveedorModel> findAll();
	public void deleteById(Long id);
}
