package com.centroinformacion.security;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.centroinformacion.entity.Usuario;
import com.centroinformacion.service.UsuarioService;
import com.centroinformacion.util.AppSettings;

@RestController
@RequestMapping("/url/mobile")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class MobileAuthController {

    @Autowired
    private UsuarioService usuarioService;  // Inyección de dependencia

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginUsuario request, HttpServletRequest httpRequest, HttpServletResponse response) {
        // Autenticación del usuario
        Usuario usuario = usuarioService.authenticate(request.getLogin(), request.getPassword());
        if (usuario == null) {
            System.out.println("Usuario no encontrado o contraseña incorrecta.");
            return ResponseEntity.status(401).body("Credenciales inválidas.");
        }

        // Crear sesión
        HttpSession session = httpRequest.getSession();
        session.setAttribute("idUsuario", usuario.getIdUsuario());
        session.setAttribute("nombres", usuario.getNombres());
        session.setAttribute("apellidos", usuario.getApellidos());
        session.setAttribute("dni", usuario.getDni());

        // Devolver el idUsuario como parte de la respuesta
        return ResponseEntity.ok(Map.of(
            "idUsuario", usuario.getIdUsuario(),
            "nombres", usuario.getNombres(),
            "apellidos", usuario.getApellidos()
        ));
    }
}
