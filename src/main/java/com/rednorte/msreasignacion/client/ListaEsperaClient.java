package com.rednorte.msreasignacion.client;

import com.rednorte.msreasignacion.dto.SolicitudDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(
        name = "listas-espera",
        url = "${ms.listas-espera.url}"
)
public interface ListaEsperaClient {

    @GetMapping("/api/solicitudes")
    List<SolicitudDTO> obtenerSolicitudes();
}