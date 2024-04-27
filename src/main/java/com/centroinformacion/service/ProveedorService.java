package com.centroinformacion.service;
import java.util.List;

import com.centroinformacion.entity.Proveedor;
public interface ProveedorService {
	public abstract Proveedor insertaActualizaProveedor(Proveedor obj);
	public abstract List<Proveedor> listaTodos();

}
