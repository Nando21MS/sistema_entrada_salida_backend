package com.centroinformacion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.centroinformacion.entity.Usuario;
import com.centroinformacion.service.UsuarioService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/url/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;  

    @GetMapping("/buscarPorId")
    public ResponseEntity<?> obtenerUsuarioPorId(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return ResponseEntity.status(401).body("Usuario no autenticado.");
        }

        int idUsuario = (int) session.getAttribute("idUsuario");
        Usuario usuario = usuarioService.buscaPorId(idUsuario);  
        if (usuario == null) {
            return ResponseEntity.status(404).body("Usuario no encontrado.");
        }
        return ResponseEntity.ok(usuario);
    }
}
