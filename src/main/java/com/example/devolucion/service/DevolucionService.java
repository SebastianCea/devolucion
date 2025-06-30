package com.example.devolucion.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/*import org.springframework.web.client.RestTemplate;*/

import com.example.devolucion.model.Devolucion;
/*import com.example.devolucion.model.VentaDTO;*/
import com.example.devolucion.repository.DevolucionRepository;

@Service
public class DevolucionService {
    @Autowired
    private DevolucionRepository devolucionRepository;

    /*@Autowired
    private RestTemplate restTemplate;*/

    public List<Devolucion> listarTodos(){
        return devolucionRepository.findAll();
    }

    public Devolucion guardarDevolucion(Devolucion devolucion){
        return devolucionRepository.save(devolucion);
    }

    public String eliminarDevolucion(int id){
        if(devolucionRepository.existsById(id)){
            devolucionRepository.deleteById(id);
            return "Devolucion eliminada";
        }
        return "No se encontró la devolucion";
    }

    public Optional<Devolucion> buscarxIdDevolucion(int id_devolucion){
        return devolucionRepository.findById(id_devolucion);
    }

    /*public Devolucion crearDevolucion(Devolucion devolucion) {
        // Llamada al microservicio cliente
        String url = "http://localhost:8081/api/ventas/" + devolucion.getIdVenta();
        VentaDTO venta = restTemplate.getForObject(url, VentaDTO.class);
        if (venta != null) {
            devolucion.setIdProducto(devolucion.getIdProducto());
        }
        return devolucionRepository.save(devolucion);
    }*/
}
