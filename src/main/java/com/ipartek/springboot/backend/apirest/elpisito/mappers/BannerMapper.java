package com.ipartek.springboot.backend.apirest.elpisito.mappers;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ipartek.springboot.backend.apirest.elpisito.dtos.BannerIdDTO;
import com.ipartek.springboot.backend.apirest.elpisito.dtos.BannerImagenDTO;
import com.ipartek.springboot.backend.apirest.elpisito.entities.Banner;

@Mapper(componentModel = "spring")
public interface BannerMapper {
	
	List<BannerIdDTO> toDtoIdList(List<Banner> banners);

	@Mapping(target = "imagenes", ignore = true)
	BannerImagenDTO toDto(Banner banner);
	
	List<BannerImagenDTO> toDtoList(List<Banner> banners);
		
}
