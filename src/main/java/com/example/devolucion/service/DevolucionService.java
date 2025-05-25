package com.example.devolucion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.devolucion.model.Devolucion;
import com.example.devolucion.model.VentaDTO;
import com.example.devolucion.repository.DevolucionRepository;

@Service
public class DevolucionService {
    @Autowired
    private DevolucionRepository devolucionRepository;

    @Autowired
    private RestTemplate restTemplate;

    public List<Devolucion> listarTodos(){
        return devolucionRepository.findAll();
    }

    public Devolucion guardar(Devolucion devolucion){
        return devolucionRepository.save(devolucion);
    }

    public Devolucion devolucionxId(String id_devolucion){
        return devolucionRepository.getReferenceById(id_devolucion);
    }

    public Devolucion crearDevolucion(Devolucion devolucion) {
        // Llamada al microservicio cliente
        String url = "http://localhost:8081/api/ventas/" + devolucion.getIdVenta();
        VentaDTO venta = restTemplate.getForObject(url, VentaDTO.class);
        if (venta != null) {
            devolucion.setIdCliente(devolucion.getIdCliente());
        }
        return devolucionRepository.save(devolucion);
    }
}
