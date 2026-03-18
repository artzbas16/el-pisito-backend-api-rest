package com.ipartek.springboot.backend.apirest.elpisito.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ipartek.springboot.backend.apirest.elpisito.dtos.TematicaDTO;
import com.ipartek.springboot.backend.apirest.elpisito.entities.Tematica;

@Mapper(componentModel = "spring")
public interface TematicaMapper {
	
	@Mapping(target = "numeroBanners", ignore = true)
	TematicaDTO toDto(Tematica tematica);
	
	List<TematicaDTO> toDtoList(List<Tematica> tematicas);

}
