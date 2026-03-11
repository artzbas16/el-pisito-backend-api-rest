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
		
		BannerCarouselImagenDTO elBannerCarousel = bannerCarouselMapper.toDto(bannerCarousel);
		
		List<ImagenDTO> imagenesBannerCarousel = imagenService.getImagenes(EntidadImagen.BANNER_CAROUSEL, elBannerCarousel.getId());
		
		elBannerCarousel.setImagenes(imagenesBannerCarousel);
		return elBannerCarousel;
	}
	
	public BannerCarouselImagenDTO deleteBannerCarouselToTematica(Long tematicaId, Long bannerCarouselId) {
		Tematica tematica = tematicaRepository.findById(tematicaId).orElseThrow(() -> new EntityNotFoundException("La tematica con id " + tematicaId + " no existe, por lo tanto no podemos incluir el banner"));
		BannerCarousel bannerCarousel = bannerCarouselRepository.findById(bannerCarouselId).orElseThrow(() -> new EntityNotFoundException("El banner carousel con id " + bannerCarouselId + " no existe, por lo tanto no podemos incluir el banner carousel en la tematica "+ tematicaId));
		
		tematica.getBannersCarousel().remove(bannerCarousel);//Aqui eliminamos el banner
		tematicaRepository.save(tematica);//Es aqui donde hibernate establece la persistencia en la BBDD
		
		BannerCarouselImagenDTO elBannerCarousel = bannerCarouselMapper.toDto(bannerCarousel);
		
		List<ImagenDTO> imagenesBannerCarousel = imagenService.getImagenes(EntidadImagen.BANNER_CAROUSEL, elBannerCarousel.getId());
		
		elBannerCarousel.setImagenes(imagenesBannerCarousel);
		return elBannerCarousel;
	}
	
	//A partir de un id de Tematica devuelve todos los bannersCarousel incluidos en esa Tematica
	public List<BannerCarouselImagenDTO> findBannersCarouselTematica(Long tematicaId){
		Tematica tematica = tematicaRepository.findById(tematicaId).orElseThrow(() -> new EntityNotFoundException("La tematica con id " + tematicaId + " no existe"));
		List<BannerCarousel> bannerCarouselSinImagenes =  new ArrayList<>(tematica.getBannersCarousel());//El constructor del arraylist admite un set
		
		List<BannerCarouselImagenDTO> dtos = bannerCarouselMapper.toDtoList(bannerCarouselSinImagenes); //Un list de inmuebles sin imagenes
		
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
	
	//A partir de un id de Tematica devuelve solo los ids de los bannersCarousel incluidos en esa Tematica
	public List<BannerCarouselIdDTO> findBannersCarouselIdTematica(Long tematicaId){
		Tematica tematica = tematicaRepository.findById(tematicaId).orElseThrow(() -> new EntityNotFoundException("La tematica con id " + tematicaId + " no existe"));
		List<BannerCarousel> bannerCarouselSinImagenes =  new ArrayList<>(tematica.getBannersCarousel());//El constructor del arraylist admite un set
		
		return bannerCarouselMapper.toDtoIdList(bannerCarouselSinImagenes);
	}
}
