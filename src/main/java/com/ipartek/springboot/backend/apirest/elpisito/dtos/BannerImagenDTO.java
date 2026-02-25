package com.ipartek.springboot.backend.apirest.elpisito.dtos;

import java.util.List;

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
public class BannerImagenDTO {
	private Long id;
	private String titular; 
	private String claim; 
	private String link;
	private Integer activo;
	private List<ImagenDTO> imagenes; //Lo preparamos por si en un futuro un banner tiene mas de una ImagenDTO
}
