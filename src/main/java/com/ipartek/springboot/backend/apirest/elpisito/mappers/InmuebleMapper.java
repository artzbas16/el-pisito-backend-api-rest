package com.ipartek.springboot.backend.apirest.elpisito.mappers;

import java.util.List;
import java.util.Map;

import org.mapstruct.AfterMapping;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.ipartek.springboot.backend.apirest.elpisito.dtos.ImagenDTO;
import com.ipartek.springboot.backend.apirest.elpisito.dtos.InmuebleImagenDTO;
import com.ipartek.springboot.backend.apirest.elpisito.entities.Inmueble;
import com.ipartek.springboot.backend.apirest.elpisito.enumerators.EntidadImagen;
import com.ipartek.springboot.backend.apirest.elpisito.services.ImagenServiceImpl;

@Mapper(componentModel = "spring")
public interface InmuebleMapper {
	
	@Mapping(target = "imagenes", ignore = true)
	InmuebleImagenDTO toDto(Inmueble inmueble, @Context ImagenServiceImpl imagenService);
	
	List<InmuebleImagenDTO> toDtoList(List<Inmueble> inmuebles, @Context ImagenServiceImpl imagenService);
	
	//Este metodo es llamado desde el servicio de Inmueble (InmuebleServiceImpl) desde el metodo findAllBulk()
	//pero puede tambien ser llamado por findAllActiveBulk, findAllPortadaBulk...etc
	default List<InmuebleImagenDTO> toDtoBulk(List<Inmueble> inmuebles, Map<Long, List<ImagenDTO>> imagenesMap){
		
		List<InmuebleImagenDTO> dtos = toDtoList(inmuebles, null);
		
		//El List de DTO (dtos) llega sin imagenes, ahora se las asignamos mediante el Map que recibimos como argumento
		
		for(InmuebleImagenDTO dto : dtos) {
			dto.setImagenes(imagenesMap.getOrDefault(dto.getId(), List.of()));
		}
		
		return dtos; //Ahora con las imagenes
		
	}
	
	@AfterMapping
	default void cargarImagenes(Inmueble inmueble, @MappingTarget InmuebleImagenDTO dto, @Context ImagenServiceImpl imagenService) {
		if (imagenService != null) {
			dto.setImagenes(imagenService.getImagenes(EntidadImagen.INMUEBLE, inmueble.getId()));
		}
	}
	
	
	
}
