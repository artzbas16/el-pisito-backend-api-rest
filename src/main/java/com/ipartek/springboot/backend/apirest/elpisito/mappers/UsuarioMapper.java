package com.ipartek.springboot.backend.apirest.elpisito.mappers;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.ipartek.springboot.backend.apirest.elpisito.entities.Usuario;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void updateUsuarioFromDto(Usuario usuarioRequest, @MappingTarget Usuario usuarioEntity);
	
}

//@MappingTarget: actualiza la entidad existente
//IGNORE: si en el DTO viene null --> no pisa el valor actual
//usuarioRequest: lo que llega al Controller (incompleto)
//usuarioEntity: lo que llega de la BBDD (completo)
