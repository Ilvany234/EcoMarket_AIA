package com.ecomarket_spa.ecomarket_spa.controller;

import com.ecomarket_spa.ecomarket_spa.assembler.EnvioModelAssembler;
import com.ecomarket_spa.ecomarket_spa.model.Envio;
import com.ecomarket_spa.ecomarket_spa.service.EnvioService;
import org.springframework.context.annotation.Profile;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/envios")
@Profile("hateoas") // Solo se activa con este perfil
public class EnvioHATEOASController {

    private final EnvioService envioService;
    private final EnvioModelAssembler assembler;

    public EnvioHATEOASController(EnvioService envioService, EnvioModelAssembler assembler) {
        this.envioService = envioService;
        this.assembler = assembler;
    }

    @GetMapping
    public CollectionModel<EntityModel<Envio>> obtenerTodos() {
        List<Envio> envios = envioService.obtenerTodosLosEnvios();

        List<EntityModel<Envio>> modelos = envios.stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(modelos,
                linkTo(methodOn(EnvioHATEOASController.class).obtenerTodos()).withSelfRel());
    }

    @GetMapping("/{id}")
    public EntityModel<Envio> obtenerPorId(@PathVariable Long id) {
        Envio envio = envioService.obtenerEnvioPorId(id);
        return assembler.toModel(envio);
    }
}
