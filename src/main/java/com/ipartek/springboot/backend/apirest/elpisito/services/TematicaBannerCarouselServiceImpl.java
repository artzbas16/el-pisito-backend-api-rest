package com.ipartek.springboot.backend.apirest.elpisito.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipartek.springboot.backend.apirest.elpisito.dtos.BannerCarouselIdDTO;
import com.ipartek.springboot.backend.apirest.elpisito.dtos.BannerCarouselImagenDTO;
import com.ipartek.springboot.backend.apirest.elpisito.dtos.ImagenDTO;
import com.ipartek.springboot.backend.apirest.elpisito.entities.BannerCarousel;
import com.ipartek.springboot.backend.apirest.elpisito.entities.Tematica;
import com.ipartek.springboot.backend.apirest.elpisito.enumerators.EntidadImagen;
import com.ipartek.springboot.backend.apirest.elpisito.mappers.BannerCarouselMapper;
import com.ipartek.springboot.backend.apirest.elpisito.repositories.BannerCarouselRepository;
import com.ipartek.springboot.backend.apirest.elpisito.repositories.TematicaRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TematicaBannerCarouselServiceImpl {
	@Autowired
	private TematicaRepository tematicaRepository;
	
	@Autowired
	private BannerCarouselRepository bannerCarouselRepository;
	
	@Autowired
	private BannerCarouselMapper bannerCarouselMapper;
	
	@Autowired
	private ImagenServiceImpl imagenService;
	
	public BannerCarouselImagenDTO addBannerCarouselToTematica(Long tematicaId, Long bannerCarouselId) {
		Tematica tematica = tematicaRepository.findById(tematicaId).orElseThrow(() -> new EntityNotFoundException("La tematica con id " + tematicaId + " no existe, por lo tanto no podemos incluir el banner"));
		BannerCarousel bannerCarousel = bannerCarouselRepository.findById(bannerCarouselId).orElseThrow(() -> new EntityNotFoundException("El banner carousel con id " + bannerCarouselId + " no existe, por lo tanto no podemos incluir el banner carousel en la tematica "+ tematicaId));
		
		tematica.getBannersCarousel().add(bannerCarousel);//Aqui añadimos el banner
		tematicaRepository.save(tematica);//Es aqui donde hibernate establece la persistencia en la BBDD y se puede producir una excepcion (intentar crear una relacion que ya existe)
		
		return bannerCarouselMapper.toDto(bannerCarousel, imagenService);
	}
	
	public BannerCarouselImagenDTO deleteBannerCarouselToTematica(Long tematicaId, Long bannerCarouselId) {
		Tematica tematica = tematicaRepository.findById(tematicaId).orElseThrow(() -> new EntityNotFoundException("La tematica con id " + tematicaId + " no existe, por lo tanto no podemos incluir el banner"));
		BannerCarousel bannerCarousel = bannerCarouselRepository.findById(bannerCarouselId).orElseThrow(() -> new EntityNotFoundException("El banner carousel con id " + bannerCarouselId + " no existe, por lo tanto no podemos incluir el banner carousel en la tematica "+ tematicaId));
		
		tematica.getBannersCarousel().remove(bannerCarousel);//Aqui eliminamos el banner
		tematicaRepository.save(tematica);//Es aqui donde hibernate establece la persistencia en la BBDD
		
		return bannerCarouselMapper.toDto(bannerCarousel, imagenService);
	}
	
	//A partir de un id de Tematica devuelve todos los bannersCarousel incluidos en esa Tematica
	public List<BannerCarouselImagenDTO> findBannersCarouselTematica(Long tematicaId){
		Tematica tematica = tematicaRepository.findById(tematicaId).orElseThrow(() -> new EntityNotFoundException("La tematica con id " + tematicaId + " no existe"));
		List<BannerCarousel> bannerCarouselSinImagenes =  new ArrayList<>(tematica.getBannersCarousel());//El constructor del arraylist admite un set
		
		List<Long> ids = bannerCarouselSinImagenes.stream()		
				.map(BannerCarousel::getId)
				.toList();	
		Map<Long, List<ImagenDTO>> mapaImagenes = imagenService.getImagenesPorEntidadBulk(EntidadImagen.BANNER, ids);
		
		return bannerCarouselMapper.toDtoBulk(bannerCarouselSinImagenes, mapaImagenes);
	}
	
	//A partir de un id de Tematica devuelve solo los ids de los bannersCarousel incluidos en esa Tematica
	public List<BannerCarouselIdDTO> findBannersCarouselIdTematica(Long tematicaId){
		Tematica tematica = tematicaRepository.findById(tematicaId).orElseThrow(() -> new EntityNotFoundException("La tematica con id " + tematicaId + " no existe"));
		List<BannerCarousel> bannerCarouselSinImagenes =  new ArrayList<>(tematica.getBannersCarousel());//El constructor del arraylist admite un set
		
		return bannerCarouselMapper.toDtoIdList(bannerCarouselSinImagenes);
	}
}
