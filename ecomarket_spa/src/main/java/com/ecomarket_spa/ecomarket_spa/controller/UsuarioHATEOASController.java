package com.ecomarket_spa.ecomarket_spa.controller;

import com.ecomarket_spa.ecomarket_spa.assembler.UsuarioModelAssembler;
import com.ecomarket_spa.ecomarket_spa.model.Usuario;
import com.ecomarket_spa.ecomarket_spa.service.UsuarioService;
import org.springframework.context.annotation.Profile;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/usuarios")
@Profile("hateoas")
public class UsuarioHATEOASController {

    private final UsuarioService usuarioService;
    private final UsuarioModelAssembler assembler;

    public UsuarioHATEOASController(UsuarioService usuarioService, UsuarioModelAssembler assembler) {
        this.usuarioService = usuarioService;
        this.assembler = assembler;
    }

    @GetMapping
    public CollectionModel<EntityModel<Usuario>> obtenerTodos() {
        List<EntityModel<Usuario>> usuarios = usuarioService.obtenerTodos().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(usuarios,
                linkTo(methodOn(UsuarioHATEOASController.class).obtenerTodos()).withSelfRel());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Usuario>> obtenerPorId(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioService.obtenerPorId(id);
        return usuario
                .map(u -> ResponseEntity.ok(assembler.toModel(u)))
                .orElse(ResponseEntity.notFound().build());
    }
}
