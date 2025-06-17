package com.ecomarket_spa.ecomarket_spa.service;

import com.ecomarket_spa.ecomarket_spa.model.Pedido;
import com.ecomarket_spa.ecomarket_spa.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public Pedido save(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    public List<Pedido> findAll() {
        return pedidoRepository.findAll();
    }

    public Pedido findById(Long id) {
        return pedidoRepository.findById(id).orElseThrow();
    }

    public Pedido update(Long id, Pedido pedido) {
        Pedido existing = findById(id);
        existing.setUserId(pedido.getUserId());
        existing.setOrderDate(pedido.getOrderDate());
        existing.setStatus(pedido.getStatus());
        return pedidoRepository.save(existing);
    }

    public void delete(Long id) {
        pedidoRepository.deleteById(id);
    }
}
