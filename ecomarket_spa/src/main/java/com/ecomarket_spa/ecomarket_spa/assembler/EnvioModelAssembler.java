package com.ecomarket_spa.ecomarket_spa.assembler;

import com.ecomarket_spa.ecomarket_spa.controller.EnvioHATEOASController;
import com.ecomarket_spa.ecomarket_spa.model.Envio;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class EnvioModelAssembler implements RepresentationModelAssembler<Envio, EntityModel<Envio>> {

    @Override
    public EntityModel<Envio> toModel(Envio envio) {
        return EntityModel.of(envio,
                linkTo(methodOn(EnvioHATEOASController.class).obtenerPorId(envio.getId())).withSelfRel(),
                linkTo(methodOn(EnvioHATEOASController.class).obtenerTodos()).withRel("envios"));
    }
}
