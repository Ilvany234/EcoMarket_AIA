package com.ecomarket_spa.ecomarket_spa.service;

import com.ecomarket_spa.ecomarket_spa.model.Pedido;
import com.ecomarket_spa.ecomarket_spa.repository.PedidoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PedidoServiceTest {

    @Mock
    private PedidoRepository pedidoRepository;

    @InjectMocks
    private PedidoService pedidoService;

    @Test
    void testSave() {
        Pedido pedido = new Pedido();
        pedido.setUserId(1L);
        pedido.setOrderDate(LocalDateTime.now());
        pedido.setStatus("PENDIENTE");

        when(pedidoRepository.save(pedido)).thenReturn(pedido);

        Pedido resultado = pedidoService.save(pedido);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getUserId());
        assertEquals("PENDIENTE", resultado.getStatus());
        verify(pedidoRepository, times(1)).save(pedido);
    }

    @Test
    void testFindAll() {
        Pedido pedido1 = new Pedido();
        pedido1.setUserId(1L);

        Pedido pedido2 = new Pedido();
        pedido2.setUserId(2L);

        when(pedidoRepository.findAll()).thenReturn(List.of(pedido1, pedido2));

        List<Pedido> resultado = pedidoService.findAll();

        assertEquals(2, resultado.size());
        verify(pedidoRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        Pedido pedido = new Pedido();
        pedido.setUserId(1L);
        pedido.setStatus("ENVIADO");

        when(pedidoRepository.findById(1L)).thenReturn(Optional.of(pedido));

        Pedido resultado = pedidoService.findById(1L);

        assertNotNull(resultado);
        assertEquals("ENVIADO", resultado.getStatus());
        verify(pedidoRepository, times(1)).findById(1L);
    }

    @Test
    void testUpdate() {
        Pedido existing = new Pedido();
        existing.setUserId(1L);
        existing.setOrderDate(LocalDateTime.now().minusDays(1));
        existing.setStatus("PENDIENTE");

        Pedido updateData = new Pedido();
        updateData.setUserId(2L);
        updateData.setOrderDate(LocalDateTime.now());
        updateData.setStatus("ENVIADO");

        when(pedidoRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(pedidoRepository.save(any(Pedido.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Pedido resultado = pedidoService.update(1L, updateData);

        assertEquals(2L, resultado.getUserId());
        assertEquals("ENVIADO", resultado.getStatus());
        verify(pedidoRepository, times(1)).findById(1L);
        verify(pedidoRepository, times(1)).save(existing);
    }

    @Test
    void testDelete() {
        doNothing().when(pedidoRepository).deleteById(1L);

        pedidoService.delete(1L);

        verify(pedidoRepository, times(1)).deleteById(1L);
    }
}
