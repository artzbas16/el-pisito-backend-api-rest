package com.ipartek.springboot.backend.apirest.elpisito.services;


import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ipartek.springboot.backend.apirest.elpisito.dtos.ImagenDTO;
import com.ipartek.springboot.backend.apirest.elpisito.entities.Imagen;
import com.ipartek.springboot.backend.apirest.elpisito.enumerators.EntidadImagen;
import com.ipartek.springboot.backend.apirest.elpisito.exceptions.FileStorageException;
import com.ipartek.springboot.backend.apirest.elpisito.exceptions.ResourceNotFoundException;
import com.ipartek.springboot.backend.apirest.elpisito.mappers.ImagenMapper;
import com.ipartek.springboot.backend.apirest.elpisito.repositories.ImagenRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ImagenServiceImpl{
	
	@Autowired
	private ImagenRepository imagenRepository;
	
	@Autowired
	private ImagenMapper imagenMapper;
	
	//COn esta propiedad estamos indicando que la ruta definida en "media.location"
	//que esta en el archivo "aplication-properties" se refiere a la ruta de la 
	//carpeta en la que queremos albergar nuestros archivos fisicos.
	//En nuestro caso "C:/mediafiles"
	//@Value: sirve para dar un valor determinado a un atributo
	@Value("${media.location}")
	private String mediaPath;
	
	private final Tika tika = new Tika();
	
	//SUBIR IMAGEN IMAGEN FISICA Y GUARDAR REGISTRO
	
	public ImagenDTO store(MultipartFile file, EntidadImagen entidadImagen, Long entidadId, String alt) {
		//https://developer.mozilla.org/es/docs/Web/HTTP/Guides/MIME_types/Common_types
		//El tipo MIME no dice inequivocamente que tipo de archivo es
		//Es comprobacion NO DEBEMOS hacerla con la extension del archivo ".jpg" etc...
		//image/gif, image/png, image/jpeg, image/bmp, image/webp
		
		//Lo primero que vamos a hacer es comprobar si el file esta vacio
		if (file.isEmpty()) {
			throw new FileStorageException("Error al subir el archivo. No es posible almacenar un archivo vacío");
		}
		
		//Detectamos el tipo MIME real con TIKA
		String mimeType = null;
		try {
			mimeType = tika.detect(file.getInputStream());
		} catch (IOException e) {
			throw new FileStorageException("Error al subir el archivo. No ha sido posible analizar el archivo.");
		}
		
		//Mapear extensiones que vamos a permitir (jpg, png, webp)
		
		String extension = switch (mimeType) {
		case "image/jpeg" -> "jpg";
		case "image/png" -> "png";
		case "image/webp" -> "webp";
		case "image/svg" -> "svg";
		default -> throw new FileStorageException("Error al subir el archivo. Formato " + mimeType + " no permitido.");
		};
		
		//Generar nombre unico
		
		String nombreArchivo = UUID.randomUUID() + "." + extension;
		
		//Crear carpetas si no existen	
		Path directorio = null;
		try {
			directorio = Paths.get(mediaPath, entidadImagen.name().toLowerCase(), entidadId.toString());
			
			Files.createDirectories(directorio);//El metodo crea los directorios SI NO EXISTEN
			
			Path ruta = directorio.resolve(nombreArchivo);//resolve añade el nombre del archivo al Path directorio
			Files.copy(file.getInputStream(), ruta);
			
		} catch (IOException e) {
			throw new FileStorageException("Error al subir el archivo");
		}
		
		//Ahora guardamos la imagen en la BBDD
		Imagen img = new Imagen();
		img.setNombre(nombreArchivo);
		img.setEntidadImagen(entidadImagen);
		img.setEntidadId(entidadId);
		img.setAltImagen(alt);
		
		Imagen imagen = imagenRepository.save(img);
		
		return imagenMapper.toDto(imagen);
		
	}
	
	//RENDERIZAR IMAGEN
	public ResponseEntity<Resource> renderizarImagen(EntidadImagen entidadImagen, Long entidadId, String nombre) {
		
		try {		
			Path path = Paths.get(mediaPath, entidadImagen.name().toLowerCase(), entidadId.toString(), nombre);
			
			//SI la imagen no existe fisicamente no puedo devolverla
			if (!Files.exists(path)) {
				throw new ResourceNotFoundException("Archivo no encontrado");
			}
			
			//En este punto estamos seguros de que la imagen existe...
			
			Resource resource = new UrlResource(path.toUri());
			
			String mimeType = Files.probeContentType(path);
			
			if (mimeType==null) {
				mimeType = "application/octet-stream";
			}
			
			return ResponseEntity.ok().contentType(MediaType.parseMediaType(mimeType)).body(resource);
			
		} catch (MalformedURLException e) {
			throw new FileStorageException("Ruta invalida");
		} catch (IOException e) {
			throw new FileStorageException("No se pudo leer el archivo");
		}
	}
	
	//ELIMINAR IMAGEN (FISICA Y BBDD)
	public ImagenDTO deleteImagenFisicaYBBDD(Long id) {
		
		//Obtenemos el objeto IMagen de la BBDD
		Imagen imagen = imagenRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("La imagen con id " + id + " no existe"));
		
		//Eliminamos imagen en la BBDD
		ImagenDTO imagenDTO = deleteById(id);
		
		//ELiminamos imagen fisica
		//Creamos un objeto Path dotandole al metodo get de todos los "trocitos" que forman el Path
		//C:/mediafiles/banner/34/12431413132312.jpg
		Path path = Paths.get(mediaPath, imagen.getEntidadImagen().name().toLowerCase(), imagen.getEntidadId().toString(), imagen.getNombre());
		
		try {
			Files.deleteIfExists(path); //Genera IOException. En este momento borramos imagen fisica
		} catch (IOException e) {
			throw new FileStorageException("No se ha podido borrar la imagen física. Sin enbargo, la imagen de la BBDD ha sido eliminada");
		} //Comprueba si la imagen fisica existe en el sistema
		
		
		return imagenDTO;
		
	}
	
	
	//OBTENER IMAGENES POR ENTIDAD (BULK)
	//Este metodo es universal para todas las entidades que tienen imagen (representadas
	//en el enumerador EntidadImagen)
	//Con una sola llamada a la BBDD vamos a obtener un Map con el id de la entidad
	//(Inmueble, Banner,...cualquier entidad que tenga imagen o imagenes)
	//y su correspondiente listado de imagenes
	//Entrada: entidad --> por ejemplo EntidadImagen.INMUEBLE
	//ids --> lista de ids, de inmueble, banner, banner carousel, inmobiliaria,...
	//Salida: un Map donde cada id de EntidadImagen tiene su propia lista de ImagenDTO
	//Objetivo: Sacar todas las imagenes de todos los ids en una sola query y evitar
	//(n+1) queries ---> EFICIENCIA
	//Se invoca desde los servicios de las entidades (entidades que tienen imagen se entiende)
	//por ejemplo desde InmuebleServiceImpl 
	
	public Map<Long, List<ImagenDTO>> getImagenesPorEntidadBulk(EntidadImagen entidad, List<Long> ids){
		//Conseguimos todas las imagenes de una entidad
		List<Imagen> imagenes = imagenRepository.findByEntidadImagenAndEntidadIdIn(entidad, ids);
		
		//Conseguimos las imagenesDTO de la entidad
		List<ImagenDTO> imagenesDTO = imagenMapper.tDtoList(imagenes);
		
		return imagenesDTO.stream()
			.collect(Collectors.groupingBy(ImagenDTO::entidadId)); //ImagenDTO es un record, por lo tanto, no es getEntidadId sino entidadId

	}
	
	//OBTENER IMAGENES
	public List<ImagenDTO> getImagenes(EntidadImagen tipoEntidad, Long entidadId) {
		return imagenRepository.findByEntidadImagenAndEntidadId(tipoEntidad, entidadId).stream()
			.map(i -> imagenMapper.toDto(i))
			.toList();
	}
	
	/*
	//OBTENER UNA UNICA IMAGEN (LA PRIMERA)
	public ImagenDTO getUnicaImagen(EntidadImagen tipoEntidad, Long entidadId) {
		Imagen imagen = imagenRepository.findFirstByEntidadImagenAndEntidadId(tipoEntidad, entidadId).orElseThrow(() -> new EntityNotFoundException("La imagen con id " + entidadId + " perteneciente al tipo de entidad " + tipoEntidad.name().toLowerCase() +" no existe"));
		return imagenMapper.toDto(imagen);
	}
	*/
	
	/*
	//CONVERTIR IMAGEN EN IMAGENDTO
	private ImagenDTO toDTO(Imagen imagen) {
		String url = "/api/imagenes/" + imagen.getEntidadImagen().name().toLowerCase()+"/"+imagen.getEntidadId()+"/"+imagen.getNombre();
		return new ImagenDTO(imagen.getId(), url, imagen.getAltImagen(), imagen.getEntidadId());
	}
	*/

	public ImagenDTO save(Imagen t) {
		return imagenMapper.toDto(imagenRepository.save(t));
	}

	public ImagenDTO findById(Long id) {
		Imagen imagen = imagenRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("La imagen con id " + id + " no existe"));
		return imagenMapper.toDto(imagen);
	}

	public ImagenDTO deleteById(Long id) {
		Imagen imagen = imagenRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("La imagen con id " + id + " no existe"));
		imagenRepository.deleteById(id);
		return imagenMapper.toDto(imagen);
	}

}
