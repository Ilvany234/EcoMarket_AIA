package com.ecomarket_spa.ecomarket_spa.service;

import com.ecomarket_spa.ecomarket_spa.model.Usuario;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Profile("hateoas")
public class UsuarioService {

    private final List<Usuario> usuarios = List.of(
        new Usuario(1L, "Ana", "ana@email.com"),
        new Usuario(2L, "Luis", "luis@email.com")
    );

    public List<Usuario> obtenerTodos() {
        return usuarios;
    }

    public Optional<Usuario> obtenerPorId(Long id) {
        return usuarios.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst();
    }

    public Usuario crear(Usuario usuario) {
        return usuario;
    }

    public Optional<Usuario> actualizar(Long id, Usuario usuario) {
        return Optional.of(usuario);
    }

    public boolean eliminar(Long id) {
        return true;
    }
}
