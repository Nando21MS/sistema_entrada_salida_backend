package com.centroinformacion.service;

import java.util.List;

import com.centroinformacion.entity.Tesis;

public interface TesisService {
	public abstract Tesis insertaActualizaTesis(Tesis obj);
	public abstract List<Tesis> listaTesis();
}
