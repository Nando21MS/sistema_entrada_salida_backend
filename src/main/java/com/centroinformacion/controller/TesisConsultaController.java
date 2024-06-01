package com.centroinformacion.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.centroinformacion.entity.Tesis;
import com.centroinformacion.service.TesisService;
import com.centroinformacion.util.AppSettings;

@RestController
@RequestMapping("/url/consultaTesis")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class TesisConsultaController {
	
	@Autowired
	private TesisService tesisService;
	
	@GetMapping("/consultaTesisPorParametros")
	@ResponseBody
	public ResponseEntity<?> consultaTesisPorParametros(
			@RequestParam(name = "titulo" , required = true , defaultValue = "") String nombre,
			@RequestParam(name = "fecDesde" , required = true , defaultValue = "") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecDesde,
			@RequestParam(name = "fecHasta" , required = true , defaultValue = "") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecHasta,
			@RequestParam(name = "estado" , required = true , defaultValue = "") int estado,
			@RequestParam(name = "idTema" , required = false , defaultValue = "-1") int idTema,
			@RequestParam(name = "idIdioma" , required = false , defaultValue = "-1") int idIdioma,
			@RequestParam(name = "idCentroEstudios" , required = false , defaultValue = "-1") int idCentroEstudios
			){
		List<Tesis> lstSalida = tesisService.listaConsultaCompleja(
								"%"+nombre+"%", fecDesde, fecHasta, estado, idTema, idIdioma, idCentroEstudios);
		
		return ResponseEntity.ok(lstSalida);
	}
	
}
