package com.centroinformacion.service;

import java.util.List;

import com.centroinformacion.entity.Alumno;

public interface AlumnoService {
	
	public abstract Alumno insertaActualizaAlumno(Alumno obj);
	public abstract List<Alumno> listaTodos();
	public abstract List<Alumno> listaPorDni(String dni);
	
	//CRUD
	//public abstract Alumno insertaActualizaAlumno(Alumno obj);
	public abstract List<Alumno> listaAlumnoPorNombreLike(String nombre);
	public abstract void eliminaAlumno(int idAlumno);
	//	public abstract List<Alumno> listaTodos();

	
	//Validaciones Para Actualizar
			public abstract List<Alumno> listaAlumnoPorNombreIgualActualiza(String nombre, int idAlumno);
			//Validaciones Para Revistrar
			public abstract List<Alumno> listaAlumnoPorNombreIgualRegistra(String nombre);

}
