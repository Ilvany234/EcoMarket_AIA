package com.ecomarket_spa.ecomarket_spa;

import com.ecomarket_spa.ecomarket_spa.model.*;
import com.ecomarket_spa.ecomarket_spa.repository.*;

import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Random;

@Profile("dev")
@Component
public class DataLoader implements CommandLineRunner {

    @Autowired private UsuarioRepository usuarioRepository;
    @Autowired private PedidoRepository pedidoRepository;
    @Autowired private EnvioRepository envioRepository;
    @Autowired private CatalogoRepository catalogoRepository;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();
        Random random = new Random();

        // ---------------- USUARIOS ----------------
        String[] roles = {"cliente", "admin", "repartidor"};
        for (int i = 0; i < 20; i++) {
            Usuario usuario = new Usuario();
            usuario.setUsername(faker.name().username());
            usuario.setPassword(faker.internet().password(8, 12));
            usuario.setRole(roles[random.nextInt(roles.length)]);
            usuarioRepository.save(usuario);
        }

        // ---------------- CATÁLOGO ----------------
        for (int i = 0; i < 20; i++) {
            Catalogo catalogo = new Catalogo();
            catalogo.setName(faker.commerce().productName());
            catalogo.setDescription(faker.lorem().sentence());
            catalogo.setPrice(Double.parseDouble(faker.commerce().price(10.0, 300.0)));
            catalogo.setStock(faker.number().numberBetween(10, 200));
            catalogoRepository.save(catalogo);
        }

        // ---------------- PEDIDOS ----------------
        for (int i = 0; i < 20; i++) {
            Pedido pedido = new Pedido();
            pedido.setUserId((long) faker.number().numberBetween(1, 21)); // IDs válidos de Usuario
            pedido.setOrderDate(LocalDateTime.now().minusDays(faker.number().numberBetween(0, 30)));
            pedido.setStatus(faker.options().option("PENDIENTE", "PROCESADO", "ENVIADO", "ENTREGADO"));
            pedidoRepository.save(pedido);
        }

        // ---------------- ENVIOS ----------------
        for (int i = 0; i < 20; i++) {
            Envio envio = new Envio();
            envio.setPedidoId((long) faker.number().numberBetween(1, 21)); // IDs válidos de Pedido
            envio.setDireccionEntrega(faker.address().fullAddress());
            envio.setTransportista(faker.name().fullName());
            envio.setEstado(faker.options().option("EN_PREPARACION", "EN_CAMINO", "ENTREGADO"));
            envio.setFechaEnvio(LocalDateTime.now().minusDays(faker.number().numberBetween(1, 10)));
            envio.setFechaEntregaEstimada(LocalDateTime.now().plusDays(faker.number().numberBetween(1, 5)));
            envioRepository.save(envio);
        }
    }
}