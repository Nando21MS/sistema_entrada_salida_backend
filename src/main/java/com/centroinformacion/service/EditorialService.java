package com.centroinformacion.service;

import java.util.List;
import com.centroinformacion.entity.Editorial;

public interface EditorialService {
//Para el crud  
	public abstract Editorial insertaActualizaEditorial(Editorial obj);

	public abstract List<Editorial> listaEditorialPorRazonSocialLike(String razonSocial);

	public abstract void eliminaEditorial(int idEditorial);

	public abstract List<Editorial> listaTodos();

	public abstract List<Editorial> listaEditorialPorRazonSocialIgualRegistra(String razonSocial);
	public abstract List<Editorial> listaEditorialPorRucIgualRegistra(String ruc);
	public abstract List<Editorial> listaEditorialPorRazonSocialIgualActualiza(String razonSocial, int idEditorial);
	public abstract List<Editorial> listaEditorialRucIgualActualiza(String ruc, int idEditorial);
	
}