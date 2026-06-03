package com.rednorte.msreasignacion.controller;

import com.rednorte.msreasignacion.dto.SolicitudDTO;
import com.rednorte.msreasignacion.service.ReasignacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reasignacion")
@RequiredArgsConstructor
public class ReasignacionController {

    private final ReasignacionService reasignacionService;

    @GetMapping("/solicitudes")
    public List<SolicitudDTO> obtenerSolicitudes() {
        return reasignacionService.obtenerSolicitudes();
    }
}