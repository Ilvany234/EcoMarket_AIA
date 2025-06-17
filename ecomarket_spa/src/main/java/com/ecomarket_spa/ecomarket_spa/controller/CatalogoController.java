package com.ecomarket_spa.ecomarket_spa.controller;

import com.ecomarket_spa.ecomarket_spa.model.Catalogo;
import com.ecomarket_spa.ecomarket_spa.service.CatalogoService;
import org.springframework.web.bind.annotation.*;

        import java.util.List;

@RestController
@RequestMapping("/api/catalogo")
public class CatalogoController {

    private final CatalogoService catalogoService;

    public CatalogoController(CatalogoService productService) {
        this.catalogoService = productService;
    }

    @PostMapping
    public Catalogo createProduct(@RequestBody Catalogo catalogo) {
        return catalogoService.save(catalogo);
    }

    @GetMapping
    public List<Catalogo> getAllProducts() {
        return catalogoService.findAll();
    }

    @GetMapping("/{id}")
    public Catalogo getProduct(@PathVariable Long id) {
        return catalogoService.findById(id);
    }

    @PutMapping("/{id}")
    public Catalogo updateProduct(@PathVariable Long id, @RequestBody Catalogo catalogo) {
        return catalogoService.update(id, catalogo);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        catalogoService.delete(id);
    }
}
