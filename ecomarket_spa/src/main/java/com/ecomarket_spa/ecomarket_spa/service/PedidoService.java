package com.ecomarket_spa.ecomarket_spa.service;

import com.ecomarket_spa.ecomarket_spa.model.Pedido;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profile("hateoas")
public class PedidoService {

    private final List<Pedido> pedidos = List.of(
        new Pedido(1L, "Cliente A", "2025-07-01", "Pendiente"),
        new Pedido(2L, "Cliente B", "2025-07-02", "Enviado")
    );

    public List<Pedido> obtenerTodosLosPedidos() {
        return pedidos;
    }

    public Pedido obtenerPedidoPorId(Long id) {
        return pedidos.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
    }

    public Pedido crearPedido(Pedido pedido) {
        return pedido;
    }

    public Pedido actualizarPedido(Long id, Pedido pedido) {
        return pedido;
    }

    public void eliminarPedido(Long id) {
        // Simulado
    }
}
