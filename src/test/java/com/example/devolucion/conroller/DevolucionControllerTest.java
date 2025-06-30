package com.example.devolucion.conroller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.http.MediaType;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.devolucion.controller.DevolucionController;
import com.example.devolucion.model.Devolucion;
import com.example.devolucion.service.DevolucionService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(DevolucionController.class)
public class DevolucionControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DevolucionService devolucionService;

    @Autowired
    private ObjectMapper objectMapper;

    private Devolucion devolucion;

    @BeforeEach
    void setUp(){
        devolucion = new Devolucion();
        devolucion.setId_devolucion(1);
        devolucion.setId_venta(1001);
        devolucion.setId_producto(501);
        devolucion.setId_cliente(300);
        devolucion.setCorreo_cliente("cliente@ejemplo.com");
        devolucion.setTipo_documento(true);
        devolucion.setFecha_solicitud(LocalDate.of(2023, 9, 1));
        devolucion.setMotivo("Producto defectuoso al recibirlo");
        devolucion.setEstado("PENDIENTE");
        devolucion.setFecha_resolucion(LocalDate.of(2023, 9, 15));
        devolucion.setResuelto_por("Juan Pérez");
        devolucion.setId_soporte(100);
    }

    @Test
    public void testGetAllDevoluciones() throws Exception{
        when(devolucionService.listarTodos()).thenReturn(List.of(devolucion));

        mockMvc.perform(get("/api/v1/devoluciones")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id_devolucion").value(1))
                .andExpect(jsonPath("$[0].id_cliente").value(300))
                .andExpect(jsonPath("$[0].id_venta").value(1001))
                .andExpect(jsonPath("$[0].id_producto").value(501))
                .andExpect(jsonPath("$[0].correo_cliente").value("cliente@ejemplo.com"))
                .andExpect(jsonPath("$[0].tipo_documento").value(true))
                .andExpect(jsonPath("$[0].fecha_solicitud").value("2023-09-01"))
                .andExpect(jsonPath("$[0].motivo").value("Producto defectuoso al recibirlo"))
                .andExpect(jsonPath("$[0].estado").value("PENDIENTE"))
                .andExpect(jsonPath("$[0].fecha_resolucion").value("2023-09-15"))
                .andExpect(jsonPath("$[0].resuelto_por").value("Juan Pérez"))
                .andExpect(jsonPath("$[0].id_soporte").value(100));

    }

    @Test
    public void testGetAllDevolucionesEmpty() throws Exception {
        when(devolucionService.listarTodos()).thenReturn(List.of());

         mockMvc.perform(get("/api/v1/devoluciones")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
}


    @Test
    public void testGetDevolucionById() throws Exception {
        when(devolucionService.buscarxIdDevolucion(1)).thenReturn(Optional.of(devolucion));

        mockMvc.perform(get("/api/v1/devoluciones/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id_devolucion").value(1))
                .andExpect(jsonPath("$.id_cliente").value(300))
                .andExpect(jsonPath("$.id_venta").value(1001))
                .andExpect(jsonPath("$.id_producto").value(501))
                .andExpect(jsonPath("$.correo_cliente").value("cliente@ejemplo.com"))
                .andExpect(jsonPath("$.tipo_documento").value(true))
                .andExpect(jsonPath("$.fecha_solicitud").value("2023-09-01"))
                .andExpect(jsonPath("$.motivo").value("Producto defectuoso al recibirlo"))
                .andExpect(jsonPath("$.estado").value("PENDIENTE"))
                .andExpect(jsonPath("$.fecha_resolucion").value("2023-09-15"))
                .andExpect(jsonPath("$.resuelto_por").value("Juan Pérez"))
                .andExpect(jsonPath("$.id_soporte").value(100));
    }

    @Test
    public void testCreateDevolucion() throws Exception{
        when(devolucionService.guardarDevolucion(any(Devolucion.class))).thenReturn(devolucion);

        mockMvc.perform(post("/api/v1/devoluciones")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(devolucion)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id_devolucion").value(1))
                .andExpect(jsonPath("$.id_cliente").value(300))
                .andExpect(jsonPath("$.id_venta").value(1001))
                .andExpect(jsonPath("$.id_producto").value(501))
                .andExpect(jsonPath("$.correo_cliente").value("cliente@ejemplo.com"))
                .andExpect(jsonPath("$.tipo_documento").value(true))
                .andExpect(jsonPath("$.fecha_solicitud").value("2023-09-01"))
                .andExpect(jsonPath("$.motivo").value("Producto defectuoso al recibirlo"))
                .andExpect(jsonPath("$.estado").value("PENDIENTE"))
                .andExpect(jsonPath("$.fecha_resolucion").value("2023-09-15"))
                .andExpect(jsonPath("$.resuelto_por").value("Juan Pérez"))
                .andExpect(jsonPath("$.id_soporte").value(100));
    }

    @Test
    public void testDeleteDevolucion() throws Exception{
        when(devolucionService.eliminarDevolucion(1)).thenReturn("Devolucion eliminada");

        mockMvc.perform(delete("/api/v1/devoluciones/1"))
               .andExpect(status().isOk())     
               .andExpect(content().string("Devolucion eliminada"));

        verify(devolucionService, times(1)).eliminarDevolucion(1);

    }
}
