package co.edu.unbosque.TiendaVirtual.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.edu.unbosque.TiendaVirtual.model.ClienteModel;
import co.edu.unbosque.TiendaVirtual.model.VentasModel;

public interface VentaRepository extends JpaRepository<VentasModel, Long>{
	public List<VentasModel> findAll();
	
	@Query(value = "SELECT u FROM VentasModel u WHERE u.codigo =:cedula")
	public ClienteModel findByCedula(@Param("cedula") VentasModel cedula);
}
