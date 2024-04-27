package com.centroinformacion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service; // Añade esta importación

import com.centroinformacion.entity.Libro;
import com.centroinformacion.repository.LibroRepository;

@Service // Añade esta anotación
public class LibroServiceImp implements LibroService{
	/*
	@Autowired	
	private LibroRepository repository;
	
	@Override
	public Libro insertaActualizaLibro(Libro obj) {
		return repository.save(obj);
	}
	
	@Override
	public List<Libro> listaLibro() {
		return repository.findAll();
	}

	@Override
	public boolean existeLibroConSerie(String serie) {
		Libro libro = repository.findBySerie(serie);
		if (libro != null) {
			return true;
		} else {
			return false;
		}
	}
*/
}
