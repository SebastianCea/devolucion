package com.example.devolucion.model;

import java.time.LocalDate;

import lombok.Data;

@Data
public class VentaDTO {
    private String idVenta;
    private String idProducto;
    private LocalDate fecha_venta;
    private String tipo_documeno;
    private Double total_final;
    private String idCliente;

}
