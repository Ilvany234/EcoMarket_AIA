package com.ecomarket_spa.ecomarket_spa.model;

import jakarta.persistence.*;
        import lombok.*;

        import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "envios")
public class Envio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long pedidoId;
    private String direccionEntrega;
    private String transportista;
    private String estado; // Ej: "EN_PREPARACION", "EN_CAMINO", "ENTREGADO"
    private LocalDateTime fechaEnvio;
    private LocalDateTime fechaEntregaEstimada;
}
