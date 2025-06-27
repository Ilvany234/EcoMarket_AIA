package com.ecomarket_spa.ecomarket_spa.controller;


import com.ecomarket_spa.ecomarket_spa.model.Catalogo;
import com.ecomarket_spa.ecomarket_spa.service.CatalogoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/api/catalogo")
@Tag(name = "Catálogo", description = "Operaciones relacionadas con productos del catálogo")
public class CatalogoController {


    @Autowired
    private CatalogoService catalogoService;


    @GetMapping
    @Operation(summary = "Obtener lista de productos del catálogo")
    public List<Catalogo> obtenerCatalogo() {
        return catalogoService.obtenerCatalogo();
    }


    @GetMapping("/{id}")
    @Operation(summary = "Obtener un producto del catálogo por su ID")
    public Catalogo obtenerPorId(@PathVariable Long id) {
        return catalogoService.obtenerPorId(id);
    }


    @PostMapping
    @Operation(summary = "Agregar un nuevo producto al catálogo")
    public Catalogo crearProducto(@RequestBody Catalogo catalogo) {
        return catalogoService.crearProducto(catalogo);
    }


    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un producto existente del catálogo")
    public Catalogo actualizarProducto(@PathVariable Long id, @RequestBody Catalogo catalogo) {
        return catalogoService.actualizarProducto(id, catalogo);
    }


    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un producto del catálogo por su ID")
    public void eliminarProducto(@PathVariable Long id) {
        catalogoService.eliminarProducto(id);
    }
}
