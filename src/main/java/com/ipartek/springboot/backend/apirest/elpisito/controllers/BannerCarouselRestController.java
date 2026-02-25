package com.ipartek.springboot.backend.apirest.elpisito.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ipartek.springboot.backend.apirest.elpisito.dtos.BannerCarouselImagenDTO;
import com.ipartek.springboot.backend.apirest.elpisito.entities.BannerCarousel;
import com.ipartek.springboot.backend.apirest.elpisito.services.BannerCarouselServiceImpl;

@RestController
@RequestMapping("/api")
public class BannerCarouselRestController {
	
	@Autowired
	private BannerCarouselServiceImpl bannerCarouselService;
	
	@GetMapping("/banners-carousel")
	public ResponseEntity<List<BannerCarouselImagenDTO>> findAll(){
		return ResponseEntity.ok(bannerCarouselService.findAllBulk()); //200
	}
	
	@GetMapping("/banners-carousel-activos")
	public ResponseEntity<List<BannerCarouselImagenDTO>> findAllActive(){		
		return ResponseEntity.ok(bannerCarouselService.findAllActiveBulk()); //200
	}
	
	@GetMapping("/banner-carousel/{id}")
	public ResponseEntity<BannerCarouselImagenDTO> findById(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(bannerCarouselService.findById(id));//200
	}
	
	@PostMapping("/banner-carousel")
	public ResponseEntity<BannerCarouselImagenDTO> create(@RequestBody BannerCarousel bannerCarousel) {
		return ResponseEntity.status(HttpStatus.CREATED).body(bannerCarouselService.save(bannerCarousel));//201
	}
	
	@PutMapping("/banner-carousel")
	public ResponseEntity<BannerCarouselImagenDTO> update(@RequestBody BannerCarousel bannerCarousel) {
		return ResponseEntity.status(HttpStatus.CREATED).body(bannerCarouselService.save(bannerCarousel));//201
	}
	
	@DeleteMapping("/banner-carousel/{id}")
	public ResponseEntity<BannerCarousel> deleteById(@PathVariable Long id) {
		return ResponseEntity.ok(bannerCarouselService.deleteById(id));
	}
	
}
