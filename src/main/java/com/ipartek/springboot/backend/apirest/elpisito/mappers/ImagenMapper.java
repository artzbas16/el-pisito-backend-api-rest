package com.ipartek.springboot.backend.apirest.elpisito.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ipartek.springboot.backend.apirest.elpisito.dtos.ImagenDTO;
import com.ipartek.springboot.backend.apirest.elpisito.entities.Imagen;

@Mapper(componentModel = "spring")
public interface ImagenMapper {
	
	@Mapping(
			target = "url", 
			expression = "java(construirUrl(imagen))"
			)
	ImagenDTO toDto(Imagen imagen);
	
	//Importante: en el caso de las listas (en este caso) no se necesita hacer nada dado que el 
	//Mapper hace el mapeo automaticamente (toma el dato del mapeo individual: los elementos se 
	//van mappeando uno auno tal y como hemos especificado en "expression")
	List<ImagenDTO> tDtoList(List<Imagen> imagenes);//Mappea listas (muy util para BULK como, por ejemplo, en InmuebleService)
	
	//En una unterface de mapstruct podemos implementar metodos utilizando "default" (desde Java 8)
	default String construirUrl(Imagen img) {
		return "api/imagenes/" + img.getEntidadImagen() + "/" + img.getEntidadId() + "/" + img.getNombre();
	}
}

/*En el ecosistema de Spring, especialmente en Spring Data JPA, el termino "Bulk" (a granel o en masa)
 * se refiere a la capacidad de precesar, leer, escribir o actualizar grandes volumenes de datos en una sola
 * operacion o en bloques (chunks) eficientes, en lugar de procesar registro por registro.
 * El objetivo principal es optimizar el rendimiento, reducir el trafico de red con la BBDD y manejar eficientemente
 * la memoria al mover o transformar informacion.
 * EN nuestro caso va a evitar tener que hacer cientos de llamadas a la API*/