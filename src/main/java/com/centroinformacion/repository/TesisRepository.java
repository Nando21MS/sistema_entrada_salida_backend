package com.centroinformacion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.centroinformacion.entity.Tesis;

public interface TesisRepository extends JpaRepository<Tesis, Integer> {
	
	@Query("select e from Tesis e where e.titulo like ?1 ")
	public abstract List<Tesis> listaPorTituloLike(String nombres);
	
	@Query("select e from Tesis e where e.titulo = ?1 ")
	public abstract List<Tesis> listaPorTituloIgualRegistra(String nombres);
	
	@Query("select e from Tesis e where e.titulo = ?1 and e.idTesis != ?2 ")
	public abstract List<Tesis> listaPorTituloIgualActualiza(String nombres, int idAutor);
	
}
