package com.ipartek.springboot.backend.apirest.elpisito.entities;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
@Table(name="tematicas")
public class Tematica {
	
	//La tematicaes la forma que vamos a tener de filtrar los banners carousel para que
	//en el front-end (Angular) aparezcan en la cabecera los banners carousel de una tematica
	//determinada
	//Esta clase sirve para contener los tipos de Tematica que pueden tener los BannersCarousel
	//para que podamos administrarlos dinamicamente desde el panel de administracion de Angular
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)//Identity es un incremental para MySQL
	@Column
	private Long id;
	
	@Column(unique = true)
	private String nombre; //CAMPAÑA PRIMAVERA, VENTAS DE VERANO, NAVIDAD,...
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(
			name = "tematica-bannercarousel",
			joinColumns = {@JoinColumn(name="tematica_id")},
			inverseJoinColumns = {@JoinColumn(name="banner_carousel_id")}
			)
	private Set<BannerCarousel> bannersCarousel; // (Que estan en esta Tematica)//Este es el mappedBy de BannerCarousel
	
	@Column
	private Integer activo;
	
	@PrePersist	
	public void prePersist() {
		if (activo == null) activo = 1;
	}

}
