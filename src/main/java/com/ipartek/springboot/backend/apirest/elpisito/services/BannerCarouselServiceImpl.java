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
		List<Long> ids = bannersCarousel.stream()			
			.map(BannerCarousel::getId)
			.toList();											
		
		Map<Long, List<ImagenDTO>> mapaImagenes = imagenService.getImagenesPorEntidadBulk(EntidadImagen.BANNER_CAROUSEL, ids);
		
		return bannerCarouselMapper.toDtoBulk(bannersCarousel, mapaImagenes);
	}
	
	public List<BannerCarouselImagenDTO> findAllActiveBulk(){

		List<BannerCarousel> bannersCarousel = bannerCarouselRepository.findByActivo(1);
		List<Long> ids = bannersCarousel.stream()		
			.map(BannerCarousel::getId)
			.toList();											
		
		Map<Long, List<ImagenDTO>> mapaImagenes = imagenService.getImagenesPorEntidadBulk(EntidadImagen.BANNER_CAROUSEL, ids); 
		
		return bannerCarouselMapper.toDtoBulk(bannersCarousel, mapaImagenes);
	}
	
	public BannerCarouselImagenDTO save(BannerCarousel bannerCarousel) {
		BannerCarousel bannerCarouselSinImagenes = bannerCarouselRepository.save(bannerCarousel);
		return bannerCarouselMapper.toDto(bannerCarouselSinImagenes, imagenService);
	}
	
	public BannerCarouselImagenDTO findById(Long id) {
		BannerCarousel bannerCarouselSinImagenes = bannerCarouselRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("El bannerCarousel con id " + id + " no existe"));
		return bannerCarouselMapper.toDto(bannerCarouselSinImagenes, imagenService);
	}
	
	public BannerCarousel deleteById(Long id) {
		//Una mejora seria borrar todas las imagenes del inmueble
		BannerCarousel bannerCarousel = bannerCarouselRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("El bannerCarousel con id " + id + " no existe"));
		
		bannerCarouselRepository.deleteById(id);
		return bannerCarousel;
	}
}
