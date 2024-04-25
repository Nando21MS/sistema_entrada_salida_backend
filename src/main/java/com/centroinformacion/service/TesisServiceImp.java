package com.centroinformacion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.centroinformacion.entity.Tesis;
import com.centroinformacion.repository.TesisRepository;

public class TesisServiceImp implements TesisService{
	
	@Autowired	
	private TesisRepository repository;
	
	@Override
	public Tesis insertaActualizaTesis(Tesis obj) {
		return repository.save(obj);
	}
	
	@Override
	public List<Tesis> listaTesis() {
		return repository.findAll();
	}
	
}
