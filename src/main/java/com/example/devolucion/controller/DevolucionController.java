package com.example.devolucion.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.devolucion.model.Devolucion;
import com.example.devolucion.service.DevolucionService;

@RestController
@RequestMapping("/api/v1/devoluciones")
public class DevolucionController {
    @Autowired
    private DevolucionService devolucionService;

    @PostMapping
    public Devolucion postDevolucion(@RequestBody Devolucion devolucion){
        return devolucionService.guardarDevolucion(devolucion);
    }

    @GetMapping
    public ResponseEntity<List<Devolucion>> getDevoluciones(){
        List<Devolucion> devoluciones=devolucionService.listarTodos();
        if(devoluciones.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(devoluciones, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Optional<Devolucion> getDevolucion(@PathVariable int id){
        return devolucionService.buscarxIdDevolucion(id);
    }

    @DeleteMapping("/{id}")
    public String deleteDevolucion(@PathVariable int id){
        return devolucionService.eliminarDevolucion(id);
    }
    
    /*@PostMapping
    public ResponseEntity<Devolucion> postDevolucion(@RequestBody Devolucion devolucion) {
        try {
            return new ResponseEntity<>(devolucionService.crearDevolucion(devolucion),HttpStatus.OK);
        } catch (Exception e) {
      
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }*/
}
