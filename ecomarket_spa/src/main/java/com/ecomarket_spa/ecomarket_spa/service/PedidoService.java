package com.ecomarket_spa.ecomarket_spa.service;


import com.ecomarket_spa.ecomarket_spa.model.Pedido;
import org.springframework.stereotype.Service;


import java.util.*;
import java.util.concurrent.atomic.AtomicLong;


@Service
public class PedidoService {


    private final Map<Long, Pedido> pedidoStorage = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong();


    public List<Pedido> obtenerTodosLosPedidos() {
        return new ArrayList<>(pedidoStorage.values());
    }


    public Pedido obtenerPedidoPorId(Long id) {
        Pedido pedido = pedidoStorage.get(id);
        if (pedido == null) {
            throw new NoSuchElementException("Pedido no encontrado con ID: " + id);
        }
        return pedido;
    }


    // Puedes agregar métodos crear, actualizar y eliminar si el controller los requiere más adelante.
    public Pedido crearPedido(Pedido pedido) {
        Long id = idGenerator.incrementAndGet();
        pedido.setId(id);
        pedidoStorage.put(id, pedido);
        return pedido;
    }


    public Pedido actualizarPedido(Long id, Pedido pedidoActualizado) {
        if (!pedidoStorage.containsKey(id)) {
            throw new NoSuchElementException("No se puede actualizar. Pedido no encontrado con ID: " + id);
        }
        pedidoActualizado.setId(id);
        pedidoStorage.put(id, pedidoActualizado);
        return pedidoActualizado;
    }


    public void eliminarPedido(Long id) {
        if (!pedidoStorage.containsKey(id)) {
            throw new NoSuchElementException("No se puede eliminar. Pedido no encontrado con ID: " + id);
        }
        pedidoStorage.remove(id);
    }
}
