package com.ecomarket_spa.ecomarket_spa.service;


import com.ecomarket_spa.ecomarket_spa.model.Usuario;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;


@Service
public class UsuarioService {


    private final List<Usuario> usuarios = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);


    public List<Usuario> obtenerTodos() {
        return usuarios;
    }


    public Optional<Usuario> obtenerPorId(Long id) {
        return usuarios.stream().filter(u -> u.getId().equals(id)).findFirst();
    }


    public Usuario crear(Usuario usuario) {
        usuario.setId(idGenerator.getAndIncrement());
        usuarios.add(usuario);
        return usuario;
    }


    public Optional<Usuario> actualizar(Long id, Usuario usuarioActualizado) {
        return obtenerPorId(id).map(usuario -> {
            usuario.setUsername(usuarioActualizado.getUsername());
            usuario.setPassword(usuarioActualizado.getPassword());
            usuario.setRole(usuarioActualizado.getRole());
            return usuario;
        });
    }


    public boolean eliminar(Long id) {
        return usuarios.removeIf(u -> u.getId().equals(id));
    }
}
