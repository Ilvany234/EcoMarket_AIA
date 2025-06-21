package com.ecomarket_spa.ecomarket_spa.service;

import com.ecomarket_spa.ecomarket_spa.model.Envio;
import com.ecomarket_spa.ecomarket_spa.repository.EnvioRepository;
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
class EnvioServiceTest {

    @Mock
    private EnvioRepository envioRepository;

    @InjectMocks
    private EnvioService envioService;

    @Test
    void testObtenerTodos() {
        Envio envio1 = new Envio();
        Envio envio2 = new Envio();
        when(envioRepository.findAll()).thenReturn(List.of(envio1, envio2));

        List<Envio> resultado = envioService.obtenerTodos();

        assertEquals(2, resultado.size());
        verify(envioRepository, times(1)).findAll();
    }

    @Test
    void testObtenerPorId_Existente() {
        Envio envio = new Envio();
        envio.setId(1L);

        when(envioRepository.findById(1L)).thenReturn(Optional.of(envio));

        Optional<Envio> resultado = envioService.obtenerPorId(1L);

        assertTrue(resultado.isPresent());
        assertEquals(1L, resultado.get().getId());
        verify(envioRepository, times(1)).findById(1L);
    }

    @Test
    void testCrearEnvio() {
        Envio envio = new Envio();
        envio.setDireccionEntrega("Av. Siempre Viva 123");

        when(envioRepository.save(envio)).thenReturn(envio);

        Envio resultado = envioService.crearEnvio(envio);

        assertNotNull(resultado);
        assertEquals("Av. Siempre Viva 123", resultado.getDireccionEntrega());
        verify(envioRepository, times(1)).save(envio);
    }

    @Test
    void testActualizarEnvio_Existente() {
        Envio envioExistente = new Envio();
        envioExistente.setId(1L);
        envioExistente.setDireccionEntrega("Dirección antigua");

        Envio envioActualizado = new Envio();
        envioActualizado.setDireccionEntrega("Dirección nueva");
        envioActualizado.setPedidoId(10L);
        envioActualizado.setTransportista("Transportista X");
        envioActualizado.setEstado("En camino");
        envioActualizado.setFechaEnvio(LocalDateTime.now());
        envioActualizado.setFechaEntregaEstimada(LocalDateTime.now().plusDays(3));

        when(envioRepository.findById(1L)).thenReturn(Optional.of(envioExistente));
        when(envioRepository.save(any(Envio.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Envio resultado = envioService.actualizarEnvio(1L, envioActualizado);

        assertEquals("Dirección nueva", resultado.getDireccionEntrega());
        assertEquals(10L, resultado.getPedidoId());
        assertEquals("Transportista X", resultado.getTransportista());
        assertEquals("En camino", resultado.getEstado());
        assertNotNull(resultado.getFechaEnvio());
        assertNotNull(resultado.getFechaEntregaEstimada());

        verify(envioRepository, times(1)).findById(1L);
        verify(envioRepository, times(1)).save(envioExistente);
    }

    @Test
    void testActualizarEnvio_NoExistente() {
        Envio envioActualizado = new Envio();

        when(envioRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            envioService.actualizarEnvio(1L, envioActualizado);
        });

        assertEquals("Envío no encontrado", exception.getMessage());
        verify(envioRepository, times(1)).findById(1L);
        verify(envioRepository, never()).save(any());
    }

    @Test
    void testEliminarEnvio() {
        doNothing().when(envioRepository).deleteById(1L);

        envioService.eliminarEnvio(1L);

        verify(envioRepository, times(1)).deleteById(1L);
    }
}
