package co.edu.unbosque.TiendaVirtual.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import co.edu.unbosque.TiendaVirtual.model.ProductosModel;

public interface ProductoRepository extends JpaRepository<ProductosModel, Long>{
	public Optional<ProductosModel> findById(Long id);
	public List<ProductosModel> findAll();
	public void deleteById(Long id);
	
	@Transactional
	@Modifying
	@Query ("DELETE FROM ProductosModel")
	public void deleteAll();
}

