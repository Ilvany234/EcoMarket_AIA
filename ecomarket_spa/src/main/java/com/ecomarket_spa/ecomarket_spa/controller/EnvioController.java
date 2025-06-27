package com.ecomarket_spa.ecomarket_spa.controller;


import com.ecomarket_spa.ecomarket_spa.model.Envio;
import com.ecomarket_spa.ecomarket_spa.service.EnvioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/api/envios")
@Tag(name = "Envios", description = "Operaciones relacionadas con envíos")
public class EnvioController {


    private final EnvioService envioService;


    public EnvioController(EnvioService envioService) {
        this.envioService = envioService;
    }


    @GetMapping
    @Operation(summary = "Obtener todos los envíos")
    public List<Envio> obtenerTodos() {
        return envioService.obtenerTodosLosEnvios();
    }


    @GetMapping("/{id}")
    @Operation(summary = "Obtener un envío por su ID")
    public Envio obtenerPorId(@PathVariable Long id) {
        return envioService.obtenerEnvioPorId(id);
    }


    @PostMapping
    @Operation(summary = "Crear un nuevo envío")
    public Envio crearEnvio(@RequestBody Envio envio) {
        return envioService.crearEnvio(envio);
    }


    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un envío existente")
    public Envio actualizarEnvio(@PathVariable Long id, @RequestBody Envio envio) {
        return envioService.actualizarEnvio(id, envio);
    }


    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un envío")
    public void eliminarEnvio(@PathVariable Long id) {
        envioService.eliminarEnvio(id);
    }
}
