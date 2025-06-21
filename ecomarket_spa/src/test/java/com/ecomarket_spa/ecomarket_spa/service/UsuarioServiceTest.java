package com.ecomarket_spa.ecomarket_spa.service;

import com.ecomarket_spa.ecomarket_spa.model.Usuario;
import com.ecomarket_spa.ecomarket_spa.repository.UsuarioRepository;
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
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @Test
    void testRegister() {
        Usuario user = new Usuario();
        user.setUsername("ilvany");
        user.setPassword("1234");
        user.setRole("USER");

        when(usuarioRepository.save(user)).thenReturn(user);

        Usuario resultado = usuarioService.register(user);

        assertNotNull(resultado);
        assertEquals("ilvany", resultado.getUsername());
        verify(usuarioRepository, times(1)).save(user);
    }

    @Test
    void testFindByUsername() {
        Usuario user = new Usuario();
        user.setUsername("ilvany");

        when(usuarioRepository.findByUsername("ilvany")).thenReturn(user);

        Usuario resultado = usuarioService.findByUsername("ilvany");

        assertNotNull(resultado);
        assertEquals("ilvany", resultado.getUsername());
        verify(usuarioRepository, times(1)).findByUsername("ilvany");
    }

    @Test
    void testFindAll() {
        Usuario user1 = new Usuario();
        user1.setUsername("user1");

        Usuario user2 = new Usuario();
        user2.setUsername("user2");

        when(usuarioRepository.findAll()).thenReturn(List.of(user1, user2));

        List<Usuario> resultado = usuarioService.findAll();

        assertEquals(2, resultado.size());
        verify(usuarioRepository, times(1)).findAll();
    }

    @Test
    void testUpdate() {
        Usuario existing = new Usuario();
        existing.setId(1L);
        existing.setUsername("oldUser");
        existing.setPassword("oldPass");
        existing.setRole("USER");

        Usuario updateData = new Usuario();
        updateData.setUsername("newUser");
        updateData.setPassword("newPass");
        updateData.setRole("ADMIN");

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(usuarioRepository.save(any(Usuario.class))).thenAnswer(i -> i.getArgument(0));

        Usuario resultado = usuarioService.update(1L, updateData);

        assertEquals("newUser", resultado.getUsername());
        assertEquals("newPass", resultado.getPassword());
        assertEquals("ADMIN", resultado.getRole());

        verify(usuarioRepository, times(1)).findById(1L);
        verify(usuarioRepository, times(1)).save(existing);
    }

    @Test
    void testDelete() {
        doNothing().when(usuarioRepository).deleteById(1L);

        usuarioService.delete(1L);

        verify(usuarioRepository, times(1)).deleteById(1L);
    }
}
