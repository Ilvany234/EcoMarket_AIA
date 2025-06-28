package com.ecomarket_spa.ecomarket_spa.assembler;

import com.ecomarket_spa.ecomarket_spa.controller.UsuarioHATEOASController;
import com.ecomarket_spa.ecomarket_spa.model.Usuario;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class UsuarioModelAssembler implements RepresentationModelAssembler<Usuario, EntityModel<Usuario>> {

    @Override
    public EntityModel<Usuario> toModel(Usuario usuario) {
        return EntityModel.of(usuario,
                linkTo(methodOn(UsuarioHATEOASController.class).obtenerPorId(usuario.getId())).withSelfRel(),
                linkTo(methodOn(UsuarioHATEOASController.class).obtenerTodos()).withRel("usuarios"));
    }
}
