package com.centroinformacion.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.centroinformacion.entity.Alumno;
import com.centroinformacion.service.AlumnoService;
import com.centroinformacion.util.AppSettings;
import com.centroinformacion.util.FunctionUtil;

@RestController
@RequestMapping("/url/crudAlumno")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class AlumnoCrudController {
	
	@Autowired
	private AlumnoService alumnoService;
	
	@GetMapping("/listaAlumnoPorNombreLike/{var}")
    @ResponseBody
    public ResponseEntity<?> lista(@PathVariable("var") String filtro) {
        List<Alumno> lstAlumno;
        if ("todos".equals(filtro)) {
            lstAlumno = alumnoService.listaTodos();
        } else {
            lstAlumno = alumnoService.listaAlumnoPorNombreLike("%" + filtro + "%");
        }
        return ResponseEntity.ok(lstAlumno);
    }
	//REGISTRA ALUMNO
	@PostMapping("/registraAlumno")
	@ResponseBody
	public ResponseEntity<?> insertaAlumno(@RequestBody Alumno obj) {
		Map<String, Object> salida = new HashMap<>();
		try {
			obj.setIdAlumno(0);
			obj.setFechaActualizacion(new Date());
			obj.setFechaRegistro(new Date());
			obj.setEstado(AppSettings.ACTIVO);
			
			List<Alumno> lstBusqueda = alumnoService.listaAlumnoPorNombreIgualRegistra(obj.getNombres());
			if(!lstBusqueda.isEmpty()) {
				salida.put("mensaje", "La Revista " + obj.getNombres() + " ya existe");
				return ResponseEntity.ok(salida);
			}
			
			Alumno objSalida =  alumnoService.insertaActualizaAlumno(obj);
			if (objSalida == null) {
				salida.put("mensaje", AppSettings.MENSAJE_REG_ERROR);
			} else {
				salida.put("mensaje", AppSettings.MENSAJE_REG_EXITOSO);
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", AppSettings.MENSAJE_REG_ERROR);
		}
		return ResponseEntity.ok(salida);
	}
	
	
	
	@GetMapping("/buscaAlumnoEdadEstudiantil")
	@ResponseBody
	public String validaFecha(String fechaNacimiento) {
		if(FunctionUtil.isEdadEstudiantil(fechaNacimiento)) {
			return "{\"valid\":true}";
		}else {
			return "{\"valid\":false}";
		}
	}
	
	///
		@PutMapping("/actualizaAlumno")
		@ResponseBody
		public ResponseEntity<Map<String, Object>> actualizaAlumno(@RequestBody Alumno obj) {
			Map<String, Object> salida = new HashMap<>();
			try {
				
				obj.setFechaActualizacion(new Date());
				
				List<Alumno> lstBusqueda = alumnoService.listaAlumnoPorNombreIgualActualiza(obj.getNombres(), obj.getIdAlumno());
				if(!lstBusqueda.isEmpty()) {
					salida.put("mensaje", "El alumno " + obj.getNombres() + " ya existe");
					return ResponseEntity.ok(salida);
				}
				
				Alumno objSalida =  alumnoService.insertaActualizaAlumno(obj);
				if (objSalida == null) {
					salida.put("mensaje", AppSettings.MENSAJE_ACT_ERROR);
				} else {
					salida.put("mensaje", AppSettings.MENSAJE_ACT_EXITOSO);
				}
			} catch (Exception e) {
				e.printStackTrace();
				salida.put("mensaje", AppSettings.MENSAJE_ACT_ERROR);
			}
			return ResponseEntity.ok(salida);
		}
		
		@DeleteMapping("/eliminaAlumno/{id}")
		@ResponseBody
		public ResponseEntity<Map<String, Object>> eliminaRevista(@PathVariable("id") int idAlumno) {
			Map<String, Object> salida = new HashMap<>();
			try {
				alumnoService.eliminaAlumno(idAlumno);
				salida.put("mensaje", AppSettings.MENSAJE_ELI_EXITOSO);
			} catch (Exception e) {
				e.printStackTrace();
				salida.put("mensaje", AppSettings.MENSAJE_ELI_ERROR);
			}
			return ResponseEntity.ok(salida);
		}
	
}
