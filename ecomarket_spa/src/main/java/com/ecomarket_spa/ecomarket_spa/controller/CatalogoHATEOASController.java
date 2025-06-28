package com.ecomarket_spa.ecomarket_spa.controller;

import com.ecomarket_spa.ecomarket_spa.assembler.CatalogoModelAssembler;
import com.ecomarket_spa.ecomarket_spa.model.Catalogo;
import com.ecomarket_spa.ecomarket_spa.service.CatalogoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/catalogo")
@Profile("hateoas")
public class CatalogoHATEOASController {

    @Autowired
    private CatalogoService catalogoService;

    @Autowired
    private CatalogoModelAssembler assembler;

    @GetMapping
    public CollectionModel<EntityModel<Catalogo>> obtenerCatalogo() {
        List<EntityModel<Catalogo>> productos = catalogoService.obtenerCatalogo()
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(productos,
                linkTo(methodOn(CatalogoHATEOASController.class).obtenerCatalogo()).withSelfRel());
    }

    @GetMapping("/{id}")
    public EntityModel<Catalogo> obtenerPorId(@PathVariable Long id) {
        Catalogo producto = catalogoService.obtenerPorId(id);
        return assembler.toModel(producto);
    }
}
