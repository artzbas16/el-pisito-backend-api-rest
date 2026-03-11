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

		List<BannerImagenDTO> dtos = bannerMapper.toDtoList(banners);
		
		List<Long> bannerIds = dtos.stream()
			.map(BannerImagenDTO::getId)
			.toList();
		
		Map<Long, List<ImagenDTO>> bannersMap = imagenService.getImagenesPorEntidadBulk(EntidadImagen.BANNER, bannerIds);
		
		for(BannerImagenDTO dto: dtos) {
			dto.setImagenes(bannersMap.getOrDefault(dto.getId(), List.of()));
		}
		
		return dtos;
	}
	
	public List<BannerImagenDTO> findAllActiveBulk(){

		List<Banner> banners = bannerRepository.findByActivo(1);
		List<BannerImagenDTO> dtos = bannerMapper.toDtoList(banners);
		
		List<Long> bannerIds = dtos.stream()
			.map(BannerImagenDTO::getId)
			.toList();
		
		Map<Long, List<ImagenDTO>> bannersMap = imagenService.getImagenesPorEntidadBulk(EntidadImagen.BANNER, bannerIds);
		
		for(BannerImagenDTO dto: dtos) {
			dto.setImagenes(bannersMap.getOrDefault(dto.getId(), List.of()));
		}
		
		return dtos;
	}
	
	public BannerImagenDTO save(Banner banner) {
		Banner bannerSinImagenes = bannerRepository.save(banner);
		BannerImagenDTO elBanner = bannerMapper.toDto(bannerSinImagenes);
		
		List<ImagenDTO> imagenesBanners = imagenService.getImagenes(EntidadImagen.BANNER, elBanner.getId());
		
		elBanner.setImagenes(imagenesBanners);
		return elBanner;
	}
	
	public BannerImagenDTO findById(Long id) {
		Banner bannerSinImagenes = bannerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("El banner con id " + id + " no existe"));
		BannerImagenDTO elBanner = bannerMapper.toDto(bannerSinImagenes);
		
		List<ImagenDTO> imagenesBanners = imagenService.getImagenes(EntidadImagen.BANNER, elBanner.getId());
		
		elBanner.setImagenes(imagenesBanners);
		return elBanner;
	}
	
	public Banner deleteById(Long id) {
		//Una mejora seria borrar todas las imagenes del inmueble
		Banner banner = bannerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("El banner con id " + id + " no existe"));
		
		bannerRepository.deleteById(id);
		return banner;
	}

}
