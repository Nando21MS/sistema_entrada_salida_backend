package com.centroinformacion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.centroinformacion.entity.Alumno;
import com.centroinformacion.repository.AlumnoRepository;

@Service // Indica que esta clase es un servicio de Spring, gestionado por el contenedor
			// de Spring.
public class AlumnoServiceImp implements AlumnoService {

	@Autowired
	private AlumnoRepository repository;

	@Override
	public List<Alumno> listaTodos() {
		return repository.findByOrderByApellidosAsc();
	}

	@Override
	public Alumno registraAlumno(Alumno obj) {
		return repository.save(obj);
	}

	@Override
	public Alumno insertaActualizaAlumno(Alumno obj) {
		return repository.save(obj);
	}

	@Override
	public List<Alumno> listaAlumnoPorTituloLike(String titulo) {
		return repository.listaAlumnoPorTituloLike(titulo);
	}

	@Override
	public void eliminaAlumno(int idAlumno) {
		repository.deleteById(idAlumno);
	}

	@Override
	public List<Alumno> listaAlumnoTodos() {
		return repository.findAll();
	}

	@Override
	public List<Alumno> listaAlumnoPorNombreIgualRegistro(String titulo) {
		return repository.listaAlumnoPorTituloLike(titulo);
	}

	@Override
	public List<Alumno> listaAlumnoPorTituloIgualActualiza(String titulo, int idAlumno) {
		return repository.listaPorTituloIgualActualiza(titulo, idAlumno);
	}

	@Override
	public List<Alumno> validacionListaPorTitutloIgualRegistra(String titulo) {
		return repository.validacionListaPorNombreIgualRegistra(titulo);
	}
}