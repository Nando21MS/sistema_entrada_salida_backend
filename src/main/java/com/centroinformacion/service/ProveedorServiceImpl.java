package com.centroinformacion.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.centroinformacion.entity.Proveedor;
import com.centroinformacion.repository.ProveedorRepository;



@Service
public class ProveedorServiceImpl implements ProveedorService{
	@Autowired
	private ProveedorRepository repository;
	

	
	
	@Override
	public Proveedor registroProveedor(Proveedor obj) {
		
		return repository.save(obj);
	}
}
