package com.ipartek.springboot.backend.apirest.elpisito.entities;

import java.util.Set;

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
@Table(name="paginas")
public class Pagina {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)//Identity es un incremental para MySQL
	@Column
	private Long id;
	
	@Column(unique = true)
	private String nombre;//calcula-hipoteca, 
	
	@Column
	private Integer activo;
	
	@PrePersist	
	public void prePersist() {
		if (activo == null) activo = 1;
	}
	
	//Hemos elegido Pagina como entidad dominante en la relacion. Es la "pagina" la que contiene los banner
	@ManyToMany
	@JoinTable(
			name = "pagina-banner",
			joinColumns = {@JoinColumn(name = "pagina_id")},
			inverseJoinColumns = {@JoinColumn(name = "banner_id")}
			)
	private Set<Banner> bannersPagina;
	
	
}

//Esta entidad trata de recoger las paginas que son susceptibles de contener banners
//De esta forma podemos hacer un mantenimiento dinamico de la BBDD (añadir, desactivar,...)
