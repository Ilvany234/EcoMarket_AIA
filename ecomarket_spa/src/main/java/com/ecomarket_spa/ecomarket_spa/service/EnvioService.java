package com.ecomarket_spa.ecomarket_spa.service;

import com.ecomarket_spa.ecomarket_spa.model.Envio;
import com.ecomarket_spa.ecomarket_spa.repository.EnvioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnvioService {

    @Autowired
    private EnvioRepository envioRepository;

    public List<Envio> obtenerTodos() {
        return envioRepository.findAll();
    }

    public Optional<Envio> obtenerPorId(Long id) {
        return envioRepository.findById(id);
    }

    public Envio crearEnvio(Envio envio) {
        return envioRepository.save(envio);
    }

    public Envio actualizarEnvio(Long id, Envio envio) {
        Optional<Envio> existente = envioRepository.findById(id);
        if (existente.isPresent()) {
            Envio e = existente.get();
            e.setPedidoId(envio.getPedidoId());
            e.setDireccionEntrega(envio.getDireccionEntrega());
            e.setTransportista(envio.getTransportista());
            e.setEstado(envio.getEstado());
            e.setFechaEnvio(envio.getFechaEnvio());
            e.setFechaEntregaEstimada(envio.getFechaEntregaEstimada());
            return envioRepository.save(e);
        } else {
            throw new RuntimeException("Envío no encontrado");
        }
    }

    public void eliminarEnvio(Long id) {
        envioRepository.deleteById(id);
    }
}
