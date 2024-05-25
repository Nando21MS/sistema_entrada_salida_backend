package com.centroinformacion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
//AUTOR : SIFUENTES GAMBINI CESAR JAREN
import org.springframework.data.jpa.repository.Query;

import com.centroinformacion.entity.Sala;

public interface SalaRepository extends JpaRepository<Sala, Integer>{

	
	@Query("select s from Sala s where s.numero like ?1 ")
	public abstract List<Sala> listaPorNumeroLike(String numero);
	
	@Query("select s from Sala s where s.numero = ?1 ")
	public abstract List<Sala> listaPorNumeroIgualRegistra(String numero);
	
	@Query("select s from Sala s where s.numero = ?1 and s.idSala != ?2 ")
	public abstract List<Sala> listaPorNumeroIgualActualiza(String numero, int idSala);

}
