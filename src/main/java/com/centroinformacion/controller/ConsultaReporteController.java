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
@RequestMapping("/url/verConsultaReporte")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class ConsultaReporteController {

    @Autowired
    private AccesosService accesosService;

    @GetMapping("/consultaReporteAccesos")
    @ResponseBody
    public ResponseEntity<?> consultaReporteAccesos(
        @RequestParam(name = "codigo", required = true, defaultValue = "") String codigo,
        @RequestParam(name = "fechaDesde", required = true, defaultValue = "") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaDesde,
        @RequestParam(name = "fechaHasta", required = true, defaultValue = "") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaHasta,
        @RequestParam(name = "idRol", required = false, defaultValue = "-1") int idRol
    ) {
        // Agregar prints para verificar parámetros
        System.out.println("Código: " + codigo);
        System.out.println("Fecha Desde: " + fechaDesde);
        System.out.println("Fecha Hasta: " + fechaHasta);
        System.out.println("ID Rol: " + idRol);
        
        List<Accesos> lstSalida = accesosService.listaConsultaCompleja(
            "%" + codigo + "%",
            fechaDesde,
            fechaHasta,
            idRol
        );
        
        return ResponseEntity.ok(lstSalida);
    }

}
