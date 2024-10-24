package com.centroinformacion.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.centroinformacion.entity.Accesos;
import com.centroinformacion.entity.Rol;

public interface AccesosRepository extends JpaRepository<Accesos, Integer> {
    List<Accesos> findByCodigo(String codigo);
    List<Accesos> findByRol(Rol idRol);
    List<Accesos> findByFecha(Date fecha);
    
    @Query("select a from Accesos a where "
			+ " a.codigo like ?1  and "
			+ " a.fecha >= ?2 and "
			+ " a.fecha <= ?3 and "
			+ " (?4 = -1 or a.rol.idRol = ?4) ")
	public abstract List<Accesos> listaConsultaCompleja(
			String codigo, 
			Date fechaDesde, 
			Date fechaHasta,
			int idRol);

}
