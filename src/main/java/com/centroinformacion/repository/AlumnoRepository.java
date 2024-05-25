package com.centroinformacion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.centroinformacion.entity.Alumno;

// Define el repositorio para la entidad Alumno, extendiendo JpaRepository para proporcionar métodos CRUD.
public interface AlumnoRepository extends JpaRepository<Alumno, Integer> {
	
public abstract List<Alumno> findByOrderByApellidosAsc();
	 
	@Query("select t from Alumno t where t.nombres like ?1")
	public abstract List<Alumno> listaAlumnoPorTituloLike(String titulo);
	
	@Query("select t from Alumno t where t.nombres like ?1")
	public abstract List<Alumno> listaPorTituloLikeIgualRegistra(String titulo);
	
	@Query("select t from Alumno t where t.nombres = ?1 and t.idAlumno != ?2")
	public abstract List<Alumno> listaPorTituloIgualActualiza(String titulo, int idAlumno);
	
	@Query("select t from Alumno t where t.nombres = ?1")
	public abstract List<Alumno> validacionListaPorNombreIgualRegistra(String titulo);
}
