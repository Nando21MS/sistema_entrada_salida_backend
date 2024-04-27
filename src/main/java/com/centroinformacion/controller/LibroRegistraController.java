package com.centroinformacion.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.centroinformacion.entity.Libro;
import com.centroinformacion.service.LibroService;
import com.centroinformacion.util.AppSettings;


@RestController
@RequestMapping("/url/libro")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
	public class LibroRegistraController {

	@Autowired
	private LibroService LibroService;
	
	@GetMapping
	@ResponseBody
	public ResponseEntity<List<Libro>> listaLibro(){
		List<Libro> lista = LibroService.listaLibro();
		return ResponseEntity.ok(lista);
	}
	
	@PostMapping
	@ResponseBody
	public ResponseEntity<?> inserta(@RequestBody Libro obj){
		HashMap<String, Object> salida = new HashMap<>();
		
		obj.setFechaActualizacion(new Date());
		obj.setFechaRegistro(new Date());
		obj.setEstado(AppSettings.ACTIVO);
		
		
		Libro objSalida = LibroService.insertaActualizaLibro(obj);
		if (objSalida == null) {
			salida.put("mensaje","Error en el registro");
		}else {
			salida.put("mensaje","Se registró el Libro con el ID ==> " + objSalida.getIdLibro());
		}
		return ResponseEntity.ok(salida);
	}
	
	@GetMapping("/buscaPorSerieLibro")
	@ResponseBody
	public Map<String, Boolean> buscaPorSerieLibro(@RequestParam("serie") String serie) {
	    Map<String, Boolean> response = new HashMap<>();

	    // Obtiene una instancia de LibroService mediante la inyección de dependencias
	    LibroService libroService = obtenerLibroService();

	    // Llama al servicio para verificar si la serie ya existe en la base de datos
	    boolean serieExiste = libroService.existeLibroConSerie(serie);

	    response.put("valid", !serieExiste);

	    return response;
	}

	private LibroService obtenerLibroService() {
	    // Obtiene una instancia de LibroService mediante la inyección de dependencias (por ejemplo, @Autowired)
	    // Asegúrate de que LibroService esté correctamente configurado y se esté inyectando en esta clase
	    return LibroService;
	}
	
	
}