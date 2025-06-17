package com.ecomarket_spa.ecomarket_spa.repository;

import com.ecomarket_spa.ecomarket_spa.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByUsername(String username);
}
