package com.rednorte.msreasignacion.service;

import com.rednorte.msreasignacion.client.ListaEsperaClient;
import com.rednorte.msreasignacion.dto.SolicitudDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReasignacionServiceImplTest {

    private ListaEsperaClient listaEsperaClient;
    private ReasignacionServiceImpl service;

    @BeforeEach
    void setUp() {
        listaEsperaClient = mock(ListaEsperaClient.class);
        service = new ReasignacionServiceImpl(listaEsperaClient);
    }
    /*Antes de ejecutar cada prueba se crea un Mock del cliente Feign ListaEsperaClient. Esto permite controlar completamente
    las respuestas que recibirá el servicio y ejecutar las pruebas de forma aislada.*/

    private SolicitudDTO crearSolicitud(Long id){

        SolicitudDTO dto = new SolicitudDTO();
        dto.setId(id);
        dto.setPacienteId(id+100);
        dto.setTipo("CONSULTA");
        dto.setEstado("PENDIENTE");
        dto.setPrioridad("ALTA");

        return dto;
    }
    /*Se implementó un método auxiliar para generar objetos con datos simulados, facilitando la
   creación de diferentes escenarios de prueba y evitando la duplicación de código.*/

    @Test
    void debeRetornarUnaSolicitud(){

        when(listaEsperaClient.obtenerSolicitudes())
                .thenReturn(List.of(crearSolicitud(1L)));

        List<SolicitudDTO> lista = service.obtenerSolicitudes();

        assertEquals(1, lista.size());
        assertEquals(1L, lista.get(0).getId());

        verify(listaEsperaClient).obtenerSolicitudes();
    }

    @Test
    void debeRetornarListaVacia(){

        when(listaEsperaClient.obtenerSolicitudes())
                .thenReturn(List.of());

        List<SolicitudDTO> lista = service.obtenerSolicitudes();

        assertTrue(lista.isEmpty());

        verify(listaEsperaClient).obtenerSolicitudes();
    }

    @Test
    void debeRetornarTresSolicitudes(){

        when(listaEsperaClient.obtenerSolicitudes())
                .thenReturn(List.of(
                        crearSolicitud(1L),
                        crearSolicitud(2L),
                        crearSolicitud(3L)
                ));

        List<SolicitudDTO> lista = service.obtenerSolicitudes();

        assertEquals(3, lista.size());

        verify(listaEsperaClient).obtenerSolicitudes();
    }
    /*Esta prueba valida el comportamiento normal del servicio
    cuando el cliente Feign devuelve una única solicitud.*/

    @Test
    void primeraSolicitudDebeSerCorrecta(){

        when(listaEsperaClient.obtenerSolicitudes())
                .thenReturn(List.of(crearSolicitud(55L)));

        SolicitudDTO dto = service.obtenerSolicitudes().get(0);

        assertEquals(55L,dto.getId());
        assertEquals("CONSULTA",dto.getTipo());
        assertEquals("PENDIENTE",dto.getEstado());
        assertEquals("ALTA",dto.getPrioridad());

        verify(listaEsperaClient).obtenerSolicitudes();
    }
    /*Se configura el Mock para simular la respuesta del microservicio Lista de Espera.*/

    @Test
    void debeInvocarClienteUnaSolaVez(){

        when(listaEsperaClient.obtenerSolicitudes())
                .thenReturn(List.of());

        service.obtenerSolicitudes();

        verify(listaEsperaClient,times(1))
                .obtenerSolicitudes();

        verifyNoMoreInteractions(listaEsperaClient);
    }
    /*Se ejecuta el método del servicio que realiza la consulta al cliente Feign.*/

    @Test
    void debePropagarExcepcion(){

        when(listaEsperaClient.obtenerSolicitudes())
                .thenThrow(new RuntimeException("Error Feign"));

        RuntimeException ex = assertThrows(
                RuntimeException.class,
                () -> service.obtenerSolicitudes()
        );

        assertEquals("Error Feign",ex.getMessage());

        verify(listaEsperaClient).obtenerSolicitudes();
    }
    /*También se valida que el servicio haya invocado correctamente al cliente Feign.*/

}