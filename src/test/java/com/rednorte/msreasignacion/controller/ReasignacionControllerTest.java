package com.rednorte.msreasignacion.controller;

import com.rednorte.msreasignacion.dto.SolicitudDTO;
import com.rednorte.msreasignacion.service.ReasignacionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReasignacionControllerTest {
    /*A partir de este controlador se desarrollaron pruebas unitarias utilizando JUnit 5 y Mockito, con el objetivo de validar el
    comportamiento del controlador de forma aislada, simulando el servicio mediante objetos Mock.*/

    private ReasignacionService service;
    private ReasignacionController controller;

    @BeforeEach
    void setUp(){

        service = mock(ReasignacionService.class);

        controller = new ReasignacionController(service);
    }
    /*Antes de ejecutar cada prueba se crea un objeto Mock del servicio ReasignacionService, evitando depender de otros componentes del sistema.
    Posteriormente se instancia el controlador utilizando dicho Mock, logrando que todas las pruebas sean completamente independientes.*/

    private SolicitudDTO crear(Long id){
        /*Se implementó un método auxiliar encargado de generar objetos SolicitudDTO con datos de prueba.
   Esto evita duplicar código y facilita la construcción de distintos escenarios durante las pruebas.*/

        SolicitudDTO dto = new SolicitudDTO();

        dto.setId(id);
        dto.setPacienteId(id+1);
        dto.setTipo("CONTROL");
        dto.setEstado("PENDIENTE");
        dto.setPrioridad("MEDIA");

        return dto;
    }

    @Test
    void debeRetornarLista(){

        when(service.obtenerSolicitudes())
                .thenReturn(List.of(crear(1L)));

        List<SolicitudDTO> lista =
                controller.obtenerSolicitudes();

        assertEquals(1,lista.size());

        verify(service).obtenerSolicitudes();
    }
    /*La primera prueba valida el escenario normal,
     donde el servicio devuelve una lista con una solicitud.*/

    @Test
    void debeRetornarListaVacia(){

        when(service.obtenerSolicitudes())
                .thenReturn(List.of());

        assertTrue(
                controller.obtenerSolicitudes().isEmpty()
        );

        verify(service).obtenerSolicitudes();
    }

    @Test
    void debeRetornarDosSolicitudes(){

        when(service.obtenerSolicitudes())
                .thenReturn(List.of(
                        crear(1L),
                        crear(2L)
                ));

        assertEquals(
                2,
                controller.obtenerSolicitudes().size()
        );

        verify(service).obtenerSolicitudes();
    }
    /*Mediante Mockito se configura el
    comportamiento del servicio para que retorne una lista simulada.*/

    @Test
    void debeMantenerDatos(){

        when(service.obtenerSolicitudes())
                .thenReturn(List.of(crear(100L)));

        SolicitudDTO dto =
                controller.obtenerSolicitudes().get(0);
        /*Se ejecuta el método del controlador tal como ocurriría cuando un
         cliente realiza una petición HTTP al endpoint correspondiente.*/

        assertEquals(100L,dto.getId());
        assertEquals("CONTROL",dto.getTipo());
        assertEquals("MEDIA",dto.getPrioridad());

        verify(service).obtenerSolicitudes();
        /*También se comprueba que el controlador haya invocado correctamente el servicio.*/
    }

    @Test
    void debeLlamarServiceUnaVez(){

        when(service.obtenerSolicitudes())
                .thenReturn(List.of());

        controller.obtenerSolicitudes();

        verify(service,times(1))
                .obtenerSolicitudes();

        verifyNoMoreInteractions(service);
    }

}