package com.centroinformacion.service;

import java.util.List;

import com.centroinformacion.entity.Externo;

public interface PersonaExternaService {

	//Registro de los externos
	public abstract Externo registroExterno(Externo obj);
	
	//Validacion de num_doc
	
	public abstract List<Externo> listaNumDoc(String num_doc);
}