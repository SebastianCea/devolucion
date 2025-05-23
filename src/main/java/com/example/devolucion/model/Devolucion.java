package com.example.devolucion.model;

import java.time.LocalDate;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table (name = "Devolucion")
public class Devolucion {
    @Id 
    private String id_devolucion;
    
    @Column (length = 30, unique = true, nullable = false)
    private String id_venta;

    @Column (length = 30, unique = true, nullable = false)
    private String id_producto;

    @Column (length = 30, unique = true, nullable = false)
    private String id_cliente;

    @Column (nullable = false)
    private LocalDate fecha_solicitud;

    @Column (length = 100, nullable = false)
    private String motivo;

    @Column (length = 50, nullable = false)
    private String estado;

    @Column (length = 100, nullable = false)
    private String comentario_cliente;

    @Column (nullable = false)
    private LocalDate fecha_resolucion;

    @Column (length = 50, nullable = false)
    private String resuelto_por;

    @Column (length = 30, unique = true, nullable = false)
    private String id_soporte;

}
