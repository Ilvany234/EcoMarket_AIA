package com.ecomarket_spa.ecomarket_spa.service;

import com.ecomarket_spa.ecomarket_spa.model.Catalogo;
import com.ecomarket_spa.ecomarket_spa.repository.CatalogoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CatalogoServiceTest {

    @Mock
    private CatalogoRepository productRepository;

    @InjectMocks
    private CatalogoService catalogoService;

    @Test
    void testSave() {
        Catalogo product = new Catalogo();
        product.setName("Producto A");
        product.setDescription("Descripción A");
        product.setPrice(100.0);
        product.setStock(10);

        when(productRepository.save(product)).thenReturn(product);

        Catalogo resultado = catalogoService.save(product);

        assertNotNull(resultado);
        assertEquals("Producto A", resultado.getName());
        assertEquals(100.0, resultado.getPrice());
        verify(productRepository, times(1)).save(product);
    }

    @Test
    void testFindAll() {
        Catalogo product1 = new Catalogo();
        Catalogo product2 = new Catalogo();

        when(productRepository.findAll()).thenReturn(List.of(product1, product2));

        List<Catalogo> resultado = catalogoService.findAll();

        assertEquals(2, resultado.size());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        Catalogo product = new Catalogo();
        product.setId(1L);
        product.setName("Producto A");

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        Catalogo resultado = catalogoService.findById(1L);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Producto A", resultado.getName());
        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    void testUpdate() {
        Catalogo existing = new Catalogo();
        existing.setId(1L);
        existing.setName("Producto A");
        existing.setDescription("Descripción antigua");
        existing.setPrice(50.0);
        existing.setStock(5);

        Catalogo updateData = new Catalogo();
        updateData.setName("Producto B");
        updateData.setDescription("Descripción nueva");
        updateData.setPrice(75.0);
        updateData.setStock(8);

        when(productRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(productRepository.save(any(Catalogo.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Catalogo resultado = catalogoService.update(1L, updateData);

        assertEquals("Producto B", resultado.getName());
        assertEquals("Descripción nueva", resultado.getDescription());
        assertEquals(75.0, resultado.getPrice());
        assertEquals(8, resultado.getStock());
        verify(productRepository, times(1)).findById(1L);
        verify(productRepository, times(1)).save(existing);
    }

    @Test
    void testDelete() {
        doNothing().when(productRepository).deleteById(1L);

        catalogoService.delete(1L);

        verify(productRepository, times(1)).deleteById(1L);
    }
}
