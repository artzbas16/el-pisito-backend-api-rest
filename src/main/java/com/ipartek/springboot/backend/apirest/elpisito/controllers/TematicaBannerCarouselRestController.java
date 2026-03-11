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

import com.ipartek.springboot.backend.apirest.elpisito.dtos.BannerCarouselIdDTO;
import com.ipartek.springboot.backend.apirest.elpisito.dtos.BannerCarouselImagenDTO;
import com.ipartek.springboot.backend.apirest.elpisito.services.TematicaBannerCarouselServiceImpl;

@RestController
@RequestMapping("/api")
public class TematicaBannerCarouselRestController {
	@Autowired
	private TematicaBannerCarouselServiceImpl tematicaBannerCarouselService;
	
	@PostMapping("/tematica-bannercarousel")
	public ResponseEntity<BannerCarouselImagenDTO> addBannerCarouseltoTematica(
			@RequestParam Long tematicaId, //El nombre utilizado "paginaId" debe coincidir con el nombre utilizado en cliente
			@RequestParam Long bannerCarouselId
			){
		
		return ResponseEntity.ok(tematicaBannerCarouselService.addBannerCarouselToTematica(tematicaId, bannerCarouselId));//200
		
	}
	
	@DeleteMapping("/tematica-bannercarousel")
	public ResponseEntity<BannerCarouselImagenDTO> deleteBannerCarouseltoTematica(
			@RequestParam Long tematicaId, //El nombre utilizado "paginaId" debe coincidir con el nombre utilizado en cliente
			@RequestParam Long bannerCarouselId
			){
		
		return ResponseEntity.ok(tematicaBannerCarouselService.deleteBannerCarouselToTematica(tematicaId, bannerCarouselId));//200
		
	}
	
	@GetMapping("/bannerscarousel-tematica/{id}")
	public ResponseEntity<List<BannerCarouselImagenDTO>> findBannersCarouselTematica(@PathVariable Long id){
		return ResponseEntity.status(HttpStatus.CREATED).body(tematicaBannerCarouselService.findBannersCarouselTematica(id));
	}
	
	@GetMapping("/bannerscarouselid-tematica/{id}")
	public ResponseEntity<List<BannerCarouselIdDTO>> findBannersCarouselIdTematica(@PathVariable Long id){
		return ResponseEntity.status(HttpStatus.CREATED).body(tematicaBannerCarouselService.findBannersCarouselIdTematica(id));
	}
}
