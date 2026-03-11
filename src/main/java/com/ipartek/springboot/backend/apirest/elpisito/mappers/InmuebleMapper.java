package com.ipartek.springboot.backend.apirest.elpisito.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.ipartek.springboot.backend.apirest.elpisito.dtos.InmuebleIdDTO;
import com.ipartek.springboot.backend.apirest.elpisito.dtos.InmuebleImagenDTO;
import com.ipartek.springboot.backend.apirest.elpisito.entities.Inmueble;

@Mapper(componentModel = "spring", uses = {InmobiliariaMapper.class})
public interface InmuebleMapper {
	
	List<InmuebleIdDTO> toDtoIdList(List<Inmueble> inmuebles);
	
	@Mapping(target = "imagenes", ignore = true)
	InmuebleImagenDTO toDto(Inmueble inmueble);
	
	List<InmuebleImagenDTO> toDtoList(List<Inmueble> inmuebles);		
	
}
