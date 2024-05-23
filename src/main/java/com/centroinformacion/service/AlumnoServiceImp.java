package com.centroinformacion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.centroinformacion.entity.Alumno;
import com.centroinformacion.repository.AlumnoRepository;

@Service // Indica que esta clase es un servicio de Spring, gestionado por el contenedor de Spring.
public class AlumnoServiceImp implements AlumnoService {

	@Autowired // Inyección de dependencia de Spring para instanciar el repositorio.
	private AlumnoRepository repository;

	@Override
	public List<Alumno> listaTodos() {
		// Llama al método del repositorio para obtener una lista de alumnos ordenados por apellidos de forma ascendente.
		return repository.findByOrderByIdAlumnoAsc();
	}

	@Override
	public Alumno insertaActualizaAlumno(Alumno obj) {
		// Guarda o actualiza un alumno en el repositorio y retorna el objeto guardado.
		return repository.save(obj);
	}
	
	@Override
	public List<Alumno> listaPorDni(String dni) {
		// Llama al método del repositorio para obtener una lista de alumnos cuyo DNI coincide, ignorando mayúsculas y minúsculas.
		return repository.listaPorDniIgualRegistra(dni);
	}

	@Override
	public List<Alumno> listaAlumnoPorNombreLike(String nombre) {
		// Llama al método personalizado del repositorio para obtener una lista de alumnos cuyos nombres coinciden parcialmente.
		return repository.listaPorNombreLike(nombre);
	}

	@Override
	public void eliminaAlumno(int idAlumno) {
		// Elimina un alumno del repositorio según su ID.
		repository.deleteById(idAlumno);
	}

	@Override
	public List<Alumno> listaAlumnoPorNombreIgualActualiza(String nombre, int idAlumno) {
		return repository.listaPorNombreIgualActualiza(nombre, idAlumno);
	}

	@Override
	public List<Alumno> listaAlumnoPorNombreIgualRegistra(String nombre) {
		return repository.listaPorNombreIgualRegistra(nombre);
	}

	@Override
	public List<Alumno> listaPorTelefono(String telefono) {
		
		return repository.listaPorTelefonoIgualRegistra(telefono);
	}
}
