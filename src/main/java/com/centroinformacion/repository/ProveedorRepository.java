package com.centroinformacion.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.centroinformacion.entity.Proveedor;

public interface ProveedorRepository extends JpaRepository<Proveedor, Integer>{
	@Query("select e from Proveedor e where e.razonsocial like ?1 ")
	public abstract List<Proveedor> listaPorRazonSocialLike(String razonsocial);
	
	@Query("select e from Proveedor e where e.razonsocial = ?1 ")
	public abstract List<Proveedor> listaPorRazonSocialIgualRegistra(String razonsocial);
	@Query("select e from Proveedor e where e.razonsocial = ?1 and e.idProveedor != ?2 ")
	public abstract List<Proveedor> listaPorRazonSocialIgualActualiza(String razonsocial, int idProveedor);
	public abstract List<Proveedor> findByOrderByRazonsocialAsc();
}
