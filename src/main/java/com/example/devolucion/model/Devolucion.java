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
    private int id_devolucion;
    
    @Column (length = 250, unique = true, nullable = false)
    private int id_venta;

    @Column (length = 250, unique = true, nullable = false)
    private int id_producto;

    @Column (length = 250, unique = true, nullable = false)
    private int id_cliente;

    @Column (length = 100, nullable = false)
    private String correo_cliente;

    @Column (nullable = false)
    private boolean tipo_documento;

    @Column (nullable = false)
    private LocalDate fecha_solicitud;

    @Column (length = 100, nullable = false)
    private String motivo;

    @Column (length = 50, nullable = false)
    private String estado;

    @Column (nullable = false)
    private LocalDate fecha_resolucion;

    @Column (length = 50, nullable = false)
    private String resuelto_por;

    @Column (length = 250, unique = true, nullable = false)
    private int id_soporte;

}
