package com.centroinformacion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.centroinformacion.entity.Autor;

public interface AutorRepository extends JpaRepository<Autor, Integer>{

	@Query("select e from Autor e where e.nombres like ?1 ")
	public abstract List<Autor> listaPorNombresLike(String nombres);
	
	@Query("select a from Autor a where a.nombres = ?1")
	List<Autor> listaPorNombresIgualRegistra(String nombres);
	
	@Query("select a from Autor a where a.apellidos = ?1")
	List<Autor> listaPorApellidosIgualRegistra(String apellidos);
	
	@Query("select a from Autor a where a.celular = ?1")
	List<Autor> listaPorCelularIgualRegistra(String celular);
	
	@Query("select a from Autor a where a.telefono = ?1")
	List<Autor> listaAutorPorTelefonoIgualRegistra(String telefono);
	
	@Query("select a from Autor a where a.nombres = ?1 and a.idAutor != ?2")
	List<Autor> listaPorNombresIgualActualiza(String nombres, int idAutor);
	
	@Query("select a from Autor a where a.apellidos = ?1 and a.idAutor != ?2")
	List<Autor> listaPorApellidosIgualActualiza(String apellidos, int idAutor);
	
	@Query("select a from Autor a where a.celular = ?1 and a.idAutor != ?2")
	List<Autor> listaPorCelularIgualActualiz(String celular, int idAutor);
	
	@Query("select a from Autor a where a.telefono = ?1 and a.idAutor != ?2")
	List<Autor> listaAutorPorTelefonoIgualActualiza(String telefono , int idAutor);
}