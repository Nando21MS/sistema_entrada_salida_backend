package com.centroinformacion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.centroinformacion.security.LoginUsuario;
import com.centroinformacion.util.AppSettings;

import lombok.extern.apachecommons.CommonsLog;

@CommonsLog
@RestController
@RequestMapping("/url/mobile")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class UsuarioController {

	 @Autowired
	    private AuthenticationManager authenticationManager;

	    @PostMapping("/login")
	    public ResponseEntity<String> login(@RequestBody LoginUsuario loginUsuario) {
	        log.info(">>> login estudiantes >>> " + loginUsuario.getLogin());
	        log.info(">>> login estudiantes >>> " + loginUsuario.getPassword());

	        try {
	            // Intenta autenticar al usuario
	            Authentication authentication = authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(loginUsuario.getLogin(), loginUsuario.getPassword())
	            );
	            
	            // Si la autenticación es exitosa
	            SecurityContextHolder.getContext().setAuthentication(authentication);
	            log.info(">>> Autenticación exitosa para el usuario: " + loginUsuario.getLogin());

	            return new ResponseEntity<>("Inicio de sesión exitoso", HttpStatus.OK);
	        } catch (Exception e) {
	            log.error(">>> Error en el inicio de sesión: " + e.getMessage());
	            return new ResponseEntity<>("Login o contraseña incorrectos", HttpStatus.UNAUTHORIZED);
	        }
	    }
	}
