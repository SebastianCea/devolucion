package com.example.devolucion.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


import java.util.List;
import java.util.stream.Collectors;
import org.springframework.hateoas.MediaTypes;

import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.devolucion.assemblers.DevolucionModelAssembler;
import com.example.devolucion.model.Devolucion;
import com.example.devolucion.service.DevolucionService;


@RestController
@RequestMapping("api/v2/devoluciones")
public class DevolucionControllerV2 {
    
    @Autowired
    private DevolucionService devolucionService;

    @Autowired
    private DevolucionModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Devolucion>> getAllDevolucion(){
        List<EntityModel<Devolucion>> devoluciones = devolucionService.listarTodos().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(devoluciones,
                linkTo(methodOn(DevolucionControllerV2.class).getAllDevolucion()).withSelfRel());
    }

    @GetMapping(value = "/{idDevolucion}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Devolucion> getDevolucionById(@PathVariable int idDevolucion) {
        Devolucion devolucion = devolucionService.buscarxIdDevolucion(idDevolucion).get();
            
     
     return assembler.toModel(devolucion);
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Devolucion>> createDevolucion(@RequestBody Devolucion devolucion) {
        Devolucion devolucion2 = devolucionService.guardarDevolucion(devolucion);
        return ResponseEntity
                .created(linkTo(methodOn(DevolucionControllerV2.class).getDevolucionById(devolucion2.getId_devolucion())).toUri())
                .body(assembler.toModel(devolucion2));
    }


}
