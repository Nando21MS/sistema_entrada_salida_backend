package com.centroinformacion.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.centroinformacion.entity.Externo;
import com.centroinformacion.service.PersonaExternaService;
import com.centroinformacion.util.AppSettings;

@RestController
@RequestMapping("/url/personaExterna")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class PersonaExternaRegistroController {
	
	@Autowired
	private PersonaExternaService pExternaService;
	
	@PostMapping("/registraPersonaExterna")
	@ResponseBody
	public  ResponseEntity<?> insertaPersonaExterna(@RequestBody Externo obj){
		Map<String, Object> salida = new HashMap<>();
		try {
			obj.setFechaRegistro(new Date());
			obj.setEstado(AppSettings.INGRESÓ);
			
			List<Externo>ltsBusquedaDni = pExternaService.listaNumDoc(obj.getNum_doc());
			if(!ltsBusquedaDni.isEmpty()) {
				salida.put("mensaje", "El número "+ obj.getNum_doc()+" de documento ya existe");
				return ResponseEntity.ok(salida);
			}
			
			Externo objSalida = pExternaService.registroExterno(obj);
			if(objSalida == null) {
				salida.put("mensaje",AppSettings.MENSAJE_REG_ERROR);
				
			}else {
				salida.put("mensaje",AppSettings.MENSAJE_REG_EXITOSO);
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", AppSettings.MENSAJE_REG_ERROR);
		}
		return ResponseEntity.ok(salida);
	}
}
