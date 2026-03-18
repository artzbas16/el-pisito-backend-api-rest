package com.ipartek.springboot.backend.apirest.elpisito.dtos;

import java.util.Set;

import com.ipartek.springboot.backend.apirest.elpisito.entities.BannerCarousel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TematicaDTO {
	private Long id;
	private String nombre;
	private Integer actual;
	private Integer activo;
	private Set<BannerCarousel> bannersCarousel;
	private Integer numeroBanners;
}
