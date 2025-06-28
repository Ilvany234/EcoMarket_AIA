package com.ecomarket_spa.ecomarket_spa.assembler;

import com.ecomarket_spa.ecomarket_spa.controller.CatalogoHATEOASController;
import com.ecomarket_spa.ecomarket_spa.model.Catalogo;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class CatalogoModelAssembler implements RepresentationModelAssembler<Catalogo, EntityModel<Catalogo>> {

    @Override
    public EntityModel<Catalogo> toModel(Catalogo catalogo) {
        return EntityModel.of(catalogo,
                linkTo(methodOn(CatalogoHATEOASController.class).obtenerPorId(catalogo.getId())).withSelfRel(),
                linkTo(methodOn(CatalogoHATEOASController.class).obtenerCatalogo()).withRel("catalogo"));
    }
}
