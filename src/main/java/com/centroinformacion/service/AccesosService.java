package com.centroinformacion.service;

import java.util.Date;
import java.util.List;

import com.centroinformacion.entity.Accesos;
import com.centroinformacion.entity.Rol;

public interface AccesosService {
	public abstract List<Accesos> listaPorCodigo(int codigo);
	public abstract List<Accesos> listaPorRol(Rol rol);
	public abstract List<Accesos> listaPorFecha(Date fecha);
	
	public abstract List<Accesos> listaCompleja(int codigo, Date fecha,int estado, int idRol);
}
