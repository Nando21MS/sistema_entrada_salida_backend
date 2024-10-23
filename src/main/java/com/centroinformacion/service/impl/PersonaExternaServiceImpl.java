package com.centroinformacion.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.centroinformacion.entity.Externo;
import com.centroinformacion.repository.PersonaExternaRepository;
import com.centroinformacion.service.PersonaExternaService;

@Service
public class PersonaExternaServiceImpl implements PersonaExternaService{
	@Autowired
	private PersonaExternaRepository repository;
	
	@Override
	public Externo registroExterno(Externo obj) {
		
		return repository.save(obj);
	}

	@Override
	public List<Externo> listaNumDoc(String num_doc) {
		// TODO Auto-generated method stub
		 return repository.listaPorDniIgualRegistra(num_doc);
	}

	
	
}
