package com.centroinformacion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.centroinformacion.entity.Proveedor;

public interface ProveedorRepository extends JpaRepository<Proveedor, Integer>{
	
	public abstract List<Proveedor> findByOrderByRazonsocialAsc();
}
