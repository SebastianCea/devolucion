package com.example.devolucion.assemblers;

import com.example.devolucion.controller.DevolucionController;
import com.example.devolucion.model.Devolucion;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


public class DevolucionModelAssembler implements RepresentationModelAssembler<Devolucion, EntityModel<Devolucion>> {
    @Override
    public EntityModel<Devolucion> toModel(Devolucion devolucion) {
        return EntityModel.of(devolucion,
                linkTo(methodOn(DevolucionController.class).getDevolucion(devolucion.getId_devolucion())).withSelfRel(),
                linkTo(methodOn(DevolucionController.class).getDevoluciones()).withRel("devolucion"));
}
}
