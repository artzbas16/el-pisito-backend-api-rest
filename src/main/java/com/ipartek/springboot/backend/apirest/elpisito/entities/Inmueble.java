package com.ipartek.springboot.backend.apirest.elpisito.entities;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
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
@Entity 
@Table(name="inmuebles")
public class Inmueble {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)//Identity es un incremental para MySQL
	@Column
	private Long id;
	
	@Column
	private String via; //CALLE, PLAZA, CARRETERA, AVENIDA
	
	@Column
	private String claim; //Maravilloso piso!!
	
	@Column(name = "nombre_via") 
	private String nombreVia;
	
	@Column
	private String numero;
	
	@Column 
	private String planta; //SIN PLANTA, PLANTA BAJA, PRIMERO, SEGUNDO,...
	
	@Column
	private String puerta; //A, B, C,...
	
	@Column
	private String apertura; //INTERIOR, EXTERIOR, SEMI-EXTERIOR
	
	@Column
	private String orientacion; //NORTE, SUR,...
	
	@Column(name = "superficie_util")
	private Double superficieUtil;
	
	@Column(name = "superficie_construida")
	private Double superficieConstruida;
	
	@Column
	private Double precio;
	
	@Column
	private Integer habitaciones;
	
	@Column
	private Integer banhos;
	
	@Column(length = 3500)
	private String descripcion; //Una descripcion amplia del inmueble
	
	@Column
	private String calefaccion;//"ELECTRICA, CENTRAL, GAS NATURAL,...
	
	@Column
	private Integer amueblado; //1 o 0
	
	@Column
	private Integer balcones;
	
	@Column
	private Integer garages;
	
	@Column
	private Integer piscina; //1 o 0
	
	@Column
	private Integer trastero; //1 o 0
	
	@Column
	private Integer ascensor; //1 o 0
	
	@Column
	private Integer jardin; //1 o 0
	
	@Column
	private Integer tendedero;//1 o 0
	
	@Column
	private Integer portada; //1 o 0
	
	@Column
	private Integer oportunidad; //1 o 0
	
	@ManyToOne
	@JoinColumn(name = "tipo")
	private Tipo tipo; //este es el mappedBy del @OneToMany de la clase Tipo
	
	@ManyToOne
	@JoinColumn(name = "operacion")
	private Operacion operacion; //este es el mappedBy del @OneToMany de la clase Operacion
	
	@ManyToOne
	@JoinColumn(name = "poblacion")
	private Poblacion poblacion; //este es el mappedBy del @OneToMany de la clase Poblacion
	
	@ManyToOne
	@JoinColumn(name = "inmobiliaria")
	private Inmobiliaria inmobiliaria; //este es el mappedBy del @OneToMany de la clase Inmobiliaria
	
	//Inversa de la relacion con Usuario
	@ManyToMany(mappedBy = "inmueblesFavoritos")
	private Set<Usuario> usuariosQueLoFavoritean;
	
	@Column
	private Integer activo;
	
	@PrePersist	
	public void prePersist() {
		if (activo == null) activo = 1;
		if (portada == null) portada = 0; 
		if (oportunidad == null) oportunidad = 0;
	}

}
