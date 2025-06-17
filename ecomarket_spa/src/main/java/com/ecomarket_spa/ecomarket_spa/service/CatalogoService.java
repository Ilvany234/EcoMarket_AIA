package com.ecomarket_spa.ecomarket_spa.service;

import com.ecomarket_spa.ecomarket_spa.model.Catalogo;
import com.ecomarket_spa.ecomarket_spa.repository.CatalogoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogoService {

    private final CatalogoRepository productRepository;

    public CatalogoService(CatalogoRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Catalogo save(Catalogo product) {
        return productRepository.save(product);
    }

    public List<Catalogo> findAll() {
        return productRepository.findAll();
    }

    public Catalogo findById(Long id) {
        return productRepository.findById(id).orElseThrow();
    }

    public Catalogo update(Long id, Catalogo product) {
        Catalogo existing = findById(id);
        existing.setName(product.getName());
        existing.setDescription(product.getDescription());
        existing.setPrice(product.getPrice());
        existing.setStock(product.getStock());
        return productRepository.save(existing);
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}
