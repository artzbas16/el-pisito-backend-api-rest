package com.ipartek.springboot.backend.apirest.elpisito.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipartek.springboot.backend.apirest.elpisito.dtos.BannerImagenDTO;
import com.ipartek.springboot.backend.apirest.elpisito.dtos.ImagenDTO;
import com.ipartek.springboot.backend.apirest.elpisito.entities.Banner;
import com.ipartek.springboot.backend.apirest.elpisito.enumerators.EntidadImagen;
import com.ipartek.springboot.backend.apirest.elpisito.mappers.BannerMapper;
import com.ipartek.springboot.backend.apirest.elpisito.repositories.BannerRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class BannerServiceImpl {
	
	@Autowired
	private BannerRepository bannerRepository;
	
	@Autowired
	private ImagenServiceImpl imagenService;
	
	@Autowired
	private BannerMapper bannerMapper;
	
	public List<BannerImagenDTO> findAllBulk(){

		List<Banner> banners = bannerRepository.findAll();
		List<Long> ids = banners.stream()			
			.map(Banner::getId)
			.toList();											
		
		Map<Long, List<ImagenDTO>> mapaImagenes = imagenService.getImagenesPorEntidadBulk(EntidadImagen.BANNER, ids);
		
		return bannerMapper.toDtoBulk(banners, mapaImagenes);
	}
	
	public List<BannerImagenDTO> findAllActiveBulk(){

		List<Banner> banners = bannerRepository.findByActivo(1);
		List<Long> ids = banners.stream()		
			.map(Banner::getId)
			.toList();											
		
		Map<Long, List<ImagenDTO>> mapaImagenes = imagenService.getImagenesPorEntidadBulk(EntidadImagen.BANNER, ids); 
		
		return bannerMapper.toDtoBulk(banners, mapaImagenes);
	}
	
	public BannerImagenDTO save(Banner banner) {
		Banner bannerSinImagenes = bannerRepository.save(banner);
		return bannerMapper.toDto(bannerSinImagenes, imagenService);
	}
	
	public BannerImagenDTO findById(Long id) {
		Banner bannerSinImagenes = bannerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("El banner con id " + id + " no existe"));
		return bannerMapper.toDto(bannerSinImagenes, imagenService);
	}
	
	public Banner deleteById(Long id) {
		//Una mejora seria borrar todas las imagenes del inmueble
		Banner banner = bannerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("El banner con id " + id + " no existe"));
		
		bannerRepository.deleteById(id);
		return banner;
	}

}
