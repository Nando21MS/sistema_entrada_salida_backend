package com.centroinformacion.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.centroinformacion.entity.Rol;
import com.centroinformacion.repository.RolRepository;
import com.centroinformacion.service.RolService;

@Service
public class RolServiceImpl implements RolService{
	@Autowired
	private RolRepository rolRepository;
	
	@Override
	public List<Rol> listaRol(){
		return rolRepository.findAll();
	}
}
