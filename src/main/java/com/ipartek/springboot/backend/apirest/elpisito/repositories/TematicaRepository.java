package com.ipartek.springboot.backend.apirest.elpisito.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ipartek.springboot.backend.apirest.elpisito.entities.Tematica;

@Repository
public interface TematicaRepository extends JpaRepository<Tematica, Long>{
	List<Tematica> findByActivo(Integer activo);
	
	List<Tematica> findByActual(Integer actual);
	
	/* Para lograr que un solo registro este activp en la tabla de Tematica
	 * necesitamos aplicar una logica de actualizacion atomica cada vez que un 
	 * registro se activa*/
	@Modifying
	@Query("UPDATE Tematica t SET t.actual = CASE WHEN t.id = :idTematica THEN 1 ELSE 0 END")
	void desactualizarTodas(Long idTematica);
	
}
