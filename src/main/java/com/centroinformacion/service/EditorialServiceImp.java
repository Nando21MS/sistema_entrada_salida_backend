package com.centroinformacion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.centroinformacion.entity.Editorial;
import com.centroinformacion.repository.EditorialRepository;

@Service
public class EditorialServiceImp implements EditorialService {

	@Autowired
	private EditorialRepository repository;

	@Override
	public List<Editorial> listaTodos() {
		return repository.findByOrderByRazonSocialAsc();
	}

	
}
