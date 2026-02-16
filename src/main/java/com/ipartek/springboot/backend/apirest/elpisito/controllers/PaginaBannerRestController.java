package com.ipartek.springboot.backend.apirest.elpisito.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ipartek.springboot.backend.apirest.elpisito.dtos.BannerDTO;
import com.ipartek.springboot.backend.apirest.elpisito.services.PaginaBannerServiceImpl;

@RestController
@RequestMapping("/api")
public class PaginaBannerRestController {
	@Autowired
	private PaginaBannerServiceImpl paginaBannerService;
	
	@PostMapping("/pagina-banner")
	public ResponseEntity<BannerDTO> addBannertoPagina(
			@RequestParam Long paginaId, //El nombre utilizado "paginaId" debe coincidir con el nombre utilizado en cliente
			@RequestParam Long bannerId
			){
		
		return ResponseEntity.ok(paginaBannerService.addBannerToPagina(paginaId, bannerId));//200
		
	}
	
	@DeleteMapping("/pagina-banner")
	public ResponseEntity<BannerDTO> deleteBannertoPagina(
			@RequestParam Long paginaId, //El nombre utilizado "paginaId" debe coincidir con el nombre utilizado en cliente
			@RequestParam Long bannerId
			){
		
		return ResponseEntity.ok(paginaBannerService.deleteBannerToPagina(paginaId, bannerId));//200
		
	}
	
}
