package com.ecomarket_spa.ecomarket_spa.assembler;

import com.ecomarket_spa.ecomarket_spa.controller.PedidoHATEOASController;
import com.ecomarket_spa.ecomarket_spa.model.Pedido;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class PedidoModelAssembler implements RepresentationModelAssembler<Pedido, EntityModel<Pedido>> {

    @Override
    public EntityModel<Pedido> toModel(Pedido pedido) {
        return EntityModel.of(pedido,
                linkTo(methodOn(PedidoHATEOASController.class).obtenerPorId(pedido.getId())).withSelfRel(),
                linkTo(methodOn(PedidoHATEOASController.class).obtenerTodos()).withRel("pedidos"));
    }
}
