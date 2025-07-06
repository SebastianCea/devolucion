package com.example.devolucion.assemblers;


import com.example.devolucion.controller.DevolucionControllerV2;
import com.example.devolucion.model.Devolucion;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class DevolucionModelAssembler implements RepresentationModelAssembler<Devolucion, EntityModel<Devolucion>> {
    @Override
    public EntityModel<Devolucion> toModel(Devolucion devolucion) {
        return EntityModel.of(devolucion,
                linkTo(methodOn(DevolucionControllerV2.class).getDevolucionById(devolucion.getId_devolucion())).withSelfRel(),
                linkTo(methodOn(DevolucionControllerV2.class).getAllDevolucion()).withRel("devolucion"),
                linkTo(methodOn(DevolucionControllerV2.class).createDevolucion(null)).withRel("Crear Devolucion"),
                linkTo(methodOn(DevolucionControllerV2.class).deleteDevolucion(devolucion.getId_devolucion())).withRel("Eliminar Devolucion"));
}
}
