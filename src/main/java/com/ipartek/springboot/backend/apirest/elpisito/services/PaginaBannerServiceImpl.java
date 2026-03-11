package com.ipartek.springboot.backend.apirest.elpisito.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipartek.springboot.backend.apirest.elpisito.dtos.BannerIdDTO;
import com.ipartek.springboot.backend.apirest.elpisito.dtos.BannerImagenDTO;
import com.ipartek.springboot.backend.apirest.elpisito.dtos.ImagenDTO;
import com.ipartek.springboot.backend.apirest.elpisito.entities.Banner;
import com.ipartek.springboot.backend.apirest.elpisito.entities.Pagina;
import com.ipartek.springboot.backend.apirest.elpisito.enumerators.EntidadImagen;
import com.ipartek.springboot.backend.apirest.elpisito.mappers.BannerMapper;
import com.ipartek.springboot.backend.apirest.elpisito.repositories.BannerRepository;
import com.ipartek.springboot.backend.apirest.elpisito.repositories.PaginaRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PaginaBannerServiceImpl {
	@Autowired
	private PaginaRepository paginaRepository;
	
	@Autowired
	private BannerRepository bannerRepository;
	
	@Autowired
	private BannerMapper bannerMapper;
	
	@Autowired
	private ImagenServiceImpl imagenService;
	
	public BannerImagenDTO addBannerToPagina(Long paginaId, Long bannerId) {
		Pagina pagina = paginaRepository.findById(paginaId).orElseThrow(() -> new EntityNotFoundException("La pagina con id " + paginaId + " no existe, por lo tanto no podemos incluir el banner"));
		Banner banner = bannerRepository.findById(bannerId).orElseThrow(() -> new EntityNotFoundException("El banner con id " + bannerId + " no existe, por lo tanto no podemos incluir el banner en la pagina "+ paginaId));
		
		pagina.getBannersPagina().add(banner);//Aqui añadimos el banner
		paginaRepository.save(pagina);//Es aqui donde hibernate establece la persistencia en la BBDD y se puede producir una excepcion (intentar crear una relacion que ya existe)
		
		BannerImagenDTO elBanner = bannerMapper.toDto(banner);
		
		List<ImagenDTO> imagenesBanners = imagenService.getImagenes(EntidadImagen.BANNER, elBanner.getId());
		
		elBanner.setImagenes(imagenesBanners);
		return elBanner;
	}
	
	public BannerImagenDTO deleteBannerToPagina(Long paginaId, Long bannerId) {
		Pagina pagina = paginaRepository.findById(paginaId).orElseThrow(() -> new EntityNotFoundException("La pagina con id " + paginaId + " no existe, por lo tanto no podemos quitar el banner de ella"));
		Banner banner = bannerRepository.findById(bannerId).orElseThrow(() -> new EntityNotFoundException("El banner con id " + bannerId + " no existe, por lo tanto no podemos eliminar el banner en la pagina "+ paginaId));
		
		pagina.getBannersPagina().remove(banner);//Aqui eliminamos el banner
		paginaRepository.save(pagina);//Es aqui donde hibernate establece la persistencia en la BBDD
		
		BannerImagenDTO elBanner = bannerMapper.toDto(banner);
		
		List<ImagenDTO> imagenesBanners = imagenService.getImagenes(EntidadImagen.BANNER, elBanner.getId());
		
		elBanner.setImagenes(imagenesBanners);
		return elBanner;
	}
	
	//A partir de un id de Pagina devuelve todos los banners incluidos en esa Pagina
	public List<BannerImagenDTO> findBannersPagina(Long paginaId){
		Pagina pagina = paginaRepository.findById(paginaId).orElseThrow(() -> new EntityNotFoundException("La pagina con id " + paginaId + " no existe"));
		List<Banner> bannerSinImagenes =  new ArrayList<>(pagina.getBannersPagina());//El constructor del arraylist admite un set
		
		List<BannerImagenDTO> dtos = bannerMapper.toDtoList(bannerSinImagenes);
		
		List<Long> bannerIds = dtos.stream()
			.map(BannerImagenDTO::getId)
			.toList();
		
		Map<Long, List<ImagenDTO>> bannersMap = imagenService.getImagenesPorEntidadBulk(EntidadImagen.BANNER, bannerIds);
		
		for(BannerImagenDTO dto: dtos) {
			dto.setImagenes(bannersMap.getOrDefault(dto.getId(), List.of()));
		}
		
		return dtos;
	}
	
	//A partir de un id de Pagina devuelve solo los ids de los banners incluidos en esa Pagina
	public List<BannerIdDTO> findBannersIdPagina(Long paginaId){
		Pagina pagina = paginaRepository.findById(paginaId).orElseThrow(() -> new EntityNotFoundException("La pagina con id " + paginaId + " no existe"));
		List<Banner> bannerSinImagenes =  new ArrayList<>(pagina.getBannersPagina());//El constructor del arraylist admite un set
		
		return bannerMapper.toDtoIdList(bannerSinImagenes);
	}
	
}
