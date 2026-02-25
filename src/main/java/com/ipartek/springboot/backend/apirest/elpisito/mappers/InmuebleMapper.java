package com.ipartek.springboot.backend.apirest.elpisito.mappers;

import java.util.List;
import java.util.Map;

import org.mapstruct.AfterMapping;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.ipartek.springboot.backend.apirest.elpisito.dtos.ImagenDTO;
import com.ipartek.springboot.backend.apirest.elpisito.dtos.InmuebleIdDTO;
import com.ipartek.springboot.backend.apirest.elpisito.dtos.InmuebleImagenDTO;
import com.ipartek.springboot.backend.apirest.elpisito.entities.Inmueble;
import com.ipartek.springboot.backend.apirest.elpisito.enumerators.EntidadImagen;
import com.ipartek.springboot.backend.apirest.elpisito.services.ImagenServiceImpl;

@Mapper(componentModel = "spring")
public interface InmuebleMapper {
	
	List<InmuebleIdDTO> toDtoIdList(List<Inmueble> inmuebles);
	
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
	
	//Primero mapstruct mapea los campos normales (en nuestro caso no tiene en cuenta "imagenes" --> (target = "imagenes", ignore = true))
	//Despues automaticamente ejecuta @AfterMapping (no lo tenemos que llamar)
	//1) Ejecuta la logica PERSONALIZADA despues del @Mapping
	//2) Añade datos que no vienen en el objeto de origen (en nuestro caso Inmueble viene sin imagenes...)
	//3) Usar un Servicio dentro el Mapper (en nuestro caso @Context ImagenService imagenService)
	//4) Mantener separado el mapeo normal del mapeo Bulk
	//5) Evitar meter logica de negocio dentro del mapping automatico
	//RESUMEN: ¿Cuando lo empleamos? Cuando mapstruct termino su trabajo, yo completo lo que falta
	@AfterMapping
	default void cargarImagenes(Inmueble inmueble, @MappingTarget InmuebleImagenDTO dto, @Context ImagenServiceImpl imagenService) {
		if (imagenService != null) {
			dto.setImagenes(imagenService.getImagenes(EntidadImagen.INMUEBLE, inmueble.getId()));
		}
	}
	
	
	
}
