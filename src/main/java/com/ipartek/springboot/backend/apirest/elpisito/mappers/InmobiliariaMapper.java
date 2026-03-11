package com.ipartek.springboot.backend.apirest.elpisito.mappers;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.ipartek.springboot.backend.apirest.elpisito.dtos.InmobiliariaIdDTO;
import com.ipartek.springboot.backend.apirest.elpisito.dtos.InmobiliariaImagenDTO;
import com.ipartek.springboot.backend.apirest.elpisito.entities.Inmobiliaria;

@Mapper(componentModel = "spring")
public interface InmobiliariaMapper {

	List<InmobiliariaIdDTO> toDtoIdList(List<Inmobiliaria> inmobiliarias);

	@Mapping(target = "imagenes", ignore = true)
	InmobiliariaImagenDTO toDto(Inmobiliaria inmobiliaria);
	
	List<InmobiliariaImagenDTO> toDtoList(List<Inmobiliaria> inmobiliarias);
		
}
