package com.centroinformacion.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.centroinformacion.entity.Accesos;
import com.centroinformacion.entity.Rol;
import com.centroinformacion.repository.AccesosRepository;
import com.centroinformacion.service.AccesosService;

@Service
public class AccesosServiceImpl implements AccesosService {

	@Autowired
	private AccesosRepository repository;
	
	@Override
	public List<Accesos> listaPorCodigo(int codigo) {
		return repository.findByCodigo(codigo);
	}

	@Override
	public List<Accesos> listaPorRol(Rol rol) {
		return repository.findByRol(rol);
	}

	@Override
	public List<Accesos> listaPorFecha(Date fecha) {
		return repository.findByFecha(fecha);
	}

	@Override
	public List<Accesos> listaCompleja(int codigo, Date fecha, int estado, int idRol) {
		return repository.listaCompleja(codigo, fecha, estado, idRol);
	}

}
