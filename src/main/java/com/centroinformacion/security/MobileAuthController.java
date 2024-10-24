package com.centroinformacion.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import com.centroinformacion.util.AppSettings;
import com.centroinformacion.security.JwtProvider;
import com.centroinformacion.security.LoginUsuario;
import com.centroinformacion.security.UsuarioPrincipal;

@RestController
@RequestMapping("/url/mobile/auth")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class MobileAuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtProvider jwtProvider;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginUsuario loginUsuario) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginUsuario.getLogin(), loginUsuario.getPassword())
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtProvider.generateToken(authentication);
            
            UsuarioPrincipal usuario = (UsuarioPrincipal) authentication.getPrincipal();

            // Respuesta simplificada para la app móvil
            Map<String, Object> response = new HashMap<>();
            response.put("token", jwt);
            response.put("login", usuario.getUsername());
            response.put("nombreCompleto", usuario.getNombreCompleto());
            response.put("idUsuario", usuario.getIdUsuario());

            return ResponseEntity.ok(response);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error en la autenticación.");
        }
    }
}
