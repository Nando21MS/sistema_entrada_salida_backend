package com.centroinformacion.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.centroinformacion.entity.Externo;
import com.centroinformacion.repository.PersonaExternaRepository;

@Service
public class PersonaExternaServiceImpl implements PersonaExternaService{

	@Autowired
	private PersonaExternaRepository repository;
	
	@Override
	public Externo registroExterno(Externo obj) {
		
		return repository.save(obj);
	}
	
}
