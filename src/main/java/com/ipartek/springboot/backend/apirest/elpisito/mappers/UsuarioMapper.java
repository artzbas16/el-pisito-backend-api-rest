package com.ipartek.springboot.backend.apirest.elpisito.mappers;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.ipartek.springboot.backend.apirest.elpisito.dtos.UsuarioDTO;
import com.ipartek.springboot.backend.apirest.elpisito.entities.Usuario;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void updateUsuarioFromDto(Usuario usuarioRequest, @MappingTarget Usuario usuarioEntity);
	
	//UsuarioDTO recibe como rol un String. Parece que MapStruct aplica el toString en estos casos
	UsuarioDTO toDto(Usuario usuario); //No hace falta @Mapping por que no necesitamos "aclarar nada" a MapStruct
	
	List<UsuarioDTO> toDtoList(List<Usuario> usuarios);
	
}

//@MappingTarget: actualiza la entidad existente
//IGNORE: si en el DTO viene null --> no pisa el valor actual
//usuarioRequest: lo que llega al Controller (incompleto)
//usuarioEntity: lo que llega de la BBDD (completo)
