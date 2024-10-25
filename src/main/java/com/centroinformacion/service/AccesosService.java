package com.centroinformacion.service;

import java.util.Date;
import java.util.List;

import com.centroinformacion.entity.Accesos;
import com.centroinformacion.entity.Rol;

public interface AccesosService {
    List<Accesos> listaPorCodigo(String codigo);
    List<Accesos> listaPorRol(Rol rol);
    List<Accesos> listaPorFecha(Date fecha);
    List<Accesos> listaConsultaCompleja(String codigo, Date fechaDesde, Date fechaHasta, int idRol);

    // Para apartado "Mis Accesos" de la app móvil
    List<Accesos> listaPorCodigoYFecha(String login, Date fecha);
}
