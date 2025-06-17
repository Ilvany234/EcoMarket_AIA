package com.ecomarket_spa.ecomarket_spa.model;

import jakarta.persistence.*;
        import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Catalogo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private double price;
    private int stock;
}
