package com.ipartek.springboot.backend.apirest.elpisito.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipartek.springboot.backend.apirest.elpisito.dtos.ImagenDTO;
import com.ipartek.springboot.backend.apirest.elpisito.dtos.InmuebleImagenDTO;
import com.ipartek.springboot.backend.apirest.elpisito.entities.Inmueble;
import com.ipartek.springboot.backend.apirest.elpisito.enumerators.EntidadImagen;
import com.ipartek.springboot.backend.apirest.elpisito.mappers.InmuebleMapper;
import com.ipartek.springboot.backend.apirest.elpisito.repositories.InmuebleRepository;

@Service
public class InmuebleServiceImpl implements GeneralService<Inmueble>{
	
	@Autowired
	private InmuebleRepository inmuebleRepository;
	
	@Autowired
	private ImagenServiceImpl imagenService;
	
	@Autowired
	private InmuebleMapper inmuebleMapper;
	
	public List<InmuebleImagenDTO> findAllBulk(){
		//Conseguimos todos los inmuebles (findAll)
		List<Inmueble> inmuebles = inmuebleRepository.findAll();//1) Conseguimos todos los muebles (findAll)	
		List<Long> ids = inmuebles.stream() //2)Extrae los ids de todos los inmuebles			
			.map(Inmueble::getId)
			.toList();											
		
		Map<Long, List<ImagenDTO>> mapaImagenes = imagenService.getImagenesPorENtidadBulk(EntidadImagen.INMUEBLE, ids); //3)
		//4) Tenemos un Map con unos ids asociados a su list de imagenes (en este caso todos los INMUEBLES)
		
		//5) Pasamos la lista de inmuebles y el mapa de imagenes a inmuebleMapper.toDtoBulk en InmuebleMapper
		return inmuebleMapper.toDtoBulk(inmuebles, mapaImagenes);//toDtoBulk() combina los dtos con las imagenes 
		//y devuelve la lista final de inmuebles con sus imagenes
	}

	@Override
	public List<Inmueble> findAll() {
		return inmuebleRepository.findAll();
	}

	@Override
	public List<Inmueble> findAllActive() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Inmueble save(Inmueble t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Inmueble findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Inmueble deleteById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
