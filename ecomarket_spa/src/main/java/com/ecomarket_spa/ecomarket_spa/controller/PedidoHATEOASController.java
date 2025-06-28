package com.ecomarket_spa.ecomarket_spa.controller;

import com.ecomarket_spa.ecomarket_spa.assembler.PedidoModelAssembler;
import com.ecomarket_spa.ecomarket_spa.model.Pedido;
import com.ecomarket_spa.ecomarket_spa.service.PedidoService;
import org.springframework.context.annotation.Profile;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/pedidos")
@Profile("hateoas")
public class PedidoHATEOASController {

    private final PedidoService pedidoService;
    private final PedidoModelAssembler assembler;

    public PedidoHATEOASController(PedidoService pedidoService, PedidoModelAssembler assembler) {
        this.pedidoService = pedidoService;
        this.assembler = assembler;
    }

    @GetMapping
    public CollectionModel<EntityModel<Pedido>> obtenerTodos() {
        List<Pedido> pedidos = pedidoService.obtenerTodosLosPedidos();

        List<EntityModel<Pedido>> modelos = pedidos.stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(modelos,
                linkTo(methodOn(PedidoHATEOASController.class).obtenerTodos()).withSelfRel());
    }

    @GetMapping("/{id}")
    public EntityModel<Pedido> obtenerPorId(@PathVariable Long id) {
        Pedido pedido = pedidoService.obtenerPedidoPorId(id);
        return assembler.toModel(pedido);
    }
}
