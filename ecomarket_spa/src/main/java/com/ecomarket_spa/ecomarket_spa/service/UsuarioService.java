package com.ecomarket_spa.ecomarket_spa.service;

import com.ecomarket_spa.ecomarket_spa.model.Usuario;
import com.ecomarket_spa.ecomarket_spa.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario register(Usuario user) {
        return usuarioRepository.save(user);
    }

    public Usuario findByUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Usuario update(Long id, Usuario usuario) {
        Usuario existing = usuarioRepository.findById(id).orElseThrow();
        existing.setUsername(usuario.getUsername());
        existing.setPassword(usuario.getPassword());
        existing.setRole(usuario.getRole());
        return usuarioRepository.save(existing);
    }

    public void delete(Long id) {
        usuarioRepository.deleteById(id);
    }
}
