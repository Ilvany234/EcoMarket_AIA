@Service
@Profile("hateoas")
public class CatalogoService {

    private final List<Catalogo> productos = List.of(
            new Catalogo(1L, "Detergente Eco", "Limpieza", 4500.0),
            new Catalogo(2L, "Cepillo Bambú", "Higiene", 1200.0)
    );

    public List<Catalogo> obtenerCatalogo() {
        return productos;
    }

    public Catalogo obtenerPorId(Long id) {
        return productos.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }
}
