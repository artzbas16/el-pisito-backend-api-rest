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
public class InmobiliariaImagenDTO {
	
	private Long id;
	private String nombre;
	private String telefono;
	private String representante;
	private Integer activo;
	private List<ImagenDTO> imagenes;
	
}
