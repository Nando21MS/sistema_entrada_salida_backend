package com.centroinformacion.entity;

import java.util.Date;

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
@Table(name = "persona_externa")
public class Externo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idPersonaExterna;
	private String nombres;
	private String apellidos;
	private String num_doc;
	private String celular;
	private String correo;
	
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
	private Date fechaRegistro;
	
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idTipoDoc")
	private TipoDocumento tipoDocumento;
	
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idUsuario")
	private Usuario usuarioRegistro;
	
	private int estado;
	private String motivo;	
	private String foto;
	
	
	
	public String getReporteEstado() {
		return estado == 1 ? "Ingreso" : "No Ingreso";
	}
	public String getTipoDocumento() {
		return tipoDocumento.getDescripcion();
	}
}
