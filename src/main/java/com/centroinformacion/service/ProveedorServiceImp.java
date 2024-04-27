package com.centroinformacion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.centroinformacion.entity.Proveedor;
import com.centroinformacion.repository.ProveedorRepository;

@Service
public class ProveedorServiceImp implements ProveedorService {

	@Autowired
	private ProveedorRepository repository;
	@Override
	public Proveedor insertaActualizaProveedor(Proveedor obj) {
		return repository.save(obj);
	}
	@Override
	public List<Proveedor> listaTodos() {
		return repository.findByOrderByRazonsocialAsc();
	}
	
}