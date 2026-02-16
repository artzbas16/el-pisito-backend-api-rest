package com.ipartek.springboot.backend.apirest.elpisito.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ipartek.springboot.backend.apirest.elpisito.entities.Imagen;
import java.util.List;
import java.util.Optional;

import com.ipartek.springboot.backend.apirest.elpisito.utilities.EntidadImagen;


@Repository
public interface ImagenRepository extends JpaRepository<Imagen, Long>{
	//DERIVED QUERY METHODS
	List<Imagen> findByEntidadImagenAndEntidadId(EntidadImagen entidadImagen, Long entidadId);
	
	Optional<Imagen> findFirstByEntidadImagenAndEntidadId(EntidadImagen entidadImagen, Long entidadId);
}
