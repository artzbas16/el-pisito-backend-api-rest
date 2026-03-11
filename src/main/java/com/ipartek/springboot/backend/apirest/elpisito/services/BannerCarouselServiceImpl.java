package com.ipartek.springboot.backend.apirest.elpisito.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipartek.springboot.backend.apirest.elpisito.dtos.BannerCarouselImagenDTO;
import com.ipartek.springboot.backend.apirest.elpisito.dtos.ImagenDTO;
import com.ipartek.springboot.backend.apirest.elpisito.entities.BannerCarousel;
import com.ipartek.springboot.backend.apirest.elpisito.enumerators.EntidadImagen;
import com.ipartek.springboot.backend.apirest.elpisito.mappers.BannerCarouselMapper;
import com.ipartek.springboot.backend.apirest.elpisito.repositories.BannerCarouselRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class BannerCarouselServiceImpl {
	
	@Autowired
	private BannerCarouselMapper bannerCarouselMapper;
	
	@Autowired
	private ImagenServiceImpl imagenService;
	
	@Autowired
	private BannerCarouselRepository bannerCarouselRepository;
	
	public List<BannerCarouselImagenDTO> findAllBulk(){

		List<BannerCarousel> bannersCarousel = bannerCarouselRepository.findAll();
		
		List<BannerCarouselImagenDTO> dtos = bannerCarouselMapper.toDtoList(bannersCarousel); //Un list de inmuebles sin imagenes
		
		//Para crear un bulk necesitamos los id de los inmuebles que esten en dtos
		
		List<Long> bannersCarouselIds = dtos.stream()
			.map(BannerCarouselImagenDTO::getId)
			.toList();
		
		Map<Long, List<ImagenDTO>> bannersCarouselMap = imagenService.getImagenesPorEntidadBulk(EntidadImagen.BANNER_CAROUSEL, bannersCarouselIds);
		
		for(BannerCarouselImagenDTO dto: dtos) {
			dto.setImagenes(bannersCarouselMap.getOrDefault(dto.getId(), List.of()));
		}
		
		return dtos;
	}
	
	public List<BannerCarouselImagenDTO> findAllActiveBulk(){

		List<BannerCarousel> bannersCarousel = bannerCarouselRepository.findByActivo(1);
		
		List<BannerCarouselImagenDTO> dtos = bannerCarouselMapper.toDtoList(bannersCarousel); //Un list de inmuebles sin imagenes
		
		//Para crear un bulk necesitamos los id de los inmuebles que esten en dtos
		
		List<Long> bannersCarouselIds = dtos.stream()
			.map(BannerCarouselImagenDTO::getId)
			.toList();
		
		Map<Long, List<ImagenDTO>> bannersCarouselMap = imagenService.getImagenesPorEntidadBulk(EntidadImagen.BANNER_CAROUSEL, bannersCarouselIds);
		
		for(BannerCarouselImagenDTO dto: dtos) {
			dto.setImagenes(bannersCarouselMap.getOrDefault(dto.getId(), List.of()));
		}
		
		return dtos;
	}
	
	public BannerCarouselImagenDTO save(BannerCarousel bannerCarousel) {
		BannerCarousel bannerCarouselSinImagenes = bannerCarouselRepository.save(bannerCarousel);
		
		BannerCarouselImagenDTO elBannerCarousel = bannerCarouselMapper.toDto(bannerCarouselSinImagenes);
		
		List<ImagenDTO> imagenesBannerCarousel = imagenService.getImagenes(EntidadImagen.BANNER_CAROUSEL, elBannerCarousel.getId());
		
		elBannerCarousel.setImagenes(imagenesBannerCarousel);
		return elBannerCarousel;
	}
	
	public BannerCarouselImagenDTO findById(Long id) {
		BannerCarousel bannerCarouselSinImagenes = bannerCarouselRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("El bannerCarousel con id " + id + " no existe"));
		BannerCarouselImagenDTO elBannerCarousel = bannerCarouselMapper.toDto(bannerCarouselSinImagenes);
		
		List<ImagenDTO> imagenesBannerCarousel = imagenService.getImagenes(EntidadImagen.BANNER_CAROUSEL, elBannerCarousel.getId());
		
		elBannerCarousel.setImagenes(imagenesBannerCarousel);
		return elBannerCarousel;
	}
	
	public BannerCarousel deleteById(Long id) {
		//Una mejora seria borrar todas las imagenes del inmueble
		BannerCarousel bannerCarousel = bannerCarouselRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("El bannerCarousel con id " + id + " no existe"));
		
		bannerCarouselRepository.deleteById(id);
		return bannerCarousel;
	}
}
