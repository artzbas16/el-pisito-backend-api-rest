package com.ipartek.springboot.backend.apirest.elpisito.dtos;

import java.util.List;

import com.ipartek.springboot.backend.apirest.elpisito.entities.Inmobiliaria;
import com.ipartek.springboot.backend.apirest.elpisito.entities.Operacion;
import com.ipartek.springboot.backend.apirest.elpisito.entities.Poblacion;
import com.ipartek.springboot.backend.apirest.elpisito.entities.Tipo;

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
public class InmuebleImagenDTO {
	private Long id;
	private String via;
	private String nombreVia;
	private String numero;
	private String planta;
	private String puerta;
	private String apertura;
	private String orientacion;
	private Double superficieUtil;
	private Double superficieConstruida;
	private Double precio;
	private Integer habitaciones;
	private Integer banhos;
	private String descripcion;
	private String calefaccion;
	private Integer amueblado;
	private Integer balcones;
	private Integer garages;
	private Integer piscina;
	private Integer trastero;
	private Integer ascensor;
	private Integer jardin;
	private Integer tendedero;
	private Integer portada;
	private Integer oportunidad;
	private Tipo tipo;
	private Operacion operacion;
	private Poblacion poblacion;
	private Inmobiliaria inmobiliaria;
	private Integer activo;
	
	private List<ImagenDTO> imagenes;
	
	
	
}
