package com.centroinformacion.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.centroinformacion.entity.Accesos;
import com.centroinformacion.service.AccesosService;
import com.centroinformacion.util.AppSettings;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/url/acceso")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class AccesoController {

    @Autowired
    private AccesosService accesoService;

    @GetMapping("/buscarPorUsuarioYFecha")
    public ResponseEntity<?> obtenerAccesosPorUsuarioYFecha(
            HttpServletRequest request,
            @RequestParam(required = false) String fecha) {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("login") == null) {
            return ResponseEntity.status(401).body("Usuario no autenticado.");
        }

        String login = (String) session.getAttribute("login");
        List<Accesos> accesos;

        // Si no se proporciona fecha, obtener todos los accesos del usuario
        if (fecha == null || fecha.isEmpty()) {
            accesos = accesoService.listaPorCodigo(login);
        } else {
            // Filtrar por fecha
            Date fechaFiltro;
            try {
                fechaFiltro = new SimpleDateFormat("yyyy-MM-dd").parse(fecha);
                accesos = accesoService.listaPorCodigoYFecha(login, fechaFiltro);
            } catch (ParseException e) {
                return ResponseEntity.badRequest().body("Formato de fecha inválido.");
            }
        }

        if (accesos.isEmpty()) {
            return ResponseEntity.status(404).body("No se encontraron accesos para el usuario especificado.");
        }

        return ResponseEntity.ok(accesos);
    }
}

