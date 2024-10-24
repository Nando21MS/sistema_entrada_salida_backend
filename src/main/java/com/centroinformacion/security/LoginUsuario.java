package com.centroinformacion.security;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginUsuario {

	private String login;
	private String password;

	// Getters y Setters
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
