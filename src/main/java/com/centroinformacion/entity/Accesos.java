package com.centroinformacion.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.centroinformacion.util.FunctionUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "accesos")
public class Accesos {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idAcceso;
	private String codigo;
	private String nombres;
	private String apellidos;
	
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd" , timezone = "America/Lima")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fecha;
	
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idRol")
	private Rol rol;

	private int estado;
	
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario")
	private Usuario usuarioRegistro;
	
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
	private Date fecha_Registro;
	
	public String getReporteEstado() {
		return estado == 1 ? "Ingreso" : "Salida";
	}
	public String getRol() {
		return rol.getNombre();
	}
	public String getReporteFecha() {
		return FunctionUtil.getFechaString(fecha);
	}
	public String getReporteRol() {
		return rol.getNombre();
	}

}
