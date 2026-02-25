package com.ipartek.springboot.backend.apirest.elpisito.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ipartek.springboot.backend.apirest.elpisito.entities.BannerCarousel;

@Repository
public interface BannerCarouselRepository extends JpaRepository<BannerCarousel, Long>{
	List<BannerCarousel> findByActivo(Integer activo);
}
