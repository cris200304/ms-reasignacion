package com.rednorte.msreasignacion.service;

import com.rednorte.msreasignacion.dto.SolicitudDTO;

import java.util.List;

public interface ReasignacionService {

    List<SolicitudDTO> obtenerSolicitudes();

}