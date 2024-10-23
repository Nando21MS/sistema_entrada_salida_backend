package com.centroinformacion.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.centroinformacion.entity.Externo;


public interface PersonaExternaRepository extends JpaRepository<Externo,Integer>{
	
	@Query("select e from Externo e where e.num_doc= ?1 ")
	public abstract List<Externo> listaPorDniIgualRegistra(String num_doc);
}
