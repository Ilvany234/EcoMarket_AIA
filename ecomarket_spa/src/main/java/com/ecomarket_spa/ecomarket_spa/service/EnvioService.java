@Service
@Profile("hateoas") // Solo para pruebas HATEOAS
public class EnvioService {

    private final List<Envio> envios = List.of(
        new Envio(1L, "Santiago", "Valparaíso", "2025-07-01"),
        new Envio(2L, "Temuco", "Puerto Montt", "2025-07-02")
    );

    public List<Envio> obtenerTodosLosEnvios() {
        return envios;
    }

    public Envio obtenerEnvioPorId(Long id) {
        return envios.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Envío no encontrado"));
    }

    public Envio crearEnvio(Envio envio) {
        // Simulado
        return envio;
    }

    public Envio actualizarEnvio(Long id, Envio envio) {
        return envio;
    }

    public void eliminarEnvio(Long id) {
        // Simulado
    }
}
