package com.centroinformacion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.centroinformacion.entity.Autor;
import com.centroinformacion.repository.AutorRepository;

@Service
public class AutorServiceImp implements AutorService {

	@Autowired	
	private AutorRepository repository;

	@Override
	public Autor insertaActualizaAutor(Autor obj) {
		return repository.save(obj);
	}
	
	@Override
	public List<Autor> listaAutor() {
		return repository.findAll();
	}

	@Override
	public List<Autor> listaAutorPorNombresLike(String nombres) {
		return repository.listaPorNombresLike(nombres);
	}

	@Override
	public void eliminaAutor(int idAutor) {
		repository.deleteById(idAutor);
		
	}

	@Override
	public List<Autor> listaAutorPorNombresIgualRegistra(String nombres) {
		return repository.listaPorNombresIgualRegistra(nombres);
	}

	@Override
	public List<Autor> listaAutorPorNombresIgualActualiza(String nombres, int idAutor) {
		return repository.listaPorNombresIgualActualiza(nombres, idAutor);
	}
	
}
