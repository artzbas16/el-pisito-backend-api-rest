package com.ipartek.springboot.backend.apirest.elpisito.mappers;

import java.util.List;
import java.util.Map;

import org.mapstruct.AfterMapping;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.ipartek.springboot.backend.apirest.elpisito.dtos.BannerIdDTO;
import com.ipartek.springboot.backend.apirest.elpisito.dtos.BannerImagenDTO;
import com.ipartek.springboot.backend.apirest.elpisito.dtos.ImagenDTO;
import com.ipartek.springboot.backend.apirest.elpisito.entities.Banner;
import com.ipartek.springboot.backend.apirest.elpisito.enumerators.EntidadImagen;
import com.ipartek.springboot.backend.apirest.elpisito.services.ImagenServiceImpl;

@Mapper(componentModel = "spring")
public interface BannerMapper {
	
	List<BannerIdDTO> toDtoIdList(List<Banner> banners);

	@Mapping(target = "imagenes", ignore = true)
	BannerImagenDTO toDto(Banner banner, @Context ImagenServiceImpl imagenService);
	
	List<BannerImagenDTO> toDtoList(List<Banner> banners, @Context ImagenServiceImpl imagenService);
	
	//Este metodo es llamado desde el servicio de Banner (BannerServiceImpl) desde el metodo findAllBulk()
	//pero puede tambien ser llamado por findAllActiveBulk
	default List<BannerImagenDTO> toDtoBulk(List<Banner> banners, Map<Long, List<ImagenDTO>> imagenesMap){
		
		List<BannerImagenDTO> dtos = toDtoList(banners, null);
		
		//El List de DTO (dtos) llega sin imagenes, ahora se las asignamos mediante el Map que recibimos como argumento
		
		for(BannerImagenDTO dto : dtos) {
			dto.setImagenes(imagenesMap.getOrDefault(dto.getId(), List.of()));
		}
		
		return dtos; //Ahora con las imagenes
		
	}
	
	
	@AfterMapping
	default void cargarImagenes(Banner banner, @MappingTarget BannerImagenDTO dto, @Context ImagenServiceImpl imagenService) {
		if (imagenService != null) {
			dto.setImagenes(imagenService.getImagenes(EntidadImagen.INMUEBLE, banner.getId()));
		}
	}
	
}
