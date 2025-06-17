package com.ecomarket_spa.ecomarket_spa.repository;

import com.ecomarket_spa.ecomarket_spa.model.Catalogo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatalogoRepository extends JpaRepository<Catalogo, Long> {
    // Puedes añadir consultas personalizadas si necesitas
}
