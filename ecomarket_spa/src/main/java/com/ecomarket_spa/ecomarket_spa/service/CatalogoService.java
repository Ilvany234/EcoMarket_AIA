package com.ecomarket_spa.ecomarket_spa.service;


import com.ecomarket_spa.ecomarket_spa.model.Catalogo;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class CatalogoService {


    private final List<Catalogo> catalogoMock = new ArrayList<>();


    public CatalogoService() {
        catalogoMock.add(new Catalogo(1L, "Detergente ecológico", 4590.0, 100));
        catalogoMock.add(new Catalogo(2L, "Cepillo biodegradable", 2990.0, 50));
    }


    // GET todos los productos
    public List<Catalogo> obtenerCatalogo() {
        return catalogoMock;
    }


    // GET por ID
    public Catalogo obtenerPorId(Long id) {
        return catalogoMock.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }


    // POST crear producto
    public Catalogo crearProducto(Catalogo nuevo) {
        nuevo.setId((long) (catalogoMock.size() + 1));
        catalogoMock.add(nuevo);
        return nuevo;
    }


    // PUT actualizar producto
    public Catalogo actualizarProducto(Long id, Catalogo actualizado) {
        Optional<Catalogo> existenteOpt = catalogoMock.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();


        if (existenteOpt.isPresent()) {
            Catalogo existente = existenteOpt.get();
            existente.setNombre(actualizado.getNombre());
            existente.setPrecio(actualizado.getPrecio());
            existente.setStock(actualizado.getStock());
            return existente;
        }
        return null;
    }


    // DELETE eliminar producto
    public boolean eliminarProducto(Long id) {
        return catalogoMock.removeIf(p -> p.getId().equals(id));
    }
}


