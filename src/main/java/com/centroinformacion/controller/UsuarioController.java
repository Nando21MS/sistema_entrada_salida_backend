package com.centroinformacion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.centroinformacion.entity.Usuario;
import com.centroinformacion.service.UsuarioService;
import com.centroinformacion.util.AppSettings;

@RestController
@RequestMapping("/url/usuario")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class UsuarioController {
    
    @Autowired
    private UsuarioService usuarioService;
    
    @GetMapping("/buscarPorId/{idUsuario}")
    public ResponseEntity<?> obtenerUsuarioPorId(@PathVariable int idUsuario) {
        try {
            Usuario usuario = usuarioService.buscaPorId(idUsuario);
            if (usuario == null) {
                return ResponseEntity.status(404).body("Usuario no encontrado.");
            }
            return ResponseEntity.ok(usuario);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error al buscar el usuario.");
        }
    }
}
