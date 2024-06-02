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

import com.centroinformacion.entity.Alumno;
import com.centroinformacion.service.AlumnoService;
import com.centroinformacion.util.AppSettings;

@RestController
@RequestMapping("/url/consultaAlumno")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class AlumnoConsultaController {

	@Autowired
	private AlumnoService alumnoService;
	
	@GetMapping("/consultaAlumnoPorParametros")
	@ResponseBody
	public ResponseEntity<?> consultaAlumnoPorParametros(
			@RequestParam(name = "nombres", required = true, defaultValue = "") String nombres,
	        @RequestParam(name = "apellidos", required = true, defaultValue = "") String apellidos,
	        @RequestParam(name = "telefono", required = true, defaultValue = "") String telefono,
	        @RequestParam(name = "celular", required = true, defaultValue = "") String celular,
	        @RequestParam(name = "dni", required = true, defaultValue = "") String dni,
	        @RequestParam(name = "correo", required = true, defaultValue = "") String correo,
	        @RequestParam(name = "tipoSangre", required = true, defaultValue = "") String tipoSangre,
	        @RequestParam(name = "fechaNacimientoDesde", required = true, defaultValue = "") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaNacimientoDesde,
	        @RequestParam(name = "fechaNacimientoHasta", required = true, defaultValue = "") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaNacimientoHasta,
	        @RequestParam(name = "estado", required = true, defaultValue = "") int estado,
	        @RequestParam(name = "idPais", required = false, defaultValue = "-1") int idPais,
	        @RequestParam(name = "idModalidad", required = false, defaultValue = "-1") int idModalidad
			){
		
		List<Alumno> lstSalida = alumnoService.listaConsultaCompleja(
				"%" + nombres + "%",
				"%" + apellidos+ "%",
				"%" + telefono+ "%",
				"%" + celular+ "%",
				"%" + dni+ "%",
				"%" + correo+ "%",
				"%" + tipoSangre+ "%",
				fechaNacimientoDesde,
				fechaNacimientoHasta,
				estado,
				idPais,
				idModalidad);
		
		return ResponseEntity.ok(lstSalida);
	}
	
	
}
