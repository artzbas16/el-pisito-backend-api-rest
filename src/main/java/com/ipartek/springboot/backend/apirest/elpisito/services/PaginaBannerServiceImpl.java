package com.ipartek.springboot.backend.apirest.elpisito.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipartek.springboot.backend.apirest.elpisito.dtos.BannerDTO;
import com.ipartek.springboot.backend.apirest.elpisito.dtos.BannerIdDTO;
import com.ipartek.springboot.backend.apirest.elpisito.entities.Banner;
import com.ipartek.springboot.backend.apirest.elpisito.entities.Pagina;
import com.ipartek.springboot.backend.apirest.elpisito.repositories.BannerRepository;
import com.ipartek.springboot.backend.apirest.elpisito.repositories.PaginaRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PaginaBannerServiceImpl {
	@Autowired
	private PaginaRepository paginaRepository;
	
	@Autowired
	private BannerRepository bannerRepository;
	
	public BannerDTO addBannerToPagina(Long paginaId, Long bannerId) {
		Pagina pagina = paginaRepository.findById(paginaId).orElseThrow(() -> new EntityNotFoundException("La pagina con id " + paginaId + " no existe, por lo tanto no podemos incluir el banner"));
		Banner banner = bannerRepository.findById(bannerId).orElseThrow(() -> new EntityNotFoundException("El banner con id " + bannerId + " no existe, por lo tanto no podemos incluir el banner en la pagina "+ paginaId));
		
		pagina.getBannersPagina().add(banner);//Aqui añadimos el banner
		paginaRepository.save(pagina);//Es aqui donde hibernate establece la persistencia en la BBDD
		
		return bannerToBannerDTO(banner, paginaId);
	}
	
	public BannerDTO deleteBannerToPagina(Long paginaId, Long bannerId) {
		Pagina pagina = paginaRepository.findById(paginaId).orElseThrow(() -> new EntityNotFoundException("La pagina con id " + paginaId + " no existe, por lo tanto no podemos quitar el banner de ella"));
		Banner banner = bannerRepository.findById(bannerId).orElseThrow(() -> new EntityNotFoundException("El banner con id " + bannerId + " no existe, por lo tanto no podemos eliminar el banner en la pagina "+ paginaId));
		
		pagina.getBannersPagina().remove(banner);//Aqui eliminamos el banner
		paginaRepository.save(pagina);//Es aqui donde hibernate establece la persistencia en la BBDD
		
		return this.bannerToBannerDTO(banner, paginaId);
	}
	
	//A partir de un id de Pagina devuelve todos los banners incluidos en esa Pagina
	public List<Banner> findBannersPagina(Long paginaId){
		Pagina pagina = paginaRepository.findById(paginaId).orElseThrow(() -> new EntityNotFoundException("La pagina con id " + paginaId + " no existe"));
		
		return new ArrayList<>(pagina.getBannersPagina()); //El constructor del arraylist admite un set
	}
	
	//A partir de un id de Pagina devuelve solo los ids de los banners incluidos en esa Pagina
	public List<BannerIdDTO> findBannersIdPagina(Long paginaId){
		Pagina pagina = paginaRepository.findById(paginaId).orElseThrow(() -> new EntityNotFoundException("La pagina con id " + paginaId + " no existe"));
		
		return new ArrayList<>(bannerToBannerIdDTO(pagina.getBannersPagina())); //El constructor del arraylist admite un set
	}
	
	private List<BannerIdDTO> bannerToBannerIdDTO(Set<Banner> banners) {
		return banners.stream()
			.map(b -> new BannerIdDTO(b.getId()))
			.toList();
	}
	
	private BannerDTO bannerToBannerDTO(Banner banner, Long paginaId) {
		return new BannerDTO(banner.getId(), banner.getTitular(), banner.getClaim(), banner.getLink(), paginaId);
	}
}
