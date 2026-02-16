package com.ipartek.springboot.backend.apirest.elpisito.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ipartek.springboot.backend.apirest.elpisito.entities.Banner;

@Repository
public interface BannerRepository extends JpaRepository<Banner, Long>{
	List<Banner> findByActivo(Integer activo);
}
