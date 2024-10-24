package com.centroinformacion.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.centroinformacion.entity.Accesos;
import com.centroinformacion.entity.Rol;

public interface AccesosRepository extends JpaRepository<Accesos, Integer>{
	public List<Accesos> findByCodigo(int codigo);
	public List<Accesos> findByRol(Rol idRol);
	public List<Accesos> findByFecha(Date fecha);
	
	@Query("select p from Accesos p where "
	        + "(p.codigo = ?1) and "
	        + "(p.fecha = ?2) and "
	        + "(p.estado = ?3) and "
	        + "(?4 = -1 or p.rol.idRol = ?4)")
	public abstract List<Accesos> listaCompleja(int codigo, 
	                                             Date fecha,
	                                             int estado,
	                                             int idRol);

}