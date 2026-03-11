package com.ipartek.springboot.backend.apirest.elpisito.mappers;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ipartek.springboot.backend.apirest.elpisito.dtos.BannerCarouselIdDTO;
import com.ipartek.springboot.backend.apirest.elpisito.dtos.BannerCarouselImagenDTO;
import com.ipartek.springboot.backend.apirest.elpisito.entities.BannerCarousel;

@Mapper(componentModel = "spring")
public interface BannerCarouselMapper {
	
	List<BannerCarouselIdDTO> toDtoIdList(List<BannerCarousel> bannersCarousel);

	@Mapping(target = "imagenes", ignore = true)
	BannerCarouselImagenDTO toDto(BannerCarousel bannerCarousel);
	
	List<BannerCarouselImagenDTO> toDtoList(List<BannerCarousel> bannersCarousel);
	
}
