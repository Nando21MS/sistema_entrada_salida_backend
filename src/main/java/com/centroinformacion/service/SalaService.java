package com.centroinformacion.service;

import java.util.List;
//AUTOR : SIFUENTES GAMBINI CESAR JAREN

import com.centroinformacion.entity.Sala;

public interface SalaService {

	public abstract Sala insertaActualizaSala(Sala obj);
	public abstract List<Sala> listaTodos();
	
	
	public abstract List<Sala> listaSalaPorNumeroLike(String numero);
	
	
	
	public abstract List<Sala> listaSalaPorNumeroIgualRegistra(String numero);
	
	public abstract List<Sala> listaSalaPorNombreIgualActualiza(String numero, int idSala);
	
	public abstract void eliminaSala(int idSala);

}
