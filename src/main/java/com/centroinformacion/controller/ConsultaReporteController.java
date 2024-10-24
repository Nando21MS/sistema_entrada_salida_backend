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

import com.centroinformacion.entity.Accesos;
import com.centroinformacion.service.AccesosService;
import com.centroinformacion.util.AppSettings;

@RestController
@RequestMapping("/url/consultaReporte")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class ConsultaReporteController {
	
	@Autowired
	private AccesosService accesosService;
	
	@GetMapping("/consultaReporteAccesos")
	@ResponseBody
	public ResponseEntity<?> consultaReporteAccesos(
	        @RequestParam(name = "codigo", required = true) int codigo,
	        @RequestParam(name = "fecha", required = true) @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecha,
	        @RequestParam(name = "estado", required = true) int estado,
	        @RequestParam(name = "idRol", required = true, defaultValue = "-1") int idRol) {
	    // Puedes modificar los parámetros aquí si necesitas agregar '%' para LIKE
	    List<Accesos> lstSalida = accesosService.listaCompleja(codigo, fecha, estado, idRol);
	    return ResponseEntity.ok(lstSalida);
	}

}
