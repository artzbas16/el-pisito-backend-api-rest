package com.ipartek.springboot.backend.apirest.elpisito.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipartek.springboot.backend.apirest.elpisito.entities.Banner;
import com.ipartek.springboot.backend.apirest.elpisito.repositories.BannerRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class BannerServiceImpl implements GeneralService<Banner>{
	
	@Autowired
	private BannerRepository bannerRepository;

	@Override
	public List<Banner> findAll() {
		return bannerRepository.findAll();
	}

	@Override
	public List<Banner> findAllActive() {
		return bannerRepository.findByActivo(1);
	}

	@Override
	public Banner save(Banner t) {
		return bannerRepository.save(t);
	}

	@Override
	public Banner findById(Long id) {
		return bannerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("El banner con id " + id + " no existe"));
	}

	@Override
	public Banner deleteById(Long id) {
		Banner banner = bannerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("El banner con id " + id + " no existe"));
		bannerRepository.deleteById(id);
		return banner;
	}

}
