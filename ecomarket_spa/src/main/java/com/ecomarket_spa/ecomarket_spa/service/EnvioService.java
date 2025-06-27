package com.ecomarket_spa.ecomarket_spa.service;


import com.ecomarket_spa.ecomarket_spa.model.Envio;
import org.springframework.stereotype.Service;


import java.util.*;
import java.util.concurrent.atomic.AtomicLong;


@Service
public class EnvioService {


    private final Map<Long, Envio> envioStorage = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong();


    public List<Envio> obtenerTodosLosEnvios() {
        return new ArrayList<>(envioStorage.values());
    }


    public Envio obtenerEnvioPorId(Long id) {
        Envio envio = envioStorage.get(id);
        if (envio == null) {
            throw new NoSuchElementException("Envío no encontrado con ID: " + id);
        }
        return envio;
    }


    public Envio crearEnvio(Envio envio) {
        Long id = idGenerator.incrementAndGet();
        envio.setId(id);
        envioStorage.put(id, envio);
        return envio;
    }


    public Envio actualizarEnvio(Long id, Envio envioActualizado) {
        if (!envioStorage.containsKey(id)) {
            throw new NoSuchElementException("No se puede actualizar. Envío no encontrado con ID: " + id);
        }
        envioActualizado.setId(id);
        envioStorage.put(id, envioActualizado);
        return envioActualizado;
    }


    public void eliminarEnvio(Long id) {
        if (!envioStorage.containsKey(id)) {
            throw new NoSuchElementException("No se puede eliminar. Envío no encontrado con ID: " + id);
        }
        envioStorage.remove(id);
    }
}


