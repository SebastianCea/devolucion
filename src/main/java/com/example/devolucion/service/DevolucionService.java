package com.example.devolucion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.devolucion.model.Devolucion;
import com.example.devolucion.repository.DevolucionRepository;

@Service
public class DevolucionService {
    @Autowired
    private DevolucionRepository devolucionRepository;

    public List<Devolucion> listarTodos(){
        return devolucionRepository.findAll();
    }

    public Devolucion guardar(Devolucion devolucion){
        return devolucionRepository.save(devolucion);
    }

    public Devolucion devolucionxId(String id_devolucion){
        return devolucionRepository.getReferenceById(id_devolucion);
    }
}
