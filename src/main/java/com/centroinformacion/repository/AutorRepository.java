package com.centroinformacion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.centroinformacion.entity.Autor;

public interface AutorRepository extends JpaRepository<Autor, Integer>{

	@Query("select e from Autor e where e.nombres like ?1 ")
	public abstract List<Autor> listaPorNombresLike(String nombres);
	
	@Query("select e from Autor e where e.nombres = ?1 ")
	public abstract List<Autor> listaPorNombresIgualRegistra(String nombres);
	
	@Query("select e from Autor e where e.nombres = ?1 and e.idAutor != ?2 ")
	public abstract List<Autor> listaPorNombresIgualActualiza(String nombres, int idAutor);
	
	

}
