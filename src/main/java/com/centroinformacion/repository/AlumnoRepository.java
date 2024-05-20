package com.centroinformacion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.centroinformacion.entity.Alumno;

// Define el repositorio para la entidad Alumno, extendiendo JpaRepository para proporcionar métodos CRUD.
public interface AlumnoRepository extends JpaRepository<Alumno, Integer> {
	
	// Método para obtener una lista de alumnos ordenados por apellidos de forma ascendente.
	public abstract List<Alumno> findByOrderByIdAlumnoAsc();
	
	// Método para obtener una lista de alumnos cuyo DNI coincide, ignorando mayúsculas y minúsculas.
	public List<Alumno> findByDniIgnoreCase(String dni);
	
	// CRUD: Define una consulta personalizada utilizando JPQL para buscar alumnos cuyos nombres coincidan parcialmente con el filtro.
	@Query("select a from Alumno a where concat(a.nombres, ' ', a.apellidos) like ?1") // JPQL
	public List<Alumno> listaPorNombreLike(String filtro);
	
	// Define una consulta personalizada utilizando JPQL para buscar alumnos cuyos nombres coincidan exactamente con el nombre proporcionado.
	@Query("select a from Alumno a where a.nombres = ?1 ")
	public abstract List<Alumno> listaPorNombreIgualRegistra(String nombre);
	
	// Define una consulta personalizada utilizando JPQL para buscar alumnos cuyos nombres coincidan exactamente con el nombre proporcionado, excluyendo un ID específico.
	@Query("select a from Alumno a where a.nombres = ?1 and a.idAlumno != ?2 ")
	public abstract List<Alumno> listaPorNombreIgualActualiza(String nombre, int idRevista);
}
