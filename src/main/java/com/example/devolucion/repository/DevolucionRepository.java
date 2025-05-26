package com.example.devolucion.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.devolucion.model.Devolucion;

@Repository
public interface DevolucionRepository extends JpaRepository<Devolucion, String> {
    
}
