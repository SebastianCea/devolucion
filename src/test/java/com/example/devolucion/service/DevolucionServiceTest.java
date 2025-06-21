package com.example.devolucion.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.devolucion.model.Devolucion;
import com.example.devolucion.repository.DevolucionRepository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

class DevolucionServiceTest {

    @Mock
    private DevolucionRepository devolucionRepository;

    @InjectMocks
    private DevolucionService devolucionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    /* Test para guardar devolucion en la capa servicio */
    @Test
    void testGuardarDevolucion() {
        Devolucion devolucion = new Devolucion("DEV12345", 1001, 501, 300,"cliente@ejemplo.com",true,LocalDate.of(2023, 9, 1),"Producto defectuoso al recibirlo","PENDIENTE",LocalDate.of(2023, 9, 15),"Juan Pérez", "SOP-001" );
        Devolucion devolucionGuardada = new Devolucion("DEV12345", 1001, 501, 300,"cliente@ejemplo.com",true,LocalDate.of(2023, 9, 1),"Producto defectuoso al recibirlo","PENDIENTE",LocalDate.of(2023, 9, 15),"Juan Pérez", "SOP-001" );
        when(devolucionRepository.save(devolucion)).thenReturn(devolucionGuardada);

        Devolucion resultado = devolucionService.guardarDevolucion(devolucion);
        assertThat(resultado.getId_devolucion()).isEqualTo("DEV12345");
        verify(devolucionRepository).save(devolucion);
    }

    @Test
    void testListarDevoluciones() {
        Devolucion d1 = new Devolucion("DEV12345", 1001, 501, 300,"cliente@ejemplo.com",true,LocalDate.of(2023, 9, 1),"Producto defectuoso al recibirlo","PENDIENTE",LocalDate.of(2023, 9, 15),"Juan Pérez", "SOP-001" );
        Devolucion d2 = new Devolucion("DEV12346", 1002, 502, 301,"cliente@ejemplo.cl",true,LocalDate.of(2023, 10, 1),"Producto defectuoso al recibirlo","PENDIENTE",LocalDate.of(2023, 10, 16),"Juanito Pérez", "SOP-002" );
        when(devolucionRepository.findAll()).thenReturn(Arrays.asList(d1, d2));

        List<Devolucion> resultado = devolucionService.listarTodos();
        assertThat(resultado).hasSize(2).contains(d1, d2);
        verify(devolucionRepository).findAll();
    }

    @Test
    void testEliminarDevolucion() {
        // ID de devolución de ejemplo
        String idDevolucion = "DEV12345";

        // Llamamos al método de servicio para eliminar
        devolucionService.eliminarDevolucion(idDevolucion);

        // Verificamos que el repositorio fue llamado para eliminar la devolución
        verify(devolucionRepository).deleteById(idDevolucion);
    }

}