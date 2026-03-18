package com.ipartek.springboot.backend.apirest.elpisito.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ipartek.springboot.backend.apirest.elpisito.dtos.TematicaDTO;
import com.ipartek.springboot.backend.apirest.elpisito.entities.Tematica;
import com.ipartek.springboot.backend.apirest.elpisito.mappers.TematicaMapper;
import com.ipartek.springboot.backend.apirest.elpisito.repositories.TematicaRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TematicaServiceImpl{
	
	@Autowired
	private TematicaRepository tematicaRepository;
	
	@Autowired
	private TematicaMapper tematicaMapper;

	public List<TematicaDTO> findAll() {
		List<Tematica> tematicas = tematicaRepository.findAll();
		List<TematicaDTO> dtos = tematicaMapper.toDtoList(tematicas);

		/*List<Long> tematicaIds = dtos.stream()
				.map(TematicaDTO::getId)
				.toList();*/
			
		for(TematicaDTO dto: dtos) {
			dto.setNumeroBanners(dto.getBannersCarousel().size());
		}
		
		return dtos;
	}

	public List<Tematica> findAllActive() {
		return tematicaRepository.findByActivo(1);
	}

	public Tematica save(Tematica t) {
		return tematicaRepository.save(t);
	}

	public Tematica findById(Long id) {
		return tematicaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("La temática con id " + id + " no existe"));
	}

	public Tematica deleteById(Long id) {
		Tematica tematica =tematicaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("La temática con id " + id + " no existe"));
		tematicaRepository.deleteById(id);
		return tematica;
	}
	
	//¿Cuando utilizamos @Transactional?
	//Cuando queramos que un metodo utilice una "transaccion" (en nuestro caso desactualizarTodas())
	//Una transaccion implica multiples cambios en una BBDD (puede ser en una o varias tablas)
	//Un proceso transaccional garantiza las propiedades ACID
	//-Atomicidad
	//-Consistencia
	//-Isolation (Aislamiento)
	//-Durabilidad
	//Si un metodo realiza varias acciones y todas han de completarse con exito debemos utilizar @Transactional
	//Si una de estas acciones falla se produce un "ROLLBACK" y las acciones anteriores ya ejecutadas se revierten
	//Por defecto, en Spring, un rollback solo se produce cuando se hace un RunTimeException (unchecked)
	//NO se hace rollback en las excepciones IOException (checked). Esto se puede cambiar, es decir, que el proceso 
	//de rollback se produzca en ambos casos. Para conseguirlo debemos anotar 
	//@Transactional(rollbackFor = Exception.class)
	//Cuando no utilizar @Transactional:
	//1) en metodos que solo leen datos
	//2) en metodos que solo ejecuten una sola operacion
	//3) en la mayoria de los metodos de Jpa hibernate que son transactionales por defecto
	
	//RESUMEN: "si algo falla quiero que todo se deshaga"
	@Transactional(rollbackFor = Exception.class)
	public Tematica actualizarTematica(Long id) {
		Tematica tematica = tematicaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("La temática con id " + id + " no existe, por lo tanto no podemos ponerla como temática activa"));
		
		//Ponemos todas desactualizadas
		tematicaRepository.desactualizarTodas(id);
		
		//tematica.setActual(1);
		return tematica;
	}
	
	public Tematica findActual() {
		return tematicaRepository.findByActual(1).getFirst();
	}
	
	
}
