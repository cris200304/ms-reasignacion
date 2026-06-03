package com.rednorte.msreasignacion.dto;

import lombok.Data;

@Data
public class SolicitudDTO {

    private Long id;
    private Long pacienteId;
    private String tipo;
    private String estado;
    private String prioridad;
}