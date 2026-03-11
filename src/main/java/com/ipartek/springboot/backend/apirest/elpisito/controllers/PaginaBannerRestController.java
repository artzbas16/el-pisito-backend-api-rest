package com.ipartek.springboot.backend.apirest.elpisito.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ipartek.springboot.backend.apirest.elpisito.dtos.BannerIdDTO;
import com.ipartek.springboot.backend.apirest.elpisito.dtos.BannerImagenDTO;
import com.ipartek.springboot.backend.apirest.elpisito.services.PaginaBannerServiceImpl;

@RestController
@RequestMapping("/api")
public class PaginaBannerRestController {
	@Autowired
	private PaginaBannerServiceImpl paginaBannerService;
	
	@PostMapping("/pagina-banner")
	public ResponseEntity<BannerImagenDTO> addBannertoPagina(
			@RequestParam Long paginaId, //El nombre utilizado "paginaId" debe coincidir con el nombre utilizado en cliente
			@RequestParam Long bannerId
			){
		
		return ResponseEntity.ok(paginaBannerService.addBannerToPagina(paginaId, bannerId));//200
		
	}
	
	@DeleteMapping("/pagina-banner")
	public ResponseEntity<BannerImagenDTO> deleteBannertoPagina(
			@RequestParam Long paginaId, //El nombre utilizado "paginaId" debe coincidir con el nombre utilizado en cliente
			@RequestParam Long bannerId
			){
		
		return ResponseEntity.ok(paginaBannerService.deleteBannerToPagina(paginaId, bannerId));//200
		
	}
	
	@GetMapping("/banners-pagina/{id}")
	public ResponseEntity<List<BannerImagenDTO>> findBannersPagina(@PathVariable Long id){
		return ResponseEntity.status(HttpStatus.CREATED).body(paginaBannerService.findBannersPagina(id));
	}
	
	@GetMapping("/bannersid-pagina/{id}")
	public ResponseEntity<List<BannerIdDTO>> findBannersIdPagina(@PathVariable Long id){
		return ResponseEntity.status(HttpStatus.CREATED).body(paginaBannerService.findBannersIdPagina(id));
	}
	
}
