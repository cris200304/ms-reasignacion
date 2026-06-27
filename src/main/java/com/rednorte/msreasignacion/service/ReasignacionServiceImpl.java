package com.rednorte.msreasignacion.service;

import com.rednorte.msreasignacion.client.ListaEsperaClient;
import com.rednorte.msreasignacion.dto.SolicitudDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReasignacionServiceImpl implements ReasignacionService {

    private final ListaEsperaClient listaEsperaClient;

    @Override
    public List<SolicitudDTO> obtenerSolicitudes() {
        return listaEsperaClient.obtenerSolicitudes();
    }
}