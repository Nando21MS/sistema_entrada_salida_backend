package com.centroinformacion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.centroinformacion.entity.Libro;

public interface LibroRepository extends JpaRepository<Libro, Integer>{

 

	@Query("select e from Libro e where e.titulo like ?1 ")
	public abstract List<Libro> listaPorTituloLike(String nombres);

	@Query("select e from Libro e where e.titulo = ?1 ")
	public abstract List<Libro> listaPorTituloIgualRegistra(String nombres);

	@Query("select e from Libro e where e.titulo = ?1 and e.idLibro != ?2 ")
	public abstract List<Libro> listaPorTituloIgualActualiza(String nombres, int idAutor);
}
