package com.centroinformacion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.centroinformacion.entity.Editorial;

public interface EditorialRepository extends JpaRepository<Editorial, Integer>{
	public List<Editorial> findByRuc(String ruc);
	public List<Editorial> findByRazonSocial(String razonSocial);

	@Query("select e from Editorial e where e.razonSocial like ?1 ")
	public abstract List<Editorial> listaPorRazonSocialLike(String razonSocial);
	
	@Query("select e from Editorial e where e.razonSocial = ?1 ")
	public abstract List<Editorial> listaPorRazonSocialIgualRegistra(String razonSocial);
	
	@Query("select e from Editorial e where e.razonSocial = ?1 and e.idEditorial != ?2 ")
	public abstract List<Editorial> listaPorRazonSocialIgualActualiza(String razonSocial, int idEditorial);
	
	public abstract List<Editorial> findByOrderByRazonSocialAsc();
}
